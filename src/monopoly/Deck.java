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
		// it has to be drawPrile as initialCards is immutable.
		Collections.shuffle(drawPile, rng); 
	}
	
	public void drawAndApply(PlayerState player, GameContext ctx) {
		if (drawPile.isEmpty()) {
			recycleDiscardedCards();
		}
		Card card = drawPile.remove(drawPile.size() - 1);
        
		if (card.isGetOutOfJailFree()) {
		    card.setSource(this);
		    player.addGoojfCard(card);
        } else {
            discardPile.add(card);
        }

		card.getEffect().apply(player, ctx);
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
    
    private void recycleDiscardedCards() {
    	List<Card> cards = new ArrayList<>(discardPile);
    	discardPile.clear();
    	Collections.shuffle(cards, rng);
        drawPile.addAll(cards);
    }
}
