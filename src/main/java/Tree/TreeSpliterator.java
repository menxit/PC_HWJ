package Tree;

import java.util.Deque;
import java.util.Spliterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;

public class TreeSpliterator<T> implements Spliterator<T> {

    private Deque<Node> buffer = new ConcurrentLinkedDeque<Node>();

    public TreeSpliterator(Node node) {
        this.buffer.addLast(node);
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        Node node = this.buffer.pollFirst();
        if(node != null) {
            if(node.hasSx())
                this.buffer.addLast(node.getSx());
            if(node.hasDx())
                this.buffer.addLast(node.getDx());
            action.accept((T)node);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<T> trySplit() {
        Node node = buffer.pollLast();
        if (node != null) {
            if(node.hasSx()) {
                Node left = node.getSx();
                node.setSx(null);
                buffer.add(node);
                return new TreeSpliterator<T>(left);
            }
            if(node.hasDx()) {
                Node right = node.getDx();
                node.setDx(null);
                buffer.add(node);
                return new TreeSpliterator<T>(right);
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return this.buffer.parallelStream().mapToInt(n -> n.getNumberOfNodes()).sum();
    }

    @Override
    public int characteristics() {
        return 0;
    }

}
