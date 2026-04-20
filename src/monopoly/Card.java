package monopoly;

public final class Card {
    private final String name;
    private final boolean isGetOutOfJailFree;

    public Card(String name, boolean isGetOutOfJailFree) {
        this.name = name;
        this.isGetOutOfJailFree = isGetOutOfJailFree;
    }
    
    public String getName() { return name; }
    public boolean isGetOutOfJailFree() { return isGetOutOfJailFree; }
}
