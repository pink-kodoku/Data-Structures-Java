package graphs.weightedGraph;

public class Test {
    public static void main(String[] args) {
        WeightedGraph weightedGraph = new WeightedGraph();

        weightedGraph.addNode("a");
        weightedGraph.addNode("b");
        weightedGraph.addNode("c");

        weightedGraph.addEdge("a", "b", 1);
        weightedGraph.addEdge("b", "c", 2);
        weightedGraph.addEdge("a", "c", 10);

        weightedGraph.print();

        System.out.println(weightedGraph.getShortestPath("a", "c"));
    }
}
