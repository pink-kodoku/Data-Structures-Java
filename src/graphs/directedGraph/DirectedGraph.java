package graphs.directedGraph;

import java.util.*;

public class DirectedGraph {
    private class Node {
        private String value;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
              "value='" + value + '\'' +
              '}';
        }
    }

    private Map<String, Node> nodes = new HashMap<>();
    private Map<Node, List<Node>> adjacencyList = new HashMap<>();


    public void addNode(String value) {
        Node node = new Node(value);
        nodes.putIfAbsent(value, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        if (fromNode == null) {
            throw new IllegalArgumentException();
        }

        Node toNode = nodes.get(to);
        if (toNode == null) {
            throw new IllegalArgumentException();
        }

        adjacencyList.get(fromNode).add(toNode);
    }

    public void removeNode(String value) {
        Node node = nodes.get(value);
        if (node == null) {
            return;
        }

        for (Node n : adjacencyList.keySet()) {
            adjacencyList.get(n).remove(node);
        }

        adjacencyList.remove(node);
        nodes.remove(node.value);
    }

    public void removeEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        if (fromNode == null || toNode == null) {
            return;
        }

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void depthFirstTraverse(String root) {
        Node node = nodes.get(root);
        if (node == null) {
            return;
        }
        depthFirstTraverse(node, new HashSet<>());
    }

    private void depthFirstTraverse(Node node, Set<Node> visited) {
        if (visited.contains(node)) {
            return;
        }

        System.out.println(node.value);
        visited.add(node);
        for (Node n : adjacencyList.get(node)) {
            if (!visited.contains(n)) {
                depthFirstTraverse(n, visited);
            }
        }
    }

    public void breadthFirstTraversal(String value) {
        Node node = nodes.get(value);
        if (node == null) {
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();

        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            if (visited.contains(current)) {
                continue;
            }

            System.out.println(current.value);
            visited.add(current);

            for (var neighbour : adjacencyList.get(current)) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                }
            }
        }
    }

    public List<String> topologicalSort(String value) {
        Node node = nodes.get(value);
        if (node == null) {
            return null;
        }

        Stack<Node> nodes = new Stack<>();

        topologicalSort(node, new HashSet<>(), nodes);

        List<String> list = new ArrayList<>();

        while (!nodes.isEmpty()) {
            list.add(nodes.pop().value);
        }

        return list;
    }

    private void topologicalSort(Node node, Set<Node> visited, Stack<Node> nodes) {
        if (visited.contains(node)) {
            return;
        }

        visited.add(node);
        for (Node n : adjacencyList.get(node)) {
            if (!visited.contains(n)) {
                topologicalSort(n, visited, nodes);
            }
        }

        nodes.push(node);
    }

    public boolean hasCycle() {
        Set<Node> all = new HashSet<>(nodes.values());
        Set<Node> visiting = new HashSet<>();
        Set<Node> visited = new HashSet<>();

        while (!all.isEmpty()) {
            Node current = all.iterator().next();
            if (hasCycle(current, all, visiting, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Node node, Set<Node> all, Set<Node> visiting, Set<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (Node n : adjacencyList.get(node)) {
            if (visited.contains(n)) {
                continue;
            }
            if (visiting.contains(n)) {
                return true;
            }

            if (hasCycle(n, all, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(node);
        visited.add(node);

        return false;
    }

    public void print() {
        for (Node source : adjacencyList.keySet()) {
            List<Node> targets = adjacencyList.get(source);
            if (!targets.isEmpty()) {
                System.out.println(source + " -> " + targets);
            }
        }
    }
}
