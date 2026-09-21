package sae.solver;

import sae.graph.*;

public abstract class SolverGeneric implements Solver {

    private Node startingNode;
    private Node endingNode;
    private int steps;
    private GraphSoluce graphSoluce;

    public SolverGeneric(Node startingNode, Node endingNode) {
        this.startingNode = startingNode;
        this.endingNode = endingNode;
        this.graphSoluce = new GraphSoluce();
        this.steps = 0;
    }

    @Override
    public GraphSoluce getGraphSoluce() {
        return this.graphSoluce;
    }

    @Override
    public int getSteps() {
        return this.steps;
    }

    public void incSteps() {
        this.steps += 1;
    }

    public Node getStartingNode() {
        return this.startingNode;
    }

    public Node getEndingNode() {
        return this.endingNode;
    }

    @Override
    public void solve() {
        initializeResolution();
        resolve();
    }

    protected abstract void resolve();

    private void initializeResolution() {
        this.steps = 0;
        this.graphSoluce = new GraphSoluce();
    }
}