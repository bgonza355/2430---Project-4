package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * This class tracks an individual card.
 * 
 * A card will have a name and an effect and track whether
 * or not it is a Get Out Of Jail Free (GOOJF) card.
 * It also has a reference to the deck it came out of as
 * after you use a GOOFJ card, it must return to its original
 * deck.
 */
public final class Card {
    private final String name;
    private final CardEffect effect;
    private final boolean isGetOutOfJailFree;
    private Deck source;	// this is set by Deck.drawAndApply() meth

    public Card(String name, CardEffect effect, boolean isGetOutOfJailFree) {
        this.name = name;
        this.effect = effect;
        this.isGetOutOfJailFree = isGetOutOfJailFree;
    }
    
    /**
     * This is an alternate constructor so that in DeckFactory we do not
     * have to continuously specify "no" for if it's a GOOJF card.
     */
    public Card(String name, CardEffect effect) {
        this(name, effect, false);
    }
    
    public String getName() { return name; }
    public CardEffect getEffect() { return effect; }
    public boolean isGetOutOfJailFree() { return isGetOutOfJailFree; }
    
    public Deck getSource() { return source; }
    public void setSource(Deck source) { this.source = source; }
}
