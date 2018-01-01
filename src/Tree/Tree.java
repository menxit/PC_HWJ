package Tree;

public class Tree implements Node {

    private int value;
    private Node sx, dx;

    public Tree(int value) {
        this.value = value;
        this.sx = null;
        this.dx = null;
    }

    public Tree(int value, Node sx) {
        this.value = value;
        this.sx = sx;
    }

    public Tree(int value, Node sx, Node dx) {
        this.value = value;
        this.sx = sx;
        this.dx = dx;
    }

    @Override
    public int getNumberOfNodes() {
        return 1 + getNumberOfNodes(this.getSx()) + getNumberOfNodes(this.getDx());
    }

    private int getNumberOfNodes(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + getNumberOfNodes(node.getSx()) + getNumberOfNodes(node.getDx());
    }

    @Override
    public int getMaxDepth() {
        return 1 + Math.max(getMaxDepth(this.getSx()), getMaxDepth(this.getDx()));
    }

    private int getMaxDepth(Node node) {
        if(node == null) {
            return 0;
        }
        return 1 + Math.max(getMaxDepth(node.getSx()), getMaxDepth(node.getDx()));
    }

    @Override
    public Node getSx() {
        return this.sx;
    }

    @Override
    public Node getDx() {
        return this.dx;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return toString(this.getSx()) + toString(this.getSx());
    }

    private String toString(Node node) {
        if(node == null) {
            return "";
        }
        return node.getValue() + "\n" + toString(node.getSx()) + toString(node.getDx());
    }

}
