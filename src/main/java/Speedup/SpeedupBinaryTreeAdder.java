package Speedup;

import HWJ.BinaryTreeAdder;
import Tree.Node;

import java.util.HashMap;

public class SpeedupBinaryTreeAdder {

    private Double benchmarkType1 = null;

    public double getSpeedUp(BinaryTreeAdder type1, BinaryTreeAdder type2, Node node) {
        BenchmarkBinaryTreeAdder benchmarkBinaryTreeAdder = new BenchmarkBinaryTreeAdder();
        if(benchmarkType1 == null) {
            benchmarkType1 = benchmarkBinaryTreeAdder.getBenchmark(type1, node);
        }
        double benchmarkType2 = benchmarkBinaryTreeAdder.getBenchmark(type2, node);
        return Math.floor((benchmarkType1/benchmarkType2)*10)/10;
    }

}
