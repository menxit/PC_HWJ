package Speedup;

import HWJ.BinaryTreeAdder;
import Tree.Node;

public class SpeedupBinaryTreeAdder {

    public double getSpeedUp(BinaryTreeAdder type1, BinaryTreeAdder type2, Node node) {
        BenchmarkBinaryTreeAdder benchmarkBinaryTreeAdder = new BenchmarkBinaryTreeAdder();
        double benchmarkType1 = benchmarkBinaryTreeAdder.getBenchmark(type1, node);
        double benchmarkType2 = benchmarkBinaryTreeAdder.getBenchmark(type2, node);
        return Math.floor((benchmarkType1/benchmarkType2)*10)/10;
    }

}
