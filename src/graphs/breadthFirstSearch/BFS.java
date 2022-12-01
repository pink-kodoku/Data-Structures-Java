package graphs.breadthFirstSearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private class Vertex {
        private String value;
        private boolean visited;
        private List<Vertex> edges = new ArrayList<>();

        public Vertex(String value) {
            this.value = value;
        }

        public void addEdge(Vertex vertex) {
            edges.add(vertex);
        }

        @Override
        public String toString() {
            return "Vertex{" +
              "value='" + value + '\'' +
              '}';
        }
    }

    public void main() {
        Vertex v1 = new Vertex("a");
        Vertex v2 = new Vertex("b");
        Vertex v3 = new Vertex("f");
        Vertex v4 = new Vertex("g");
        Vertex v5 = new Vertex("h");

        v1.addEdge(v2);
        v1.addEdge(v3);
        v1.addEdge(v4);
        v4.addEdge(v5);

        bfs(v1);
    }

    public void bfs(Vertex root) {
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            System.out.println(current.value);
            current.visited = true;

            for (Vertex n : current.edges) {
                if (!n.visited) {
                    n.visited = true;
                    queue.add(n);
                }
            }
        }
    }
}
