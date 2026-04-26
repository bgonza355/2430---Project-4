package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class encapsulates the policy for what a jailed player does
 * on their turn. One class with a strategy type chosen at construction.
 */
public final class JailStrategy {
    public enum Type { A, B }

    private static final int MAX_DOUBLES_ATTEMPTS = 3;

    private final Type type;
    
    public JailStrategy(Type type) {
        this.type = type;
    }

    public Type getType() { return type; }

    
    public JailOutcome resolve(PlayerState player, GameContext ctx, Dice dice) {
        if (type == Type.A) {
            return resolveStrategyA(player);
        } else {
            return resolveStrategyB(player, dice);
        }
    }
    
    /**
     * This method ...
     * 
     */
    private JailOutcome resolveStrategyA(PlayerState player) {
        if (player.hasGoojfCard()) {
            player.useGoojfCard();
        }
        
        // this will only be called if GOOFJ was used
        // in which case, it will exit jail and roll normally
        return JailOutcome.exitAndRoll();
    }
    
    /**
     * In this method, if the player has a GOOJF card, they can just
     * use that and roll. Else, if they have 3 failed attempts to
     * get out of jail, they'll just pay the fine (if money was
     * implemented) and then roll.
     * The player can exit using doubles. 
     */
    private JailOutcome resolveStrategyB(PlayerState player, Dice dice) {
        if (player.hasGoojfCard()) {
            player.useGoojfCard();
            return JailOutcome.exitAndRoll();
        }

        if (player.getJailTurnCount() >= MAX_DOUBLES_ATTEMPTS) {
            return JailOutcome.exitAndRoll();
        }

        Dice.Roll roll = dice.roll();
        if (roll.isDouble()) {
            return JailOutcome.exitUsingDoubles(roll);
        }
        return JailOutcome.stayInJail();
    }
}