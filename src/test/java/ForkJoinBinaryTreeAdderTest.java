import Factories.FactoryTree;
import HWJ.BinaryTreeAdder;
import HWJ.ForkJoinBinaryTreeAdder;
import HWJ.Seriale;
import Tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForkJoinBinaryTreeAdderTest {

    private final BinaryTreeAdder serial = new Seriale();
    private final FactoryTree factoryTree = new FactoryTree();

    @Test
    void hwj3TestBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6);
        BinaryTreeAdder hwj3 = new ForkJoinBinaryTreeAdder();
        assertEquals(hwj3.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

    @Test
    void hwj3TestNotBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6, 3);
        BinaryTreeAdder hwj3 = new ForkJoinBinaryTreeAdder();
        assertEquals(hwj3.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

}
