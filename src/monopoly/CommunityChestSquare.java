package monopoly;

public final class CommunityChestSquare extends Square {
    public CommunityChestSquare(int index, String name) {
        super(index, name, SquareType.COMMUNITY_CHEST);
    }

    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        // not implemented yet
    }
}