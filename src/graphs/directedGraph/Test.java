package graphs.directedGraph;

public class Test {
    public static void main(String[] args) {
        DirectedGraph directedGraph = new DirectedGraph();

        directedGraph.addNode("a");
        directedGraph.addNode("b");
        directedGraph.addNode("c");
        directedGraph.addNode("d");
        directedGraph.addNode("e");

        directedGraph.addEdge("e", "a");
        directedGraph.addEdge("a", "b");
        directedGraph.addEdge("b", "c");
        directedGraph.addEdge("b", "d");

        directedGraph.breadthFirstTraversal("a");
    }
}
