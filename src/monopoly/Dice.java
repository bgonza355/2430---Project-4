package monopoly;

import java.util.Random;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class models a pair of dice (standard dice each 
 * with six sides). Each roll produces a total ranging from
 * 2 (minimum) to 12 (the maximum possible value both can get)
 * and a flag for whether both dice ended up being the same (i.e.,
 * a double).
 */
public final class Dice {

	// this record is so we can bundle the total of the dice
	// and whether the roll was a double.
	public record Roll(int total, boolean isDouble) {}
	private final Random rng;

    public Dice(Random rng) {
        this.rng = rng;
    }

    /**
     * This method produces a Roll record containing a combined 
     * total (2-12) and a flag indicating whether both dice had 
     * the same value.  
     */
    public Roll roll() {
    	int d1 = rng.nextInt(6) + 1;
    	int d2 = rng.nextInt(6) + 1;
    	return new Roll(d1 + d2, d1 == d2);
    }
}
