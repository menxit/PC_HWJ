package Queue;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentQueue<T> implements HWJQueue<T> {

    private final ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void push(T elem) {
        queue.add(elem);
    }

    @Override
    public T pop() {
        return queue.poll();
    }

    @Override
    public void clear() {
        queue.clear();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
