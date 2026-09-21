package sae;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import sae.dungeon.Dungeon;
import sae.dungeon.DungeonBuilder;
import sae.dungeon.DungeonSoluce;
import sae.graph.GraphSoluce;
import sae.graph.Node;
import sae.solver.Solver;
import sae.solver.SolverWithAstar;
import sae.solver.SolverWithBFS;
import sae.solver.SolverWithDFS;
import sae.transform.Dungeon2Graph;

public class Scenarios {

	private static final int NB_ATTEMPTS = 10000;
	private static int cptDungeon = 1;

	private static final Logger logger = Logger.getLogger("mon logger");

	public static void main(String[] args) {

		initLogger();

		DungeonBuilder builder = new DungeonBuilder();

		solveDungeon(builder.createFirstDungeon());
		solveDungeon(builder.createSecondDungeon());
		solveDungeon(builder.createThirdDungeon());
		solveDungeon(builder.createFourthDungeon());
		solveDungeon(builder.createFifthDungeon());
		solveDungeon(builder.createSixthDungeon());
		solveDungeon(builder.createSeventhDungeon());
		solveDungeon(builder.createEighthDungeon());
	}

	private static void initLogger() {

		Logger rootLogger = Logger.getLogger("");
		rootLogger.setLevel(Level.OFF);
		for (Handler handler : rootLogger.getHandlers()) {
			rootLogger.removeHandler(handler);
		}

		ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.ALL);
		handler.setFormatter(new SimpleFormatter() {
			@Override
			public synchronized String format(LogRecord lr) {
				return String.format("%s%n", lr.getMessage());
			}
		});

		logger.addHandler(handler);
		logger.setLevel(Level.INFO);
	}

	private static void solveDungeon(Dungeon dungeon) {

		logger.info("***************************");
		logger.info(() -> "   Résolution du donjon " + cptDungeon++);
		logger.info("---------------------------");

		Dungeon2Graph mapping = new Dungeon2Graph(dungeon);

		Node nodeA = mapping.mappedNode(dungeon.getRoomA());
		Node nodeB = mapping.mappedNode(dungeon.getRoomB());

		solveWithSolver(mapping, new SolverWithDFS(nodeA, nodeB));
		solveWithSolver(mapping, new SolverWithBFS(nodeA, nodeB));
		solveWithSolver(mapping, new SolverWithAstar(nodeA, nodeB));

	}

	private static void solveWithSolver(Dungeon2Graph mapping, Solver solver) {
		logger.info(() -> "Résolution avec " + solver);

		long startingTime = System.currentTimeMillis();

		for (int i = 0; i < NB_ATTEMPTS; i++) {
			solver.solve();
		}
		long endingTime = System.currentTimeMillis();
		long duration = endingTime - startingTime;

		GraphSoluce soluceGraphBFS = solver.getGraphSoluce();

		DungeonSoluce soluceDonjonBFS = mapping.transform(soluceGraphBFS);

		logger.info("Solution   => " + soluceDonjonBFS.getSoluce());
		logger.info(() -> "Temps (ms) => " + duration);
		logger.info(() -> "Steps      => " + solver.getSteps());
		logger.info(() -> "---------------------------");
	}

}
