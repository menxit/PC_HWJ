package Speedup;

import HWJ.BinaryTreeAdder;
import Tree.Node;

public class BenchmarkBinaryTreeAdder {

    public double getBenchmark(BinaryTreeAdder binaryTreeAdder, Node node) {
        long startTime = System.nanoTime();
        binaryTreeAdder.computeOnerousSum(node);
        return System.nanoTime() - startTime;
    }

}
