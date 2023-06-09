import java.util.*;


public class Graph<T> {

    private int size;
    private ArrayList<T> verticesList;
    private int[][] adjMatrix;

    public Graph(List<Edge<T>> edges) {
        HashSet<T> vertices = new HashSet<>();
        for (Edge<T> edge : edges) {
            vertices.add(edge.getSource());
            vertices.add(edge.getDestination());
        }
        size = vertices.size();

        adjMatrix = new int[size][size];

        verticesList = new ArrayList<>();
        for (T o:vertices){
            verticesList.add(o);
        }


        for (Edge<T> edge : edges) {
            int srcIndex = verticesList.indexOf(edge.getSource());
            int destIndex = verticesList.indexOf(edge.getDestination());
            adjMatrix[srcIndex][destIndex] = edge.getWeight();
        }

    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        if(!verticesList.contains(startNode))    throw new NoSuchElementException();

        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[size];
        int startIndex;

        startIndex = verticesList.indexOf(startNode);

        depthFirstHelper(startIndex, visited, result);
        return result.toString();
    }

    private void depthFirstHelper(int vertexIndex, boolean[] visited, StringBuilder result) {
        visited[vertexIndex] = true;

        if(result.isEmpty())
            result.append(verticesList.get(vertexIndex));
        else
            result.append(", ").append(verticesList.get(vertexIndex));

        for (int neighborIndex = 0; neighborIndex < size; neighborIndex++) {
            int minimalWeightEdgeIndex = getMinimalWeightIndex(vertexIndex, visited);

            if (minimalWeightEdgeIndex != Integer.MAX_VALUE)
                depthFirstHelper(minimalWeightEdgeIndex, visited, result);
        }
    }

    public int getMinimalWeightIndex(int vertexIndex, boolean[] visited){
        int minimalWeightEdge = Integer.MAX_VALUE;
        int minimalWeightEdgeIndex = Integer.MAX_VALUE;

        for(int i=0; i<size; i++){
            if(adjMatrix[vertexIndex][i] > 0
                    && adjMatrix[vertexIndex][i] < minimalWeightEdge
                    && !visited[i]){
                minimalWeightEdge = adjMatrix[vertexIndex][i];
                minimalWeightEdgeIndex = i;
            }
        }
        return minimalWeightEdgeIndex;
    }


    public String breadthFirst(T startNode) throws NoSuchElementException {
        if(!verticesList.contains(startNode))   throw new NoSuchElementException();

        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[size];

        int startIndex = verticesList.indexOf(startNode);
        Queue<Integer> queue = new LinkedList<>();
        visited[startIndex] = true;
        queue.offer(startIndex);

        while (!queue.isEmpty()) {
            int vertexIndex = queue.poll();

            if(result.isEmpty())
                result.append(verticesList.get(vertexIndex));
            else
                result.append(", ").append(verticesList.get(vertexIndex));

            for (int i = 0; i < size; i++) {
                int minimalWeightEdgeIndex = getMinimalWeightIndex(vertexIndex ,visited);
                if (minimalWeightEdgeIndex != Integer.MAX_VALUE){
                    visited[minimalWeightEdgeIndex] = true;
                    queue.offer(minimalWeightEdgeIndex);
                }
            }
        }

        return result.toString();
    }

    public int connectedComponents() throws ItemOutOfRangeException {
        DisjointSetForest disjointSet = new DisjointSetForest(size);
        int numOfUnions = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (adjMatrix[i][j] > 0 && disjointSet.findSet(i) != disjointSet.findSet(j)) {
                    disjointSet.union(i, j);
                    numOfUnions++;
                }
            }
        }
        return size-numOfUnions;
    }
}
