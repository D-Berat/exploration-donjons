package sae.solver;

import sae.graph.*;

public interface Solver {
    void solve();

    GraphSoluce getGraphSoluce();

    int getSteps();
}