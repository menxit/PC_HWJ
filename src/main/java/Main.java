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

        for(int i = 8; i <= 20; i+=4) {
            Node tree = factoryTree.randomBinaryTree(i);
            speedupBinaryTreeAdder = new SpeedupBinaryTreeAdder(new Seriale(), tree);
            Double speedup;

            speedup = speedupBinaryTreeAdder.getSpeedUp(new BufferBinaryTreeAdder());
            System.out.println("HWJ1, height = " + i + ":\t" + speedup);

            speedup = speedupBinaryTreeAdder.getSpeedUp(new BufferBinaryTreeAdder(tree.getMaxDepth()));
            System.out.println("HWJ2, height = " + i + ":\t" + speedup);

            speedup = speedupBinaryTreeAdder.getSpeedUp(new ForkJoinBinaryTreeAdder());
            System.out.println("HWJ3, height = " + i + ":\t" + speedup);

            System.out.println("=======================\n");
        }

    }
}

