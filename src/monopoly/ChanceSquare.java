package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is for the Chance squares. If the player lands on it,
 * the player gets draws a chance card from GameContext (which 
 * holds the decks).
 * 
 * It implements the resolve() method which is required by the 
 * Square parent class. This allows for all the square types to 
 * be treated the same as they all implement the resolve method.
 */
public final class ChanceSquare extends Square {
    public ChanceSquare(int index, String name) {
        super(index, name, SquareType.CHANCE);
    }

    /**
     * This method draws and applies the top card from the Chance
     * deck held by the GameContext. The drawn card's effect may
     * move the player, send them to jail, or grant a GOOJF card,
     * among other outcomes.
     */
    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        ctx.getChanceDeck().drawAndApply(player, ctx);
    }
}