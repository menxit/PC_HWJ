import Factories.FactoryTree;
import HWJ.*;
import Speedup.SpeedupBinaryTreeAdder;
import Tree.Node;

public class Main {

    public static void main(String[] args) {

        FactoryTree factoryTree = new FactoryTree();
        SpeedupBinaryTreeAdder speedupBinaryTreeAdder;

        Seriale warmup = new Seriale();
        warmup.computeOnerousSum(factoryTree.randomBinaryTree(5));

        for(int i = 8; i <= 20; i = i+4) {
            Node tree = factoryTree.randomBinaryTree(i);
            speedupBinaryTreeAdder = new SpeedupBinaryTreeAdder();
            Double speedup;

            Seriale seriale = new Seriale();

            speedup = speedupBinaryTreeAdder.getSpeedUp(seriale, new BufferBinaryTreeAdder(), tree);
            System.out.println("HWJ1, height = " + i + ":\t" + speedup);

            speedup = speedupBinaryTreeAdder.getSpeedUp(seriale, new BufferBinaryTreeAdder(tree.getMaxDepth()), tree);
            System.out.println("HWJ2, height = " + i + ":\t" + speedup);

            speedup = speedupBinaryTreeAdder.getSpeedUp(seriale, new ForkJoinBinaryTreeAdder(), tree);
            System.out.println("HWJ3, height = " + i + ":\t" + speedup);

            System.out.println("======================================\n");
        }

    }
}

