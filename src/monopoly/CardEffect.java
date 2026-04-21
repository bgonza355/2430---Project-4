package monopoly;

/**
 * The purpose of this interface is to serve as a way to
 * tie an effect to a card via a lambda.
 * Each Card has a CardEffect which will be passed a lambda
 * that represents the cards specific effect.
 */
public interface CardEffect {
	void apply(PlayerState player, GameContext ctx);
}
