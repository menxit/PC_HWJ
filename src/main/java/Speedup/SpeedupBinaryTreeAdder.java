package Speedup;

import HWJ.BinaryTreeAdder;
import Tree.Node;

import java.util.HashMap;

public class SpeedupBinaryTreeAdder {

    private Double benchmarkSeriale;
    private Node node;

    public SpeedupBinaryTreeAdder(BinaryTreeAdder seriale, Node node) {
        BenchmarkBinaryTreeAdder benchmarkBinaryTreeAdder = new BenchmarkBinaryTreeAdder();
        this.benchmarkSeriale = benchmarkBinaryTreeAdder.getBenchmark(seriale, node);
        this.node = node;
    }

    public double getSpeedUp(BinaryTreeAdder concorrente) {
        BenchmarkBinaryTreeAdder benchmarkBinaryTreeAdder = new BenchmarkBinaryTreeAdder();
        double benchmarkType2 = benchmarkBinaryTreeAdder.getBenchmark(concorrente, node);
        return Math.floor((benchmarkSeriale/benchmarkType2)*10)/10;
    }

}
