import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph<String> graph = new Graph<>(Arrays.asList(
                new Edge<>("a", "b", 2),
                new Edge<>("a", "c", 5),
                new Edge<>("b", "d", 3),
                new Edge<>("b", "e", 4),
                new Edge<>("c", "d", 5),
                new Edge<>("c", "f", 6),
                new Edge<>("d", "e", 3),
                new Edge<>("d", "f", 1),
                new Edge<>("e", "f", 4),
                new Edge<>("e", "g", 8),
                new Edge<>("e", "h", 2),
                new Edge<>("f", "g", 7),
                new Edge<>("g", "h", 1)
        ));
        System.out.println(graph.findShortestRoute("a","h"));

    }
}
