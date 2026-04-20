package monopoly;

public final class ChanceSquare extends Square {
    public ChanceSquare(int index, String name) {
        super(index, name, SquareType.CHANCE);
    }

    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        // not implemented yet
    }
}