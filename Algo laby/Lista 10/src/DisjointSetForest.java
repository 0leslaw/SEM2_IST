public class DisjointSetForest implements IDisjointSetStructure {
    Node[] structure;
    private final int SIZE;


    public DisjointSetForest(int size) {
        this.SIZE = size;
        structure = new Node[size];
        for (int i=0;i<size;i++)
            structure[i] = new Node(i);
    }
//    public int countSets() throws ItemOutOfRangeException {
//        int numOfSets=0;
//
//            for (int j=0;j< structure.length;j++)
//                if (findSet(0) != findSet(j))
//                    numOfSets++;
//        return numOfSets;
//    }

    @Override
    public int findSet(int item) throws ItemOutOfRangeException {
        if(SIZE<=item || 0 > item) throw new ItemOutOfRangeException();
        Node searched_root = structure[item];

        return searched_root.parent_index;
    }

    @Override
    public void union(int item1, int item2) throws ItemOutOfRangeException {
        if(item1 >= SIZE || item2 >= SIZE || item1 < 0 || item2 < 0) throw new ItemOutOfRangeException();
        if(findSet(item1) == findSet(item2)) return;
        Node node1 = structure[findSet(item1)];
        Node node2 = structure[findSet(item2)];

        if(node1.rank > node2.rank){
            for (Node node : structure)
                if(node.parent_index == node2.parent_index)
                    node.parent_index = node1.parent_index;

        }
        else
        {
            for (Node node : structure)
                if(node.parent_index == node1.parent_index)
                    node.parent_index = node2.parent_index;

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

        @Override
        public String toString() {
            return "Node{" +
                    "rank=" + rank +
                    ", parent_index=" + parent_index +
                    '}';
        }
    }
}
