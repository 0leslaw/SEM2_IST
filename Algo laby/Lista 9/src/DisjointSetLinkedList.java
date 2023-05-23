import java.util.HashMap;

public class DisjointSetLinkedList implements IDisjointSetStructure {
    private HashMap<Integer,Node> structure = new HashMap<>();
    private final int SIZE;

    public class Node{
        int value;
        Node parent;
        Node next;
        Node last;
        int length;

        public Node(int stored_value){
            value = stored_value;
            parent = this;
            length = 1;
            next = null;
            last = this;
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

        return searched_root.parent.value;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= SIZE || item2 >= SIZE || item1 < 0 || item2 < 0) throw new ItemOutOfRangeException();
        if(item1 == item2) return;

        Node root1 = structure.get(findSet(item1));
        Node root2 = structure.get(findSet(item2));

        if(root1.length < root2.length){
            joinFromRoot(root1,root2);
        }
        else
        {
            joinFromRoot(root2,root1);
        }
    }
    public void joinFromRoot(Node superiorRoot, Node inferiorRoot){
        superiorRoot.last.next = inferiorRoot;
        while (inferiorRoot.next != null){
            inferiorRoot.parent = superiorRoot;
            inferiorRoot = inferiorRoot.next;
            superiorRoot.length++;
        }
        inferiorRoot.parent = superiorRoot;
        superiorRoot.length++;
        superiorRoot.last = inferiorRoot;
    }
}
