import org.w3c.dom.Node;

import java.util.HashMap;

public class DisjointSetForest implements IDisjointSetStructure {
    Node[] structure;
    private final int SIZE;


    public DisjointSetForest(int size) {
        this.SIZE = size;
        structure = new Node[size];
        for (int i=0;i<size;i++)
            structure[i] = new Node(i);
    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(SIZE<=item || 0 > item) throw new ItemOutOfRangeException();
        Node searched_root = structure[item];
        while (searched_root.parent_index != item){
            item = searched_root.parent_index;
            searched_root = structure[searched_root.parent_index];
        }
        return item;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= SIZE || item2 >= SIZE || item1 < 0 || item2 < 0) throw new ItemOutOfRangeException();
        if(item1 == item2) return;
        Node node1 = structure[findSet(item1)];
        Node node2 = structure[findSet(item2)];

        if(node1.rank > node2.rank){
            node2.parent_index = item1;
        }
        else
        {
            node1.parent_index = item2;
            if(node1.rank == node2.rank)
                node2.rank++;
        }
    }
    public class Node{
        int rank = 0;
        int parent_index;
        public Node(int self_index){
            parent_index = self_index;
        }
    }
}
