package monopoly;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.Test;
import monopoly.JailStrategy.Type;

class TestCases {
	//setup for test cases
	long BASE_SEED = 0;
	long seed = BASE_SEED;
	Random rng = new Random(seed);
	Board board = Board.buildStandard();
	Deck chance = DeckFactory.buildChance(rng);
	Deck community = DeckFactory.buildCommunityChest(rng);
	GameContext ctx = new GameContext(board, chance, community);
	Dice dice = new Dice(rng);
	JailStrategy strategyA = new JailStrategy(Type.A);
	JailStrategy strategyB = new JailStrategy(Type.A);
	TurnEngine engine1 = new TurnEngine(ctx, dice, strategyA);
	TurnEngine engine2 = new TurnEngine(ctx, dice, strategyB);
	PlayerState player = new PlayerState();
	
	@Test
	public void PlayerPosition() {
		//test the setting and getting player position
		player.setPosition(10);
		int actual = player.getPosition();
		assertEquals(actual,10);
	}
	@Test
	public void goojfCards() {
		//test if player has no goofj card
		boolean noGoojf = player.hasGoojfCard();
		assertEquals(noGoojf,false);
		//Test if player has goofj card
		player.addGoojfCard(new Card("Get Out of Jail Free", (p, ctx) -> {}, true));
		boolean actual = player.hasGoojfCard();
		assertEquals(actual,true);
	}
	@Test
	public void IsInJail() {
		//test first that player is not in jail then test that the player is in jail
		boolean notInJail = player.isInJail();
		assertEquals(notInJail,false);
		ctx.sendToJail(player);
		boolean isInJail = player.isInJail();
		assertEquals(isInJail,true);
	}
	@Test 
	//test method that releases player from jail 
	public void releaseFromJail(){
		ctx.sendToJail(player);
		ctx.releaseFromJail(player);
		boolean actual = player.isInJail();
		assertEquals(false,actual);
	}
	@Test 
	public void SendPlayerToSquares() {
		//move player to Reading Railroad 
		ctx.moveTo(player, 5);
		Square landed = board.at(player.getPosition());
		assertEquals(board.at(5),landed);
		//move player to Pennsylvania Railroad
		ctx.moveTo(player, 15);
		Square land = board.at(player.getPosition());
		assertEquals(board.at(15),land);
	}
	@Test
	//test whether tracking doubles works or not
	public void TestDoubleTracking() {
		boolean actual = false;
		//while loop to continue rolling dice until we get 1 double
		while(player.getConsecutiveDoubles() == 0) {
			engine1.playTurn(player);
			if(player.getConsecutiveDoubles()==1) {
				actual = true;
			}
		}
		assertEquals(true,actual);		
	}
	
	@Test
	public void SentToJailFor3Doubles() {
		boolean actual = false;
		//while loop to continue rolling dice until we get 3 doubles then send player to jail
		while(player.isInJail() == false) {
			engine1.playTurn(player);
			if(player.isInJail() == true) {
				actual = true;
			}
		}
		assertEquals(true,actual);
	}
	//edge cases 
	@Test
	public void MovePlayerBehindGo() {
		ctx.moveBy(player, -3);
		int actual = player.getPosition();
		assertEquals(37,actual);
		//put player back at the start (go)
		player.setPosition(0);
		ctx.moveBy(player, -13);
		int realPlacement = player.getPosition();
		assertEquals(27,realPlacement);
	}
	@Test
	public void MovePastGo() {
		player.setPosition(39);
		ctx.moveBy(player, 2);
		int actual = player.getPosition();
		assertEquals(1,actual);
		//put player behind the start (go)
		player.setPosition(39);
		ctx.moveBy(player, 13);
		int realPlacement = player.getPosition();
		assertEquals(12,realPlacement);
	}
}
