package HWJ;

import Factories.FactoryOnerousProcessor;
import OnerousProcessor.OnerousProcessor;
import Tree.Node;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinBinaryTreeAdder implements BinaryTreeAdder {

    private static class Task extends RecursiveTask<Integer> {

        private final Node node;
        private final BinaryTreeAdder seriale = new Seriale();
        private final int SEQUENTIAL_THRESHOLD = 5;
        private final OnerousProcessor onerousProcessor = new FactoryOnerousProcessor().createFakeProcessor();

        Task(Node node) {
            this.node = node;
        }

        @Override
        protected Integer compute() {

            if(node == null)
                return 0;

            if(node.getMaxDepth() < SEQUENTIAL_THRESHOLD)
                return seriale.computeOnerousSum(node);

            Task left = null, right = null;
            if(node.getSx() != null) {
                left = new Task(node.getSx());
                left.fork();
            }

            if(node.getDx() != null) {
                right = new Task(node.getDx());
                right.fork();
            }

            return onerousProcessor.onerousFunction(node.getValue()) + (left != null ? left.join() : 0) + (right != null ? right.join() : 0);
        }

    }

    @Override
    public int computeOnerousSum(Node node) {

        if(node == null)
            return 0;

        final ForkJoinPool pool = new ForkJoinPool();

        int result;

        try {
            result = pool.invoke(new Task(node));
        } finally {
            pool.shutdown();
        }

        return result;
    }

}
