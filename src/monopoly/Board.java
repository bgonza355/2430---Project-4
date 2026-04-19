package monopoly;

public final class Board {
    public static final int SIZE = 40;
    private final Square[] squares;

    public Board(Square[] squares) {
        if (squares.length != SIZE) {
            throw new IllegalArgumentException("Board must have " + SIZE + " squares");
        }
        this.squares = squares;
    }
    
    /**
     * This method is necessary as if you were at square 2 and had to go back 3 spaces,
     * using (2-3) % 40 would yield -1 which is not within [0, n-1].
     */
    public Square at(int index) {
        return squares[Math.floorMod(index, SIZE)];
    }
    
    public int size() {
        return SIZE;
    }

    public static Board buildStandard() {
        Square[] s = new Square[SIZE];
        s[0]  = new Square(0,  "GO",                    SquareType.GO);
        s[1]  = new Square(1,  "Mediterranean Avenue",  SquareType.PROPERTY);
        // s[2]  = new CommunityChestSquare(2,  "Community Chest");
        s[3]  = new Square(3,  "Baltic Avenue",         SquareType.PROPERTY);
        s[4]  = new Square(4,  "Income Tax",            SquareType.TAX);
        s[5]  = new Square(5,  "Reading Railroad",      SquareType.RAILROAD);
        s[6]  = new Square(6,  "Oriental Avenue",       SquareType.PROPERTY);
        // s[7]  = new ChanceSquare(7,  "Chance");
        s[8]  = new Square(8,  "Vermont Avenue",        SquareType.PROPERTY);
        s[9]  = new Square(9,  "Connecticut Avenue",    SquareType.PROPERTY);
        s[10] = new Square(10, "Jail",                  SquareType.JAIL);
        s[11] = new Square(11, "St. Charles Place",     SquareType.PROPERTY);
        s[12] = new Square(12, "Electric Company",      SquareType.UTILITY);
        s[13] = new Square(13, "States Avenue",         SquareType.PROPERTY);
        s[14] = new Square(14, "Virginia Avenue",       SquareType.PROPERTY);
        s[15] = new Square(15, "Pennsylvania Railroad", SquareType.RAILROAD);
        s[16] = new Square(16, "St. James Place",       SquareType.PROPERTY);
        // s[17] = new CommunityChestSquare(17, "Community Chest");
        s[18] = new Square(18, "Tennessee Avenue",      SquareType.PROPERTY);
        s[19] = new Square(19, "New York Avenue",       SquareType.PROPERTY);
        s[20] = new Square(20, "Free Parking",          SquareType.FREE_PARKING);
        s[21] = new Square(21, "Kentucky Avenue",       SquareType.PROPERTY);
        // s[22] = new ChanceSquare(22, "Chance");
        s[23] = new Square(23, "Indiana Avenue",        SquareType.PROPERTY);
        s[24] = new Square(24, "Illinois Avenue",       SquareType.PROPERTY);
        s[25] = new Square(25, "B&O Railroad",          SquareType.RAILROAD);
        s[26] = new Square(26, "Atlantic Avenue",       SquareType.PROPERTY);
        s[27] = new Square(27, "Ventnor Avenue",        SquareType.PROPERTY);
        s[28] = new Square(28, "Water Works",           SquareType.UTILITY);
        s[29] = new Square(29, "Marvin Gardens",        SquareType.PROPERTY);
        // s[30] = new GoToJailSquare(30, "Go To Jail");
        s[31] = new Square(31, "Pacific Avenue",        SquareType.PROPERTY);
        s[32] = new Square(32, "North Carolina Avenue", SquareType.PROPERTY);
        // s[33] = new CommunityChestSquare(33, "Community Chest");
        s[34] = new Square(34, "Pennsylvania Avenue",   SquareType.PROPERTY);
        s[35] = new Square(35, "Short Line Railroad",   SquareType.RAILROAD);
        // s[36] = new ChanceSquare(36, "Chance");
        s[37] = new Square(37, "Park Place",            SquareType.PROPERTY);
        s[38] = new Square(38, "Luxury Tax",            SquareType.TAX);
        s[39] = new Square(39, "Boardwalk",             SquareType.PROPERTY);
        return new Board(s);
    }
}
