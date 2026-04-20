package monopoly;

/**
 * The purpose of this class is to describe a player in the game, such as
 * the player's position, whether they're in-jail.
 * Section 8 Question 5 asked about multiple players. This class was implemented
 * so that multiple players are possible and so that player state is decoupled
 * from the game state (GameContext class). 
 */
public final class PlayerState {
	private int position = 0;
	private boolean inJail = false;
	// how many turns spent in current jail sentence
	private int jailTurnCount = 0;	
	private int consecutiveDoubles = 0;
	
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    public boolean isInJail() {
        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }
    

    public int getJailTurnCount() {
        return jailTurnCount;
    }
    
    public void incrementJailTurnCount() {
        jailTurnCount++;
    }

    public void resetJailTurnCount() {
        jailTurnCount = 0;
    }
    
    public int getConsecutiveDoubles() {
        return consecutiveDoubles;
    }
    
    public void incrementConsecutiveDoubles() {
        consecutiveDoubles++;
    }

    public void resetConsecutiveDoubles() {
        consecutiveDoubles = 0;
    }
}

