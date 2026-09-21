package sae.graph;

import java.util.Set;
import java.util.HashSet;

public class Graph {

    private Set<Node> nodes;

    public Graph() {
        this.nodes = new HashSet<>();
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void addEdge(Node n1, Node n2) {
        n1.addNeigbour(n2);
        n2.addNeigbour(n1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node : nodes) {
            builder.append(node.toString()).append("\n");
        }
        return builder.toString();
    }
}