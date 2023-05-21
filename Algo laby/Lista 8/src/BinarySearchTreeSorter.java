import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeSorter {
    public static <T extends Comparable<T>> void sort(List<T> list) throws DuplicateElementException {
        // TODO: Posortuj listę używając klasy BinarySearchTree.
        BinarySearchTree<T> tree = new BinarySearchTree<>();
        for (T o:list)
            tree.add(o);

        List<T> listTemp = inOrderToArray(tree);

        int n = 0;
        for (T o:listTemp){
            list.set(n,o);
            n++;
        }
    }
    public static <T extends Comparable<T>> List<T> inOrderToArray(BinarySearchTree<T> tree){
        List<T> returned = new ArrayList<>();
        if(tree.isEmpty())
            return returned;
        return InOrderToArrayHelper(tree.getRoot(), returned);

    }
    private static <T extends Comparable<T>> List<T> InOrderToArrayHelper(BinarySearchTree.Node root, List<T> returned) {

        if(root != null) {
            returned = InOrderToArrayHelper(root.getLeftChild(), returned);
            returned.add((T) root.getValue());
            returned = InOrderToArrayHelper(root.getRightChild(), returned);
        }
        return returned;
    }
}
