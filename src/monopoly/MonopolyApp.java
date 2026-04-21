package monopoly;

import java.util.Random;

public class MonopolyApp {

	public static void main(String[] args) {
		Random rng = new Random(0);
        Board board = Board.buildStandard();
        Deck chance = DeckFactory.buildChance(rng);
        Deck community = DeckFactory.buildCommunityChest(rng);
        GameContext ctx = new GameContext(board, chance, community);
        Dice dice = new Dice(rng);
        PlayerState player = new PlayerState();
        TurnEngine engine = new TurnEngine(ctx, dice);
        
        // interesting stuff goes here.
	}

}
