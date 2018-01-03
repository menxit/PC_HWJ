package HWJ;

import Factories.FactoryOnerousProcessor;
import OnerousProcessor.*;
import Queue.*;
import Tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BufferBinaryTreeAdder implements BinaryTreeAdder {

    private ExecutorService executorService;
    private HWJQueue<Node> buffer;
    private final OnerousProcessor onerousProcessor = new FactoryOnerousProcessor().createFakeProcessor();
    private final int numberOfWorkers = Runtime.getRuntime().availableProcessors();
    private Semaphore sx;

    /**
     * Se viene richiamato il costruttore senza specificare la dimensione del buffer,
     * allora si inizializzerà buffer con una concurrentQueue di dimensione illimitata.
     */
    public BufferBinaryTreeAdder() {
        this.buffer = new ConcurrentQueue<Node>();
    }

    /**
     * Se viene specificato un sizeSingleScheduleQueue allora verrà inizializzata una
     * WorkStealingQueue. Essa dedicherà una schedule queue della dimensione indicata
     * per ogni thread che la utilizzerà.
     *
     * @param sizeSingleScheduleQueue
     */
    public BufferBinaryTreeAdder(int sizeSingleScheduleQueue) {
        this.buffer = new WorkStealingQueue<Node>(sizeSingleScheduleQueue);
    }

    @Override
    public int computeOnerousSum(Node root) {

        /**
         * Se il nodo è null, restituisci direttamente 0.
         */
        if(root == null)
            return 0;

        /**
         * Pulisci il buffer nel caso in cui computerOnerousSum
         * venga eseguito più di una volta.
         */
        buffer.clear();

        /**
         * Creo l'executorService fissando il numero di thread.
         */
        executorService = Executors.newFixedThreadPool(numberOfWorkers);

        /**
         * Inizializzo il semaforo in stile cooperativo. Questo semaforo
         * servirà a determinare quando l'albero è stato interamente visitato.
         */
        sx = new Semaphore(0);

        /**
         * Inizializzo una lista di future. Ciascun future restituirà
         * un subtotale del risultato finale.
         */
        List<Future> futures = new ArrayList<>();

        /**
         * Il primo task viene invocato con il nodo root. In questo modo
         * un unico flusso di esecuzione aggiungerà il nodo root al buffer.
         */
        futures.add(executorService.submit(() -> this.task(root)));

        /**
         * Invio i successivi task all'executorService.
         */
        for(int i = 1; i < numberOfWorkers; i++)
            futures.add(executorService.submit((Callable<Integer>) this::task));

        /**
         * Comunico all'executor service di non accettare più nuovi task.
         */
        shutDownExecutorService();

        /**
         * A questo punto ottengo i risultati parziali dei future e li sommo
         * ottenendo in questo modo il risultato finale.
         */
        return futures.stream().mapToInt(this::getPartialResult).sum();
    }

    private int task(Node root) {

        int result = 0;

        if(root != null) {
            buffer.push(root);
        }

        do {

            Node node = buffer.pop();

            if(node != null) {
                sx.release();
                pushChildren(node);
                result += onerousProcessor.onerousFunction(node.getValue());
                try {
                    sx.acquire(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } while(!exitCondition());

        return result;

    }

    private int task() {
        return task( null);
    }

    private boolean exitCondition() {

        /**
         * Se non riesco a ottenere neanche un semaforo
         * e il buffer è vuoto significa che il buffer è vuoto e che
         * nessuno sta lavorando, quindi posso uscire.
         */
        if(!sx.tryAcquire(1) && buffer.isEmpty()) {
            sx.release();
            return true;
        }

        /**
         * Altrimenti significa che c'è almeno un worker che
         * sta lavorando o che il buffer non è vuoto. In entrambi
         * i casi non è ancora possibile uscire.
         */
        return false;

    }

    /**
     * Aggiunge i figli del nodo nel buffer condiviso.
     *
     * @param node
     */
    private void pushChildren(Node node) {
        if (node.getSx() != null)
            this.buffer.push(node.getSx());

        if (node.getDx() != null)
            this.buffer.push(node.getDx());
    }

    /**
     * Prendo un future e attendo il risultato.
     *
     * @param future
     * @return
     */
    private int getPartialResult(Future<Integer> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Attendi che tutti i task terminino la loro esecuzione
     * e infine spegni l'executorService.
     */
    private void shutDownExecutorService() {
        this.executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
