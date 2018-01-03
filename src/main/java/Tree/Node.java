package Tree;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Node {

    /**
     * Null se non esiste figlio sinistro
     */
    Node getSx();

    /**
     * Null se non esiste figlio destro
     */
    Node getDx();

    /**
     * Restituisce true se ha il figlio sinistro
     *
     * @return
     */
    Boolean hasSx();

    /**
     * Restituisce true se ha il figlio destro
     *
     * @return
     */
    Boolean hasDx();

    /**
     * Setta il figlio sinistro
     */
    void setSx(Node sx);

    /**
     * Setta il figlio destro
     */
    void setDx(Node dx);

    /**
     * Restituisce un intero associato al nodo
     */
    int getValue();

    /**
     * Restituisce il numero di nodi dell'albero.
     *
     * @return
     */
    int getNumberOfNodes();

    /**
     * Restituisce la profondità massima dell'albero.
     *
     * @return
     */
    int getMaxDepth();

    default TreeSpliterator<Node> getSpliterator() {
        return new TreeSpliterator<Node>(this);
    }

    default Stream<Node> parallelStream() {
        TreeSpliterator<Node> spliterator = getSpliterator();
        if(spliterator == null)
            throw new NullPointerException();
        return StreamSupport.stream(getSpliterator(), true);
    }

}