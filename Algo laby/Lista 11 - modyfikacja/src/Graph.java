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

    public Integer calculateShortestPath(T startNode, T endNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótszą ścieżkę pomiędzy wierzchołkami w grafie
        if(!valueToIndex.containsKey(startNode) || !valueToIndex.containsKey(endNode)) throw new NoSuchElementException();
        return dijkstraDistance(startNode,endNode);
    }
    public Map<Integer,T> findShortestRoute(T startNode, T endNode) throws NoSuchElementException {
        // TODO: Wylicz najkrótszą ścieżkę pomiędzy wierzchołkami w grafie
        if(!valueToIndex.containsKey(startNode) || !valueToIndex.containsKey(endNode)) throw new NoSuchElementException();
        return dijkstraRoute(startNode,endNode);
    }
    private void updateDistances(Node visitedRoot,HashMap<Integer,Node> indexToNode,int indexOfVisited){
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
    private Node findMinDistance(HashMap<Integer,Node> indexToNode,HashMap<Node,Integer> NodeToIndex){
        //dajac tu nulla upewniam sie ze pierwszy min nie jest visited
        Node min = new Node(null);
        for (int index = 0;index < ammountOfVerts;index++)
            if (min.distance > indexToNode.get(index).distance && !indexToNode.get(index).visited)
                min = indexToNode.get(index);

        return min;
    }
    private int dijkstraDistance(T startLabel,T endLabel){
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
    private Map<Integer,T> dijkstraRoute(T startLabel,T endLabel){
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
        //reversing the order
        int sizeOfMap = 0;
        while (currentNode != null){
            sizeOfMap++;
            currentNode = currentNode.linkedToThisWhenShorteningDistance;
        }
        Map<Integer,T> routeMap = new HashMap<>(sizeOfMap);
        currentNode = indexToNode.get(endIndex);

        while (currentNode != null){
            routeMap.put(sizeOfMap-1, currentNode.label);
            currentNode = currentNode.linkedToThisWhenShorteningDistance;
            sizeOfMap--;
        }
        return routeMap;
    }
    private class Node{
        private Node linkedToThisWhenShorteningDistance = null;
        private int distance = Integer.MAX_VALUE;
        private boolean visited = false;
        private T label;

        public Node(T label) {
            this.label = label;
        }
    }
}
