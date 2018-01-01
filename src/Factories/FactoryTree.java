package Factories;

import Tree.Tree;
import Utilities.Utilities;

public class FactoryTree {

    public Tree randomBinaryTree(int height) {
        if(height == 0) {
            return null;
        }
        return new Tree(
                Utilities.getRandomIntegerNumber(0, 100),
                randomBinaryTree(height-1),
                randomBinaryTree(height-1)
        );
    }

    public Tree randomBinaryTree(int leftHeight, int rightHeight) {
        return new Tree(
                Utilities.getRandomIntegerNumber(0, 100),
                randomBinaryTree(leftHeight-1),
                randomBinaryTree(rightHeight-1));
    }


}
