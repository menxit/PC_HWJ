package Factories;

import OnerousProcessor.*;

public class FactoryOnerousProcessor {

    public OnerousProcessor createFakeProcessor() {
        return  new FakeProcessor(FakeProcessor.MIN_COUNT * 2);
    }

}
