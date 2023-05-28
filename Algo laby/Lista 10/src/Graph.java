import java.util.*;


public class Graph<T> {

    private final int size;
    private final int[][] adjMatrix;
    private final int[][] weightMatrix;
    private final Map<T, Integer> vertexMap;
    private final Map<Integer, T> reverseMap;

    public Graph(List<Edge<T>> edges) {
        // TODO: Przekształcenie krawędzi na macierz sąsiedztwa, odwzorowanie wierzchołka na indeks, itp.
        Set<T> vertices = new HashSet<>();
        for (Edge<T> edge : edges) {
            vertices.add(edge.getSource());
            vertices.add(edge.getDestination());
        }
        size = vertices.size();

        vertexMap = new HashMap<>();
        reverseMap = new HashMap<>();
        adjMatrix = new int[size][size];
        weightMatrix = new int[size][size];

        int index = 0;
        for (T vertex : vertices) {
            vertexMap.put(vertex, index);
            reverseMap.put(index, vertex);
            index++;
        }

        for (Edge<T> edge : edges) {
            int srcIndex = vertexMap.get(edge.getSource());
            int destIndex = vertexMap.get(edge.getDestination());
            adjMatrix[srcIndex][destIndex] = 1;
            weightMatrix[srcIndex][destIndex] = edge.getWeight();
        }

    }

    public String depthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wgłąb od podanego wierzchołka
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[size];
        int startIndex;

        if(vertexMap.containsKey(startNode))
            startIndex = vertexMap.get(startNode);
        else
            throw new NoSuchElementException();

        depthFirstHelper(startIndex, visited, result);
        return result.toString();
    }

    private void depthFirstHelper(int vertexIndex, boolean[] visited, StringBuilder result) {
        visited[vertexIndex] = true;

        if(result.isEmpty())
            result.append(reverseMap.get(vertexIndex));
        else
            result.append(", ").append(reverseMap.get(vertexIndex));

        for (int neighborIndex = 0; neighborIndex < size; neighborIndex++) {
            int minimalWeightEdgeIndex = findMinimalWeightIndex(vertexIndex, visited);

            if (minimalWeightEdgeIndex != Integer.MAX_VALUE)
                depthFirstHelper(minimalWeightEdgeIndex, visited, result);
        }
    }

    public int findMinimalWeightIndex(int vertexIndex, boolean[] visited){
        int minimalWeightEdge = Integer.MAX_VALUE;
        int minimalWeightEdgeIndex = Integer.MAX_VALUE;

        for(int i=0; i<size; i++){
            if(adjMatrix[vertexIndex][i] == 1
                    && weightMatrix[vertexIndex][i] < minimalWeightEdge
                    && weightMatrix[vertexIndex][i] != 0
                    && !visited[i]){
                minimalWeightEdge = weightMatrix[vertexIndex][i];
                minimalWeightEdgeIndex = i;
            }
        }
        return minimalWeightEdgeIndex;
    }


    public String breadthFirst(T startNode) throws NoSuchElementException {
        // TODO: Przejście przez graf metodą najpierw-wszerz od podanego wierzchołka
        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[size];

        int startIndex;
        if(vertexMap.containsKey(startNode))
            startIndex = vertexMap.get(startNode);
        else
            throw new NoSuchElementException();

        Queue<Integer> queue = new LinkedList<>();
        visited[startIndex] = true;
        queue.offer(startIndex);

        while (!queue.isEmpty()) {
            int vertexIndex = queue.poll();

            if(result.isEmpty())
                result.append(reverseMap.get(vertexIndex));
            else
                result.append(", ").append(reverseMap.get(vertexIndex));

            for (int i = 0; i < size; i++) {
                int minimalWeightEdgeIndex = findMinimalWeightIndex(vertexIndex ,visited);
                if (minimalWeightEdgeIndex != Integer.MAX_VALUE){
                    visited[minimalWeightEdgeIndex] = true;
                    queue.offer(minimalWeightEdgeIndex);
                }
            }
        }

        return result.toString();
    }

    public int connectedComponents() throws ItemOutOfRangeException {
        // TODO: Liczba składowych spójnych grafu
        DisjointSetForest disjointSet = new DisjointSetForest(size);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (adjMatrix[i][j] == 1)
                    disjointSet.union(i, j);
            }
        }
        return disjointSet.countSets();
    }
}
