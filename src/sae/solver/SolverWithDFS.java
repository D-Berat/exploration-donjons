package sae.solver;

import java.util.*;
import sae.graph.*;

public class SolverWithDFS extends SolverGeneric {

    private Map<Node, Node> pi;
    private List<Node> visite;
    private boolean isfinish;

    public SolverWithDFS(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
    }

    @Override
    protected void resolve() {
        this.pi = new HashMap<>();
        this.visite = new ArrayList<>();
        this.isfinish = false;

        if (!visite.contains(getStartingNode())) {
            pi.put(getStartingNode(), null);
            Prof(getStartingNode());
        }

        if (isfinish) {
            Node dernier = getEndingNode();
            while (dernier != null) {
                getGraphSoluce().add(dernier);
                dernier = pi.get(dernier);
            }
            Collections.reverse(getGraphSoluce().getSoluce());
        }
    }

    public void Prof(Node i) {
        if (isfinish) return;
        incSteps();

        visite.add(i);

        if (i.equals(getEndingNode())) {
            isfinish = true;
            return;
        }

        for (Node v : i.neighbors()) {
            
            if (!visite.contains(v)) {
                pi.put(v, i);
                Prof(v);
            }
        }
    }

    public String toString(){
        return "DFS";
    }
}