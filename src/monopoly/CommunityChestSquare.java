package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is a Square subtype that represents a Community Chest space
 * on the board.
 */
public final class CommunityChestSquare extends Square {
    public CommunityChestSquare(int index, String name) {
        super(index, name, SquareType.COMMUNITY_CHEST);
    }

    /**
     * This method draws and applies the top card from the Community
     * Chest held by the GameContext. The card's effect may move
     * the player, send them too jail, or have other outcomes.
     */
    @Override
    public void resolve(PlayerState player, GameContext ctx) {
    	ctx.getCommunityChestDeck().drawAndApply(player, ctx);
    }
}