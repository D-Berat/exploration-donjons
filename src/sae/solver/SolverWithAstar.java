package sae.solver;

import sae.graph.Node;
import sae.dungeon.Coord;
import java.util.*;

public class SolverWithAstar extends SolverGeneric {

    private Map<Node, Node> pi;
    private Map<Node, Integer> cout;
    private Map<Node, Integer> heuristique;

    public SolverWithAstar(Node startingNode, Node endingNode) {
        super(startingNode, endingNode);
    }

    @Override
    protected void resolve() {
        int nvcout;
        int heuristiquetemp;
        boolean InClosed;
        boolean InOpen;
        
        this.pi = new HashMap<>();
        this.cout = new HashMap<>();
        this.heuristique = new HashMap<>();

        List<Node> closedList = new ArrayList<>();
        PriorityQueue<Node> openList = new PriorityQueue<>(Comparator.comparingInt(n -> heuristique.getOrDefault(n, Integer.MAX_VALUE)));

        cout.put(getStartingNode(), 0);
        heuristique.put(getStartingNode(), distance(getStartingNode(), getEndingNode()));
        pi.put(getStartingNode(), null);
        openList.add(getStartingNode());

        while (!openList.isEmpty()) {
            Node i = openList.poll();
            incSteps();

            if (i.equals(getEndingNode())) {
                reconstituerChemin(i);
                return;
            }

            for (Node v : i.neighbors()) {
                nvcout = cout.get(i) + 1;

                InClosed = closedList.contains(v);
                InOpen = openList.contains(v) && nvcout >= cout.getOrDefault(v, Integer.MAX_VALUE);

                if (!(InClosed || InOpen)) {
                    cout.put(v, nvcout);
                    
                    heuristiquetemp = nvcout + distance(v, getEndingNode());
                    heuristique.put(v, heuristiquetemp);
                    
                    pi.put(v, i);

                    if (openList.contains(v)) {
                        openList.remove(v);
                    }
                    openList.add(v);
                }
            }
            closedList.add(i);
        }
    }

    private void reconstituerChemin(Node u) {
        Node courant = u;
        while (courant != null) {
            getGraphSoluce().add(courant);
            courant = pi.get(courant);
        }
        Collections.reverse(getGraphSoluce().getSoluce());
    }

    private int distance(Node a, Node b) {
        Coord c1 = a.getCoord();
        Coord c2 = b.getCoord();
        return Math.abs(c1.getX() - c2.getX()) + Math.abs(c1.getY() - c2.getY());
    }

    @Override
    public String toString() {
        return "Astar";
    }
}