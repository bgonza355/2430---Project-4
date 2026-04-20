package monopoly;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public final class Deck {
	private final List<Card> drawPile = new ArrayList<>();
	private final List<Card> discardPile = new ArrayList<>();
	private final Random rng;
	
	public Deck(List<Card> initialCards, Random rng) {
		this.rng = rng;
		drawPile.addAll(initialCards);
		// rng is necessary here so we can reproduce results
		Collections.shuffle(initialCards, rng); 
	}
	
	public void drawAndApply(PlayerState player, GameContext ctx) {
		Card card = drawPile.remove(drawPile.size() - 1);
        
		if (card.isGetOutOfJailFree()) {
			// todo: still gotta fully implement this bad boy
        } else {
            discardPile.add(card);
        }

	}
	
	/**
	 * This method is gonna be called whenever someone draws a
	 * get out of jail free card. These cards get placed back at
	 * the bottom of the deck (either a Chance deck or a Community
	 * Chest deck).
	 */
    public void returnGoojfToBottom(Card card) {
        drawPile.add(0, card);
    }
}
