package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is for the Jail squares. If the player lands on it,
 * the player gets sent to jail.
 * 
 * It implements the resolve() method which is required by the 
 * Square parent class. This allows for all the square types to 
 * be treated the same as they all implement the resolve method.
 */
public final class GoToJailSquare extends Square {
    public GoToJailSquare(int index, String name) {
        super(index, name, SquareType.GO_TO_JAIL);
    }

    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        ctx.sendToJail(player);
    }
}
