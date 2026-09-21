package sae.solver;

import sae.graph.Node;
import java.util.*;

public class SolverWithBFS extends SolverGeneric {

    private Map<Node, Node> pi;
    private List<Node> visite;
    private boolean trouve;

    public SolverWithBFS(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
    }

    @Override
    protected void resolve() {
        this.pi = new HashMap<>();
        this.visite = new ArrayList<>();
        this.trouve = false;
        Queue<Node> file = new LinkedList<>();


        this.visite.add(getStartingNode());
        file.add(getStartingNode());
        this.pi.put(getStartingNode(), null);

        while (!file.isEmpty()) {
            Node i = file.poll();
            incSteps();

            if (i.equals(getEndingNode())) {
                this.trouve = true;
                break;
            }

            for (Node v : i.neighbors()) {
                if (!this.visite.contains(v)) {
                    this.pi.put(v, i);
                    this.visite.add(v);
                    file.add(v);
                }
            }
        }

        if (this.trouve) {
            Node courant = getEndingNode();
            while (courant != null) {
                getGraphSoluce().add(courant);
                courant = this.pi.get(courant);
            }
            Collections.reverse(getGraphSoluce().getSoluce());
        }
    }

    @Override
    public String toString() {
        return "BFS";
    }
}