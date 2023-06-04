import java.util.*;

public class Graph<T> {
    private int[][] adjMatrix;
    private HashMap<Integer,T> indexToValue = new HashMap<>();
    private HashMap<T,Integer> valueToIndex = new HashMap<>();
    private int ammountOfVerts;


    public Graph(List<Edge<T>> edges) {
        HashSet<T> set = new HashSet<>();
        for(Edge edge:edges){
            set.add((T) edge.getNode1());
            set.add((T) edge.getNode2());
        }
        int index=0;
        for (T o:set) {
            indexToValue.put(index,o);
            valueToIndex.put(o,index);
            index++;
        }
        adjMatrix = new int[set.size()][set.size()];
        ammountOfVerts = adjMatrix.length;

        for (Edge edge:edges){
            adjMatrix[valueToIndex.get(edge.getNode1())][valueToIndex.get(edge.getNode2())] = edge.getDistance();
            adjMatrix[valueToIndex.get(edge.getNode2())][valueToIndex.get(edge.getNode1())] = edge.getDistance();
        }
    }

    public Map<T, Integer> calculateShortestPaths(T startNode) throws NoSuchElementException {
        if (!valueToIndex.containsKey(startNode)) throw new NoSuchElementException();

        Map<T,Integer> shortestPathsMap = new HashMap<>(ammountOfVerts-1);

        T endNode;
        int distance;
        for (int index = 0;index < ammountOfVerts;index++)
            if (indexToValue.get(index) != startNode){

                endNode = indexToValue.get(index);
                distance = dijkstraDistance(startNode,endNode);

                shortestPathsMap.put(endNode,distance);
            }

        return shortestPathsMap;
    }
    public void updateDistances(Node visitedRoot,HashMap<Integer,Node> indexToNode,int indexOfVisited){
        visitedRoot.visited = true;
        int weightBetweenVisitedAndDest;
        for (int destinationIndex = 0;destinationIndex<ammountOfVerts;destinationIndex++){

            weightBetweenVisitedAndDest = adjMatrix[indexOfVisited][destinationIndex];
            Node destinationNode = indexToNode.get(destinationIndex);

            if (weightBetweenVisitedAndDest>0 && visitedRoot.distance + weightBetweenVisitedAndDest < destinationNode.distance){
                destinationNode.distance = visitedRoot.distance + weightBetweenVisitedAndDest;
                destinationNode.linkedToThisWhenShorteningDistance = visitedRoot;
            }
        }
    }
    public Node findMinDistance(HashMap<Integer,Node> indexToNode,HashMap<Node,Integer> NodeToIndex){
        //dajac tu nulla upewniam sie ze pierwszy min nie jest visited
        Node min = new Node(null);
        for (int index = 0;index < ammountOfVerts;index++)
            if (min.distance > indexToNode.get(index).distance && !indexToNode.get(index).visited)
                min = indexToNode.get(index);

        return min;
    }
    public int dijkstraDistance(T startLabel,T endLabel){
        int startIndex = valueToIndex.get(startLabel);
        int endIndex = valueToIndex.get(endLabel);

        HashMap<Integer,Node> indexToNode = new HashMap<>(ammountOfVerts);
        HashMap<Node,Integer> NodeToIndex = new HashMap<>(ammountOfVerts);

        for (int index = 0;index < ammountOfVerts;index++){
            Node temp = new Node(indexToValue.get(index));
            indexToNode.put(index,temp);
            NodeToIndex.put(temp,index);
        }

        Node currentNode = indexToNode.get(startIndex);
        currentNode.distance = 0;
        while (indexToNode.get(endIndex) != currentNode){
            updateDistances(currentNode,indexToNode,NodeToIndex.get(currentNode));
            currentNode = findMinDistance(indexToNode,NodeToIndex);
        }
        return indexToNode.get(endIndex).distance;
    }
    public class Node{
        private Node linkedToThisWhenShorteningDistance = null;
        private int distance = Integer.MAX_VALUE;
        private boolean visited = false;
        private T label;

        public Node(T label) {
            this.label = label;
        }
    }
}
