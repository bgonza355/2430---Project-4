package monopoly;

/**
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
     * @param index
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
}
