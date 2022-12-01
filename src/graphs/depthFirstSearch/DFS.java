package graphs.depthFirstSearch;

import graphs.breadthFirstSearch.BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {
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
        Vertex v6 = new Vertex("c");
        Vertex v7 = new Vertex("d");
        Vertex v8 = new Vertex("e");

        v1.addEdge(v2);
        v1.addEdge(v3);
        v1.addEdge(v4);
        v4.addEdge(v5);
        v2.addEdge(v6);
        v2.addEdge(v7);
        v7.addEdge(v8);

        dfs(v1);
    }

    private void dfs(Vertex root) {
        System.out.println(root.value);
        for (Vertex v : root.edges) {
            if (!v.visited) {
                v.visited = true;
                dfs(v);
            }
        }
    }

}
