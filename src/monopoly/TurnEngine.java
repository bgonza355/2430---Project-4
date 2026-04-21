package monopoly;

/**
 * The purpose of this class is to execute one Monopoly turn
 * for a single player. When I say "turn", I mean a single
 * dice roll plus the consequences of that dice roll like the
 * movement of that dice, resolving the square the player ends 
 * up on, the effects of the square, and incrementing the
 * landing tally (how many players have ended up on the square).  
 */
public final class TurnEngine {
	private static final int DOUBLES_LIMIT = 3;
	
    private final GameContext ctx;
    private final Dice dice;
    
    public TurnEngine(GameContext ctx, Dice dice) {
        this.ctx = ctx;
        this.dice = dice;
    }
    
    public void playTurn(PlayerState player) {
    	Dice.Roll roll = dice.roll();
    	
    	if (roll.isDouble()) {
    		player.incrementConsecutiveDoubles();
    		if (player.getConsecutiveDoubles() == DOUBLES_LIMIT) {
    			// if you roll doubles for DOUBLES_LIMIT (i.e., 3) times
    			// then you will go to jail with no movement
    			ctx.sendToJail(player);
    			ctx.incrementLanding(player.getPosition());
    			return;
    		}
    	} else {
    		// if they don't get a double, reset so that
    		// the next pair of doubles start a new streak
    		player.resetConsecutiveDoubles();
    	}
    	
    	ctx.moveBy(player, roll.total());
    	ctx.incrementLanding(player.getPosition());	
    }
}
