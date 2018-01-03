# How to run
```
mvn package -Dmaven.test.skip=true && java -jar target/P_HWJ-1.0-SNAPSHOT.jar 
```

### Risultati Speedup
Risultati ottenuti su una macchina con 16 core:
```
HWJ1, height = 8:	0.2
HWJ2, height = 8:	2.3
HWJ3, height = 8:	0.6
HWJ4, height = 8:	2.8
=======================

HWJ1, height = 12:	6.7
HWJ2, height = 12:	4.1
HWJ3, height = 12:	9.3
HWJ4, height = 12:	8.0
=======================

HWJ1, height = 16:	10.3
HWJ2, height = 16:	7.9
HWJ3, height = 16:	11.3
HWJ4, height = 16:	12.0
=======================

HWJ1, height = 20:	12.3
HWJ2, height = 20:	8.6
HWJ3, height = 20:	9.1
HWJ4, height = 20:	13.0
=======================
```

# HWJ1 e HWJ2

Per risolvere HWJ1 e HWJ2 è stata utilizzata un unica classe, denominata BufferBinaryTreeAdder.

Se viene inizializzata in questo modo richiama il codice relativo a hwj1:
```
BufferBinaryTreeAdder hwj1 = new BufferBinaryTreeAdder();
```

Dove n è la dimensione di ciascuna schedule queue:
```
int n = 10;
BufferBinaryTreeAdder hwj1 = new BufferBinaryTreeAdder(n);
```

La classe al suo interno fa uso di un buffer di tipo HWJQueue. Questa coda ha due diverse implementazioni:

- ConcurrentQueue: viene utilizzata per risolvere hwj1 e fa uso di una ConcurrentLinkedQueue di dimensione illimitata.
- WorkStealingQueue: viene utilizzata in hwj2

Sia in HWJ1 che in HWJ2 ho definito la exitCondition facendo uso di un semaforo in stile cooperativo.

L'idea è che si ha un semaforo inizializzato a 0. Poi ogni volta che un flusso di esecuzione inizia la onerousFunction
viene rilasciato un semaforo. Quando termina viene riacquisito.

La exitCondition invece è così:

```java
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
```

## HWJ3
La THRESHOLD è calcolata sulla base del numero di nodi dell'albero. Quando il numero di nodi è maggiore di 32 si
preferisce forkare.

## HWJ4
Per implementare il TreeSpiterator ho utilizzato una Deque. L'approccio è simile a quello utilizzato per implementare
la WorkStealingQueue.

### tryAdvance
Qui si fa semplicemente il poll del primo nodo e si pushano nel buffer i figli.

### estimateSize
Si fa la somma dei nodi di ciascun rimanente albero nel buffer.
```java
@Override
public long estimateSize() {
    return this.buffer.parallelStream().mapToInt(n -> n.getNumberOfNodes()).sum();
}
```

### trySplit
TrySplit ruba l'ultimo nodo presente nel buffer. Se ha un figlio sinistro, viene cancellato e quel nodo sinistro
viene passato come parametro al nuovo TreeSpliterator. Altrimenti si verifica che abbia un figlio destro. Se non ha
né un figlio destro, né un figlio sinistro, allora non splitta.