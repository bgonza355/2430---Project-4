package monopoly;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is the application entry point for the Monopoly simulation.
 * It creates and connects together all the top-level components like
 * Board, Decks, GameContexts, Dice, PlayerState, and TurnEngine.
 * 
 */
public class MonopolyApp {
	
    private static final int[] CHECKPOINTS = {1000, 10000, 100000, 1000000};
    private static final int RUNS_PER_STRATEGY = 10;
    private static final long BASE_SEED = 0;
    
    private static final Path OUTPUT_DIR = Paths.get("output");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(OUTPUT_DIR);

        runStrategy(JailStrategy.Type.A);
        runStrategy(JailStrategy.Type.B);

        String absolutePath = OUTPUT_DIR.toAbsolutePath().toString();
        System.out.println("CSV file written to " + absolutePath);
    }
    
    /**
     * This method runs independent simulations for each strategy.
     * The method takes snapshots at the four checkpoint turn counts and
     * creates a CSV for each checkpoint.
     * 
     * The method runs RUNS_PER_STRATEGY (10) times per strategy.
     */
    private static void runStrategy(JailStrategy.Type strategyType) throws IOException {
    	long[][][] snapshots = new long[CHECKPOINTS.length]
    								   [RUNS_PER_STRATEGY]
    								   [Board.SIZE];
    	
    	for (int run = 0; run < RUNS_PER_STRATEGY; run++ ) {
    		
    		// This makes it so that each run will give us the
    		// same values. If we want different values, we can
    		// switch the seed.
    		long seed = BASE_SEED;
    		if (strategyType == JailStrategy.Type.A) {
    			seed += run;
    		} else if (strategyType == JailStrategy.Type.B) {
    			seed += 1000000 + run;
    		}
	    	Random rng = new Random(seed);
	    	
	    	Board board = Board.buildStandard();
	    	Deck chance = DeckFactory.buildChance(rng);
	    	Deck community = DeckFactory.buildCommunityChest(rng);
	    	GameContext ctx = new GameContext(board, chance, community);
	    	Dice dice = new Dice(rng);
	    	JailStrategy strategy = new JailStrategy(strategyType);
	    	TurnEngine engine = new TurnEngine(ctx, dice, strategy);
	    	PlayerState player = new PlayerState();
	    	
	    	// Walk through checkpoints in order. For each, advance the simulation
	    	int turnsPlayed = 0;
	    	for (int i = 0; i < CHECKPOINTS.length; i++) {
	    		int target = CHECKPOINTS[i];
	    		while (turnsPlayed < target) {
	    			engine.playTurn(player);
	    			turnsPlayed++;
	    		}
	    		snapshots[i][run] = ctx.getLandingCounts();
	    	}	
	    	
	    	System.out.printf("Strategy %s, run %d/%d complete%n",
	    			strategyType, run + 1, RUNS_PER_STRATEGY);
    	}
    	
    	for (int i = 0; i < CHECKPOINTS.length; i++) {
    		writeCsv(strategyType, CHECKPOINTS[i], snapshots[i]);
    	}
    	
    }
    
    /**
     * This method writes a CSV with one row per square with the file name
     * format: strategy_(A|B)_n_(turns).csv where turns represents
     * whether it is 1,000, 10,000, 100,000, or 1,000,000 turns.
     */
    private static void writeCsv(JailStrategy.Type strategyType, int n, long[][] runsCounts) 
    		throws IOException {
    	String filename = String.format("strategy_%s_n_%d.csv", strategyType, n);
    	Path file = OUTPUT_DIR.resolve(filename);
    	
    	Board board = Board.buildStandard();
    	
    	try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(file))) {
    		StringBuilder header = new StringBuilder("square_index,square_name");
    		for (int run = 0; run < RUNS_PER_STRATEGY; run++) {
                header.append(",run").append(run + 1).append("_count");
                header.append(",run").append(run + 1).append("_pct");
    		}
    		out.println(header);
    		
        	// this loop is for one row per square
        	for (int square = 0; square < Board.SIZE; square++) {
        		StringBuilder row = new StringBuilder();
        		row.append(square).append(',');
        		
        		row.append(board.at(square).getName());
        		
        		for (int run = 0; run < RUNS_PER_STRATEGY; run++) {
        			long count = runsCounts[run][square];
        			double pct = (count * 100.0) / n;
        			row.append(',').append(count);
        			row.append(',').append(String.format("%.4f", pct));
        		}
        		
        		out.println(row);
        	}
    	}
    }
}
