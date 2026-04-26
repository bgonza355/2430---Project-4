package monopoly;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * The purpose of this class is to track the environment that a player
 * is acting within. This tracks stuff like the board, the two decks,
 * the landing counter, along with the helper methods that help coordinate
 * those variables.
 * This class is initialized once per game and describes a single simulation. 
 * Running multiple GameContext's would be used for running multiple independent 
 * simulations in which case each object would have its own board/deck/counter. 
 */
public class GameContext {
	public static final int JAIL_INDEX = 10;
	
	private final Board board;
    private final Deck chanceDeck;
    private final Deck communityChestDeck;

    private final long[] landingCounts = new long[Board.SIZE];
	private long totalMoves = 0;
	
    public GameContext(Board board, Deck chanceDeck, Deck communityChestDeck) {
    	this.board = board;
    	this.chanceDeck = chanceDeck;
    	this.communityChestDeck = communityChestDeck;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public Deck getChanceDeck() {
        return chanceDeck;
    }

    public Deck getCommunityChestDeck() {
        return communityChestDeck;
    }
    
    public long[] getLandingCounts() {
        return landingCounts.clone();
    }

    public long getTotalMoves() {
        return totalMoves;
    }

    /**
     * This method increments the landing counter for the specific square
     * This should be called once per turn at the end and after the effects
     * have been applied.
     * @param index	the square the user lands at.
     */
    public void incrementLanding(int index) {
        landingCounts[Math.floorMod(index, Board.SIZE)]++;
        totalMoves++;
    }


    /**
     * Moves a player to a specific square (tracked by index) and resolve which
     * square the user lands on. 
     * This is used by specific cards that require the player to move to a specific
     * location such as "Advance to Boardwalk".
     * @param player	the player that will be moved
     * @param targetIndex	the index of the square the player should be moved to
     */
    public void moveTo(PlayerState player, int targetIndex) {
        player.setPosition(Math.floorMod(targetIndex, Board.SIZE));
        Square landed = board.at(player.getPosition());
        landed.resolve(player, this);
    }
    
    /**
     * Moves a player by a specific number of squares and resolve which square
     * the player will land on.
     * This can handle both positive and negative deltas such as "Go forward 3
     * spaces" or "Go back 3 spaces".
     * @param player	the player that will be moved
     * @param delta		the positive or negative number of squares the player will be moved.
     */
    public void moveBy(PlayerState player, int delta) {
        moveTo(player, player.getPosition() + delta);
    }
    
    public void sendToJail(PlayerState player) {
        player.setPosition(JAIL_INDEX);
        player.setInJail(true);
        player.resetConsecutiveDoubles();
        player.resetJailTurnCount();
    }
	//use GOOJF card or leave after 1 turn
    public void strategyA(PlayerState player) {
    	boolean goojf  = player.hasGoojfCard();
    	//check to see if GOOJF card is available if so use immediately 
    	if(goojf = true) {
    		totalMoves++;
    		player.useGoojfCard();
    		player.setPosition(player.getPosition() + 1);
    	}
    	//if not wait 1 turn then leave jail on the next turn
    	else {
    	totalMoves= totalMoves + 2;
    	player.setPosition(player.getPosition() + 1);
    	}
    }
	//roll double to get out of jail or leave after 3 turns
    public void strategyB(PlayerState player,Dice dice) {
  
    	int turns = 1 ;
    	boolean doubles = false;
    	boolean killswitch = false;
    //check to see if GOOJF card is available if so use immediately 
    	boolean goojf  = player.hasGoojfCard();
    	//check to see if GOOJF card is available if so use immediately 
    	if( goojf = true) {
    		totalMoves++;
    		player.useGoojfCard();
    		player.setPosition(player.getPosition() + 1);
    	}
    	//if not try to roll a double 3 times
    	else if (goojf = false){
    		while(killswitch = false) {
    			//after the 3 attempts
    			if (turns == 4) {
    				killswitch = true;
    			}
    			
    			if(dice.roll().isDouble()) {	
    				doubles = true;
    				killswitch = true;
    			}
    			//increment every time
    			turns++; 			
    		}
    		//if doubles happened
    		if(doubles = true) {
    		totalMoves = totalMoves + turns;
    		player.setPosition(player.getPosition() + 1);
    		}
    		//after 3 attempts give up and leave jail on 4th turn	
    		else{
    			totalMoves = totalMoves + 4;
        		player.setPosition(player.getPosition() + 1);	
    		}
    	}
    }
}
