package Queue;

public interface Queue<T> {

    /**
     * Aggiunge un elemento.
     * Se la coda è piena restituisce null.
     *
     * @param elem
     * @return
     */
    void push(T elem);


    /**
     * Consuma elemento della coda. Se la coda
     * è vuota restituisce null.
     *
     * @return
     */
    T pop();


    /**
     * Rimuove tutti gli elementi della collection.
     */
    void clear();

    boolean isEmpty();

}
