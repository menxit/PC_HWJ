import Factories.FactoryTree;
import Tree.Tree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TreeTest {

    private final FactoryTree factoryTree = new FactoryTree();

    @Test
    void treeValueShouldBeSetted() {
        Tree tree = new Tree(1);
        assertEquals(1, tree.getValue());
    }

    @Test
    void treeWithSingleNodeShouldNotHaveLeftChild() {
        Tree tree = new Tree(1);
        assertNull(tree.getSx());
    }

    @Test
    void treeWithSingleNodeShouldNotHaveRightChild() {
        Tree tree = new Tree(1);
        assertNull(tree.getDx());
    }

    @Test
    void treeShouldHaveLeftChild() {
        Tree leftChild = new Tree(2);
        Tree tree = new Tree(1, leftChild);
        assertNotNull(tree.getSx());
    }

    @Test
    void treeShouldHaveRightChild() {
        Tree rightChild = new Tree(2);
        Tree tree = new Tree(1, null, rightChild);
        assertNotNull(tree.getDx());
    }

    @Test
    void getNumberOfNodesShouldReturnNumberOfNodes() {
        int height = 10;
        Tree tree = factoryTree.randomBinaryTree(height);
        assertEquals(tree.getNumberOfNodes(), (int)Math.pow(2, height) - 1);
    }

    @Test
    void getMaxDepthBalancedTree() {
        int height = 10;
        Tree tree = factoryTree.randomBinaryTree(height);
        assertEquals(tree.getMaxDepth(), 10);
    }

    @Test
    void getMaxDepthNotBalancedTree() {
        int maxHeigth = 10;
        Tree tree = factoryTree.randomBinaryTree(maxHeigth, maxHeigth/2);
        assertEquals(tree.getMaxDepth(), 10);
    }

}
