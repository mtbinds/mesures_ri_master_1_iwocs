
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

public class Scenario3 implements Scenario {

    private SIS sis;
    private List<Node> nodes;
    private Collection<Node> group0;
    private Collection<Node> group1;

    public Scenario3(Graph graph) {
        this.group0 = Toolkit.randomNodeSet(graph, (graph.getNodeCount() / 2));
        this.group1 = new HashSet<>();
        for (Node n : group0) {
            Edge e = Toolkit.randomEdge(n);
            if (e != null) {
                Node u = e.getOpposite(n);
                this.group1.add(u);
            }
        }
        List<Node> susceptibleNodes = this.nodes = new ArrayList<>(graph.getNodeSet());
        susceptibleNodes.removeAll(this.group1);
        this.sis = new SIS(1 / 7D, 1 / 14D, susceptibleNodes);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(this.nodes);
    }

    public List<Node> getGroup0() {
        return new ArrayList<>(this.group0);
    }

    public List<Node> getGroup1() {
        return new ArrayList<>(this.group1);
    }

    @Override
    public List<Collection<Node>> propagation(int days) {
        return this.sis.propagation(days);
    }
}