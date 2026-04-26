package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class represents the result of a jail strategy resolving 
 * one turn for a jailed player. 
 * 
 * Three possible outcomes are possible for this:
 * 		STAY_IN_JAIL: The turn will end with the player still in jail
 * 		EXIT_AND_ROLL: the players exits jail and then rolls normally
 * 		EXIT_USING_DOUBLES: the player rolled doubles to exit and the
 * 							roll is reused for movement.
 */
public class JailOutcome {
	public enum Type { STAY_IN_JAIL, EXIT_AND_ROLL, EXIT_USING_DOUBLES }

    private final Type type;
    private final Dice.Roll roll;
    
    private JailOutcome(Type type, Dice.Roll roll) {
        this.type = type;
        this.roll = roll;
    }
    
    /**
     * This method is used as an alternative to:
     * 		new JailOutcome(Type.STAY_IN_JAIL, null);
     * Instead:
     * 		JailOutcome.stayInJail();
     */
    public static JailOutcome stayInJail() {
        return new JailOutcome(Type.STAY_IN_JAIL, null);
    }

    /**
     * This method is used as an alternative to:
     * 		new JailOutcome(Type.EXIT_AND_ROLL, null);
     * Instead:
     * 		JailOutcome.exitAndRoll();
     */
    public static JailOutcome exitAndRoll() {
        return new JailOutcome(Type.EXIT_AND_ROLL, null);
    }

    /**
     * This method is used as an alternative to:
     * 		new JailOutcome(Type.EXIT_USING_DOUBLES, null);
     * Instead:
     * 		JailOutcome.exitUsingDoubles();
     */
    public static JailOutcome exitUsingDoubles(Dice.Roll roll) {
        return new JailOutcome(Type.EXIT_USING_DOUBLES, roll);
    }

    public Type getType() { return type; }
    public Dice.Roll getRoll() { return roll; }

}
