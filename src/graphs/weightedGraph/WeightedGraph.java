package graphs.weightedGraph;

import java.util.*;

public class WeightedGraph {

    public void shortestPath(String from, String to) {
        Node fromNode = nodes.get(from);
        if (fromNode == null) {
            throw new IllegalArgumentException();
        }
        Node toNode = nodes.get(to);
        if (toNode == null) {
            throw new IllegalArgumentException();
        }

        PriorityQueue<NodeEntry> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));
        Set<Node> visited = new HashSet<>();
        Map<Node, Integer> distances = new HashMap<>();
        Map<Node, Node> prevNodes = new HashMap<>();

        for (Node n : nodes.values()) {
            distances.put(n, Integer.MAX_VALUE);
        }

        distances.put(fromNode, 0);

        priorityQueue.add(new NodeEntry(fromNode, 0));

        while (!priorityQueue.isEmpty()) {
            NodeEntry current = priorityQueue.remove();
            Node currentNode = current.node;
            visited.add(currentNode);
            for (Edge edge : currentNode.edges) {
                if (!visited.contains(edge.to)) {
                    int newDistance = distances.get(currentNode) + edge.weight;
                    if (newDistance < distances.get(edge.to)) {
                        distances.replace(edge.to, newDistance);
                        prevNodes.put(edge.to, currentNode);
                        priorityQueue.add(new NodeEntry(edge.to, newDistance));
                    }
                }
            }
        }

        System.out.println(buildPath(toNode, prevNodes));
    }

    private class Node {
        private String value;
        private List<Edge> edges = new ArrayList<>();

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        public void addEdge(Node to, int weight) {
            edges.add(new Edge(this, to, weight));
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private class Edge {
        private Node from;
        private Node to;
        private int weight;

        public Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return from + " -> " + to;
        }
    }

    private class NodeEntry {
        private Node node;
        private int priority;

        public NodeEntry(Node node, int priority) {
            this.node = node;
            this.priority = priority;
        }
    }

    private HashMap<String, Node> nodes = new HashMap<>();

    public void addNode(String value) {
        nodes.putIfAbsent(value, new Node(value));
    }

    public void addEdge(String from, String to, int weight) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        if (fromNode == null || toNode == null) {
            throw new IllegalArgumentException();
        }

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }

    public Path getShortestPath(String from, String to) {
        Node fromNode = nodes.get(from);
        if (fromNode == null) {
            throw new IllegalArgumentException();
        }
        Node toNode = nodes.get(to);
        if (toNode == null) {
            throw new IllegalArgumentException();
        }
        Map<Node, Integer> distances = new HashMap<>();

        for (Node node : nodes.values()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.replace(fromNode, 0);

        Map<Node, Node> previousNodes = new HashMap<>();

        Set<Node> visited = new HashSet<>();

        PriorityQueue<NodeEntry> queue = new PriorityQueue<>(Comparator.comparingInt(ne -> ne.priority));

        queue.add(new NodeEntry(fromNode, 0));

        while (!queue.isEmpty()) {
            Node current = queue.remove().node;
            visited.add(current);
            System.out.println(current.value);
            for (var edge : current.getEdges()) {
                if (visited.contains(edge.to)) {
                    continue;
                }
                int newDistance = distances.get(current) + edge.weight;
                if (newDistance < distances.get(toNode)) {
                    distances.replace(edge.to, newDistance);
                    previousNodes.put(edge.to, current);
                    queue.add(new NodeEntry(edge.to, newDistance));
                }
            }
        }


        return buildPath(toNode, previousNodes);
    }

    private Path buildPath(Node toNode, Map<Node, Node> previousNodes) {
        Stack<Node> stack = new Stack<>();
        stack.push(toNode);
        Node previous = previousNodes.get(toNode);
        while (previous != null) {
            stack.push(previous);
            previous = previousNodes.get(previous);
        }

        Path path = new Path();

        while (!stack.isEmpty()) {
            path.addNode(stack.pop().value);
        }

        return path;
    }

    public boolean hasCycle() {
        Set<Node> visited = new HashSet<>();
        for (var node : nodes.values()) {
            if (!visited.contains(node) && hasCycle(node, null, visited)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycle(Node node, Node parent, Set<Node> visited) {
        visited.add(node);

        for (var edge : node.getEdges()) {
            if (edge.to == parent) {
                continue;
            }
            if (visited.contains(edge.to) || hasCycle(edge.to, node, visited)) {
                return true;
            }
        }

        return false;
    }


    public void print() {
        for (var node : nodes.values()) {
            var edges = node.getEdges();
            if (!edges.isEmpty()) {
                System.out.println(node + " is connected to " + edges);
            }
        }
    }
}
