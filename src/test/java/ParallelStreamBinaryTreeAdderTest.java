import Factories.FactoryTree;
import HWJ.BinaryTreeAdder;
import HWJ.BufferBinaryTreeAdder;
import HWJ.ParallelStreamBinaryTreeAdder;
import HWJ.Seriale;
import Tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelStreamBinaryTreeAdderTest {

    private final BinaryTreeAdder parallelStream = new ParallelStreamBinaryTreeAdder();
    private final FactoryTree factoryTree = new FactoryTree();

    @Test
    void hwj4TestBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6);
        BinaryTreeAdder hwj4 = new BufferBinaryTreeAdder();
        assertEquals(hwj4.computeOnerousSum(tree), this.parallelStream.computeOnerousSum(tree));
    }

    @Test
    void hwj4TestNotBalancedTree() {
        Tree tree = factoryTree.randomBinaryTree(6, 3);
        BinaryTreeAdder hwj4 = new BufferBinaryTreeAdder();
        assertEquals(hwj4.computeOnerousSum(tree), this.parallelStream.computeOnerousSum(tree));
    }

}
