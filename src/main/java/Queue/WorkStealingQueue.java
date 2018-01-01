package Queue;

import HWJ.Seriale;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

public class WorkStealingQueue<T> implements Queue<T> {

    private int sizeSingleScheduleQueue;
    private Map<Thread, BlockingDeque<T>> scheduleQueues = new ConcurrentHashMap<>();

    public WorkStealingQueue(int sizeSingleScheduleQueue) {
        this.sizeSingleScheduleQueue = sizeSingleScheduleQueue;
    }

    @Override
    public void push(T elem) {
        try {
            getCurrentScheduleQueue().putLast(elem);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T pop() {

        /**
         * Inizialmente provo a estrarre normalmente in coda alla
         * mia scheduling queue.
         */
        T elem = getCurrentScheduleQueue().pollLast();

        /**
         * Se nella mia scheduling queue non c'era niente fare, provo a rubare
         * in testa dalle scheduling queue degli altri flussi.
         */
        if(elem == null) {
            for (Entry<Thread, BlockingDeque<T>> entry : scheduleQueues.entrySet()) {
                elem = entry.getValue().pollFirst();
                if(elem != null)
                    return elem;
            }
        }

        return elem;

    }

    @Override
    public void clear() {
        this.scheduleQueues.clear();
    }

    @Override
    public boolean isEmpty() {
        for (Entry<Thread, BlockingDeque<T>> entry : scheduleQueues.entrySet()) {
            if(!entry.getValue().isEmpty())
                return false;
        }
        return true;
    }

    private BlockingDeque<T> getCurrentScheduleQueue() {
        Thread currentThread = Thread.currentThread();
        scheduleQueues.putIfAbsent(currentThread, new LinkedBlockingDeque<>(sizeSingleScheduleQueue));
        return scheduleQueues.get(currentThread);
    }

}
