package HWJ;

import Factories.FactoryOnerousProcessor;
import OnerousProcessor.*;
import Tree.Node;

public class Seriale implements BinaryTreeAdder {

    private final OnerousProcessor fakeProcessor = new FactoryOnerousProcessor().createFakeProcessor();

    @Override
    public int computeOnerousSum(Node node) {
        if(node == null) {
            return 0;
        }
        return fakeProcessor.onerousFunction(node.getValue()) + computeOnerousSum(node.getSx()) + computeOnerousSum(node.getDx());
    }

}
