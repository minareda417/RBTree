public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.insert(55);
        tree.insert(40);
        tree.insert(65);
        tree.insert(60);
        tree.insert(75);
        tree.insert(57);
        tree.insert(56);
        tree.insert(62);

        System.out.println("Preorder traversal:");
        tree.preorder();

        System.out.println("\nInorder traversal:");
        tree.inorder();

        System.out.println("\nPostorder traversal:");
        tree.postorder();

        tree.search(60);
        tree.search(32);

        System.out.println(tree.getHeight(tree.root));
        System.out.println(tree.getBlackHeight(tree.root));
        System.out.println(tree.getNumberOfNodes(tree.root));
    }
}