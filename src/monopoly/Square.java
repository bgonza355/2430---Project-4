package monopoly;

public class Square {
    private final int index;
    private final String name;
    private final SquareType type;

    public Square(int index, String name, SquareType type) {
        this.index = index;
        this.name = name;
        this.type = type;
    }

    public int getIndex() { return index; }
    public String getName() { return name; }
    public SquareType getType() { return type; }

    public void resolve(PlayerState player, GameContext ctx) {
        // Haven't implemented PlayerState or GameContext yet
    }

}
