public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);

        System.out.println("Preorder traversal:");
        tree.preorder();

        System.out.println("\nInorder traversal:");
        tree.inorder();

        System.out.println("\nPostorder traversal:");
        tree.postorder();
    }
}