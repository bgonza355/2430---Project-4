package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * The purpose of this interface is to serve as a way to
 * tie an effect to a card via a lambda.
 * Each Card has a CardEffect which will be passed a lambda
 * that represents the cards specific effect.
 */
public interface CardEffect {
	/**
	 * This method receives the PlayerState of the player who drew
	 * the card and the shared GameContext, allowing effects to 
	 * move the player, send them to jail, or interact with the
	 * board in any other way.
	 */
	void apply(PlayerState player, GameContext ctx);
}
