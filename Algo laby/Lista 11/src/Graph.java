import java.util.*;

public class Graph<T> {
    private HashMap<Integer,T> indexToValue = new HashMap<>();
    private HashMap<T,Integer> valueToIndex = new HashMap<>();
    private int ammountOfVerts;
    private int[][] adjMatrix;


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

        for (int index = 0;index < ammountOfVerts;index++)
// koment bo albo bedziemy zliczac droge tez dla startNode i usuniemy go na koniec, albo bedziemy przekazywac go do kazdej F
            // if (indexToValue.get(index) != startNode)
            {
                endNode = indexToValue.get(index);
                shortestPathsMap.put(endNode,Integer.MAX_VALUE);
            }

        boolean[] visited = new boolean[ammountOfVerts];
        Arrays.fill(visited,false);
        T currentNode = startNode;
        updateDistancesForStrartNode(shortestPathsMap,currentNode,visited);
        currentNode = findMinDis(shortestPathsMap,visited);
        while(currentNode != null){
            updateDistances(shortestPathsMap,currentNode,visited);
            currentNode = findMinDis(shortestPathsMap,visited);
        }
        shortestPathsMap.remove(startNode);
        return shortestPathsMap;
    }

    private T findMinDis(Map<T, Integer> shortestPathsMap, boolean[] visited) {
        T min = null;
            for (int i=0;i<ammountOfVerts;i++){
                if(!visited[i] && (min == null || shortestPathsMap.get(indexToValue.get(i))<shortestPathsMap.get(min))){
                    min = indexToValue.get(i);
                }
            }
            return min;
    }

    private void updateDistances(Map<T, Integer> shortestPathsMap,T currentNode, boolean[] visited) {
        int indexOfVisited = valueToIndex.get(currentNode);
        visited[indexOfVisited] = true;
        int weightBetweenVisitedAndDest;
        for (int destinationIndex = 0;destinationIndex<ammountOfVerts;destinationIndex++){

            weightBetweenVisitedAndDest = adjMatrix[indexOfVisited][destinationIndex];

            if (weightBetweenVisitedAndDest != 0 &&
                    shortestPathsMap.get(currentNode) + weightBetweenVisitedAndDest
                            < shortestPathsMap.get(indexToValue.get(destinationIndex)))
                shortestPathsMap.replace(indexToValue.get(destinationIndex),shortestPathsMap.get(currentNode) + weightBetweenVisitedAndDest);


        }
    }
    private void updateDistancesForStrartNode(Map<T, Integer> shortestPathsMap,T currentNode, boolean[] visited) {
        int indexOfVisited = valueToIndex.get(currentNode);
        visited[indexOfVisited] = true;
        int weightBetweenVisitedAndDest;
        for (int destinationIndex = 0;destinationIndex<ammountOfVerts;destinationIndex++){

            weightBetweenVisitedAndDest = adjMatrix[indexOfVisited][destinationIndex];

            if (weightBetweenVisitedAndDest != 0)
                shortestPathsMap.replace(indexToValue.get(destinationIndex),weightBetweenVisitedAndDest);


        }
    }

}
