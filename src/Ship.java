public class Ship {
    private int deck, xPosition, yPosition;

    Ship(int deck, int x, int y) {
        this.deck = deck;
        this.xPosition = x;
        this.yPosition = y;
    }

    public int getDeck() {
        return deck;
    }
    public int getxPosition() {
        return xPosition;
    }
    public int getyPosition() {
        return yPosition;
    }
}