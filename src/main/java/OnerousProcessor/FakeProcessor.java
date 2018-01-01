package OnerousProcessor;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class FakeProcessor implements OnerousProcessor {

    public final static int MIN_COUNT = 1000;
    private int max;
    private final Map<Thread, Random> theRandomHashMap = new ConcurrentHashMap<>();

    public FakeProcessor(int max) {
        this.max = max;
    }

    @Override
    public int onerousFunction(int value) {
        Random random = this.getRandom();
        int r = random.nextInt(this.max);
        int n = Math.max(MIN_COUNT, r);
        for(int counter=0; counter<n; counter++) {
            r = random.nextInt(this.max);
            r = r ^ random.nextInt(this.max);
            r = r ^ random.nextInt(this.max);
        }
        return value;
    }

    /**
     * Se si usa una singola istanza di Random tra diversi thread, per garantire
     * la thread safety di fatto si serializza l'accesso al metodo nextInt.
     * Per evitare di avere una istanza per ciascun thread di FakeProcessor si è
     * creato il metodo getRandom, il quale crea una istanza separata di Random per
     * ciascun thread.
     *
     * @return
     */
    private Random getRandom() {
        Thread currentThread = Thread.currentThread();
        if(!theRandomHashMap.containsKey(currentThread)) {
            theRandomHashMap.put(currentThread, new Random());
        }
        return theRandomHashMap.get(currentThread);
    }

}