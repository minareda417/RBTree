public class Node<T extends Comparable<T>> {
    T key;
    Node<T> parent;
    Node<T> left;
    Node<T> right;
    Color color;

    public Node(T key) {
        this.key = key;
        this.color = Color.Red; // Default color for new nodes
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}