package sae.graph;

import java.util.List;
import java.util.ArrayList;

public class GraphSoluce {
    private List<Node> soluce;

    public GraphSoluce() {
        this.soluce = new ArrayList<>();
    }

    public void add(Node node) {
        this.soluce.add(node);
    }

    public List<Node> getSoluce() {
        return this.soluce;
    }
}