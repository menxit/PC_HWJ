import Factories.FactoryTree;
import HWJ.BinaryTreeAdder;
import HWJ.BufferBinaryTreeAdder;
import HWJ.Seriale;
import Tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BufferBinaryTreeAdderTest {

    private final BinaryTreeAdder serial = new Seriale();
    private final FactoryTree factoryTree = new FactoryTree();

    @Test
    void hwj1TestBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6);
        BinaryTreeAdder hwj1 = new BufferBinaryTreeAdder();
        assertEquals(hwj1.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

    @Test
    void hwj1TestNotBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6, 3);
        BinaryTreeAdder hwj1 = new BufferBinaryTreeAdder();
        assertEquals(hwj1.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

    @Test
    void hwj2TestBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6);
        BinaryTreeAdder hwj2 = new BufferBinaryTreeAdder(tree.getMaxDepth());
        assertEquals(hwj2.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

    @Test
    void hwj2TestNotBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6, 3);
        BinaryTreeAdder hwj2 = new BufferBinaryTreeAdder(tree.getMaxDepth());
        assertEquals(hwj2.computeOnerousSum(tree), this.serial.computeOnerousSum(tree));
    }

}
