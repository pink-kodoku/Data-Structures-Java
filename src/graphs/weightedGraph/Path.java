package graphs.weightedGraph;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<String> nodes = new ArrayList<>();

    public void addNode(String node) {
        nodes.add(node);
    }

    @Override
    public String toString() {
        return nodes.toString();
    }
}
