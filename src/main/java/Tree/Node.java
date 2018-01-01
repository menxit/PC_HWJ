package Tree;

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

}