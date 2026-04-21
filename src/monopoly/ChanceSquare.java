package monopoly;

/**
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

    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        ctx.getChanceDeck().drawAndApply(player, ctx);
    }
}