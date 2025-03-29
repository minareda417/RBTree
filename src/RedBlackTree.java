public class RedBlackTree <T extends Comparable<T>>{
    private Node<T> root;
    private final Node<T> TNull;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLACK = "\u001B[30m";

    public RedBlackTree(){
        this.TNull = new Node<>(null);
        TNull.color = Color.Black;
        this.root = this.TNull;
    }

    private void preOrderHelper(Node<T> node) {
        if (node != TNull) {
            System.out.print((node.color == Color.Red ? ANSI_RED : ANSI_BLACK) + node.key + ANSI_RESET + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Function to start preorder traversal
    public void preorder() {
        preOrderHelper(this.root);
    }

    // Inorder traversal helper function
    private void inOrderHelper(Node<T> node) {
        if (node != TNull) {
            inOrderHelper(node.left);
            System.out.print((node.color == Color.Red ? ANSI_RED : ANSI_BLACK) + node.key + ANSI_RESET + " ");
            inOrderHelper(node.right);
        }
    }

    // Function to start inorder traversal
    public void inorder() {
        inOrderHelper(this.root);
    }

    // Postorder traversal helper function
    private void postOrderHelper(Node<T> node) {
        if (node != TNull) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print((node.color == Color.Red ? ANSI_RED : ANSI_BLACK) + node.key + ANSI_RESET + " ");
        }
    }

    // Function to start postorder traversal
    public void postorder() {
        postOrderHelper(this.root);
    }


    private void leftRotate(Node<T> x) {
        Node<T> y = x.right;
        x.right = y.left; // turn y's left subtree into x's right subtree
        if (y.left != this.TNull) // if y's left subtree is not empty
            y.left.parent = x; // ... then x becomes the parent of the subtree's root
        y.parent = x.parent; // x's parent becomes y's parent
        if (x.parent == null) // if x was the root
            this.root = y; // ... then y becomes the root
        else if (x == x.parent.left) // otherwise, if x was a left child
            x.parent.left = y; // ... then y becomes a left child
        else
            x.parent.right = y; // otherwise, x was a right child, and now y is
        y.left = x; // make x become y's left child
        x.parent = y;
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.left;
        x.left = y.right; // turn y's right subtree into x's left subtree
        if (y.right != this.TNull) // if y's right subtree is not empty
            y.right.parent = x; // ... then x becomes the parent of the subtree's root
        y.parent = x.parent; // x's parent becomes y's parent
        if (x.parent == null) // if x was the root
            this.root = y; // ... then y becomes the root
        else if (x == x.parent.right) // otherwise, if x was a right child
            x.parent.right = y; // ... then y becomes a right child
        else
            x.parent.left = y; // otherwise, x was a left child, and now y is
        y.right = x; // make x become y's right child
        x.parent = y;
    }

    public void insert(T key) {
        Node<T> z = new Node<>(key);
        Node<T> x = this.root; // node being compared with z
        Node<T> y = this.TNull; // y will be parent of z
        while (x != this.TNull) { // descend until reaching the sentinel
            y = x;
            if (z.key.compareTo(x.key) < 0)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y; // found the location -insert z with parent y
        if (y == this.TNull)
            this.root = z; // tree T was empty
        else if (z.key.compareTo(y.key) < 0)
            y.left = z;
        else
            y.right = z;
        z.left = this.TNull; // both of z's children are the sentinel
        z.right = this.TNull;
        z.color = Color.Red; // the new node starts out red
        insertFixup(z); // correct any violations of red-black properties
    }

    private void insertFixup(Node<T> z) {
        Node<T> y;
        while (z.parent.color == Color.Red) {
            if (z.parent == z.parent.parent.left) { // is z's parent a left child?
                y = z.parent.parent.right; // y is z's uncle
                if (y.color == Color.Red) { // are z's parent and uncle both red?
                    // case 1
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        // case 2
                        z = z.parent;
                        leftRotate(z);
                    }
                    // case 3
                    z.parent.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    rightRotate(z.parent.parent);
                }
            } else { // same as lines 116-132 but with "right" and "left" exchanged
                y = z.parent.parent.left;
                if (y.color == Color.Red) {
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    z.parent.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    leftRotate(z.parent.parent);
                }
            }
        }
        this.root.color = Color.Black;
    }

    public Node<T> search(T key){
        Node<T> x = this.root;
        while (x != this.TNull){
            int comparison = x.key.compareTo(key);
            if (comparison == 0){
                System.out.println("\n" +key+ " found.");
                return x;
            }

            else if (comparison > 0)
                x = x.left;
            else
                x = x.right;
        }
        System.out.println("\n" +key+ " not found.");
        return null;
    }

}
