package graphs.weightedGraph;

public class Test {
    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();

        weightedGraph.addNode("a");
        weightedGraph.addNode("c");
        weightedGraph.addNode("d");
        weightedGraph.addNode("e");
        weightedGraph.addNode("g");

        weightedGraph.addEdge("a", "c", 1);
        weightedGraph.addEdge("c", "d", 1);
        weightedGraph.addEdge("a", "e", 3);
        weightedGraph.addEdge("a", "d", 8);
        weightedGraph.addEdge("e", "g", 2);
        weightedGraph.addEdge("d", "g", 3);

        weightedGraph.shortestPath("a", "d");

    }
}
