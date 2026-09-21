package sae.graph;

import java.util.Set;
import java.util.HashSet;
import sae.dungeon.Coord;

public class Node {

    private String name;
    private Set<Node> neighbors;
    private Coord coord;

    public Node(String name, Coord coord) {
        this.name = name;
        this.coord = coord;
        this.neighbors = new HashSet<>();
    }

    public Set<Node> neighbors() {
        return this.neighbors;
    }

    public void addNeigbour(Node node) {
        this.neighbors.add(node);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Node ").append(name).append(" ").append(coord.toString()).append(" [voisins: ");

        for (Node neighbor : neighbors) {
            builder.append(neighbor.getName()).append(" ");
        }

        builder.append("]");
        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Node))
            return false;
        Node other = (Node) obj;
        return name.equals(other.name);
    }
}
