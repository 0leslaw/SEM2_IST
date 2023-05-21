import org.w3c.dom.Node;

import java.util.HashMap;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    private HashMap<Integer,Node> structure = new HashMap<>();
    private final int SIZE;

    public class Node{
        int value;
        Node parent;
        int rank;

        public Node(int stored_value){
            value = stored_value;
            parent = this;
            rank = 0;
        }
    }
    public DisjointSetLinkedList(int size) {
        for(int i=0;i<size;i++)
            structure.put(i,new Node(i));
        this.SIZE = size;
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(item >= SIZE || item < 0) throw new ItemOutOfRangeException();
        Node searched_root = structure.get(item);
        while (searched_root.parent != searched_root)
            searched_root = searched_root.parent;
        return searched_root.value;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= SIZE || item2 >= SIZE || item1 < 0 || item2 < 0) throw new ItemOutOfRangeException();
        if(item1 == item2) return;

        Node root1 = structure.get(findSet(item1));
        Node root2 = structure.get(findSet(item2));

        if(root1.rank > root2.rank){
            root2.parent = root1;
        }
        else
        {
            root1.parent = root2;
            if(root1.rank == root2.rank)
                root2.rank++;
        }
    }
}
