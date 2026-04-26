package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is the the base representation of a single square on the Monopoly 
 * board. Every square has a fixed index (its position on the board), a display 
 * name, and a SquareType that categorizes its behavior.
 * 
 * Subclasses override resolve() method to implement square-specific effects
 * such as drawing a card or sending the player to jail.
 */
public class Square {
    private final int index;
    private final String name;
    private final SquareType type;

    public Square(int index, String name, SquareType type) {
        this.index = index;
        this.name = name;
        this.type = type;
    }

    public int getIndex() { return index; }
    public String getName() { return name; }
    public SquareType getType() { return type; }

    /**
     * This method does not do anything by default. The purpose of this
     * method is to be overriden by methods that do have an effect.
     */
    public void resolve(PlayerState player, GameContext ctx) {
        return;
    }

}
