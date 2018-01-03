package HWJ;

import Factories.FactoryOnerousProcessor;
import OnerousProcessor.OnerousProcessor;
import Tree.*;

public class ParallelStreamBinaryTreeAdder implements BinaryTreeAdder {

    private final OnerousProcessor onerousProcessor = new FactoryOnerousProcessor().createFakeProcessor();

    @Override
    public int computeOnerousSum(Node root) {
        return root.parallelStream().map(n -> n.getValue()).mapToInt(onerousProcessor::onerousFunction).sum();
    }

}
