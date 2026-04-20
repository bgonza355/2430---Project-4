package monopoly;

public final class GoToJailSquare extends Square {
    public GoToJailSquare(int index, String name) {
        super(index, name, SquareType.GO_TO_JAIL);
    }

    @Override
    public void resolve(PlayerState player, GameContext ctx) {
        ctx.sendToJail(player);
    }
}
