package monopoly;

import java.util.List;
import java.util.Random;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * The purpose of this class is to actually build the chance and
 * community chest decks. The Deck class should only worry about
 * being a Deck and should not worry about how it is created - that
 * is the purpose of this class.
 * 
 * The user interacts with this class to create and initialize the 
 * decks and should not create a Deck on their own.
 */
public final class DeckFactory {
    private static final int GO = 0;
    private static final int JAIL = 10;
    private static final int ST_CHARLES = 11;
    private static final int ILLINOIS_AVE = 24;
    private static final int READING_RR = 5;
    private static final int BOARDWALK = 39;
    
    /**
     * This method returns a fully-populated Chance deck containing
     * 16 cards based on the standard game rules. 
     */
    public static Deck buildChance(Random rng) {
        List<Card> cards = List.of(
            new Card("Advance to Go", (p, ctx) -> ctx.moveTo(p, GO)),
            new Card("Advance to Illinois Avenue", (p, ctx) -> ctx.moveTo(p, ILLINOIS_AVE)),
            new Card("Advance to St. Charles Place", (p, ctx) -> ctx.moveTo(p, ST_CHARLES)),
            new Card("Advance to nearest Utility", (p, ctx) -> ctx.moveTo(p,
                    ctx.getBoard().indexOfNearest(p.getPosition(), SquareType.UTILITY))),
            new Card("Advance to nearest Railroad", (p, ctx) -> ctx.moveTo(p,
                    ctx.getBoard().indexOfNearest(p.getPosition(), SquareType.RAILROAD))),
            new Card("Advance to nearest Railroad", (p, ctx) -> ctx.moveTo(p,
                        ctx.getBoard().indexOfNearest(p.getPosition(), SquareType.RAILROAD))),
            new Card("Bank pays you dividend of $50", (p, ctx) -> {}),
            new Card("Get Out of Jail Free", (p, ctx) -> {}, true),
            new Card("Go back 3 spaces", (p, ctx) -> ctx.moveBy(p, -3)),
            new Card("Go to Jail", (p, ctx) -> ctx.sendToJail(p)),
            new Card("Make general repairs on all your property", (p, ctx) -> {}),
            new Card("Speeding fine $15", (p, ctx) -> {}),
            new Card("Take a trip to Reading Railroad", (p, ctx) -> ctx.moveTo(p, READING_RR)),
            new Card("Take a walk on the Boardwalk", (p, ctx) -> ctx.moveTo(p, BOARDWALK)),
            new Card("You have been elected Chairman of the Board", (p, ctx) -> {}),
            new Card("Your building loan matures", (p, ctx) -> {}) 
        );
        return new Deck(cards, rng);
    }
    
    /**
     * This method returns a fully-populated Community Chest deck 
     * containing 16 cards based on the standard game rules. 
     */
    public static Deck buildCommunityChest(Random rng) {
        List<Card> cards = List.of(
            new Card("Advance to Go", (p, ctx) -> ctx.moveTo(p, GO)),
            new Card("Bank error in your favor", (p, ctx) -> {}),
            new Card("Doctor's fee", (p, ctx) -> {}),
            new Card("From sale of stock you get $50", (p, ctx) -> {}),
            new Card("Get Out of Jail Free", (p, ctx) -> {}, true),
            new Card("Go to Jail", (p, ctx) -> ctx.sendToJail(p)),
            new Card("Holiday fund matures", (p, ctx) -> {}),
            new Card("Income tax refund", (p, ctx) -> {}),
            new Card("It is your birthday", (p, ctx) -> {}),
            new Card("Life insurance matures", (p, ctx) -> {}),
            new Card("Pay hospital fees of $100", (p, ctx) -> {}),
            new Card("Pay school fees of $50", (p, ctx) -> {}),
            new Card("Receive $25 consultancy fee", (p, ctx) -> {}),
            new Card("You are assessed for street repairs", (p, ctx) -> {}),
            new Card("You have won second prize in a beauty contest", (p, ctx) -> {}),
            new Card("You inherit $100", (p, ctx) -> {})
        );
        return new Deck(cards, rng);
    }
}
