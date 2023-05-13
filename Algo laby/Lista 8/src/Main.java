public class Main {
    public static void main(String[] args) throws DuplicateElementException {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(8);
        tree.add(2);
        tree.add(1);
        tree.add(3);
        tree.add(10);
        tree.add(7);

        tree.delete(2);
        System.out.println(tree.toStringPreOrder());
    }
}
