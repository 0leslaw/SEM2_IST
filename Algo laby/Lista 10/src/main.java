import java.util.ArrayList;
import java.util.LinkedList;

public class main {
    public static void main(String[] args) {


        var edges = new LinkedList<Edge<String>>();
        edges.add(new Edge<>("zero", "two", 2));
        edges.add(new Edge<>("zero", "four", 5));
        edges.add(new Edge<>("four", "two", 3));
        edges.add(new Edge<>("four", "one", 6));
        edges.add(new Edge<>("two", "one", 4));
        edges.add(new Edge<>("four", "three", 1));
        edges.add(new Edge<>("three", "one", 3));
        edges.add(new Edge<>("three", "five", 4));
        edges.add(new Edge<>("five", "zero", 3));

        edges.add(new Edge<>("six", "seven", 3));
        edges.add(new Edge<>("seven", "eight", 2));
        edges.add(new Edge<>("eight", "six", 4));

        edges.add(new Edge<>("nine", "ten", 2));
        Graph<String> graf = new Graph<>(edges);
        System.out.println(graf.breadthFirst("three"));
    }
}
