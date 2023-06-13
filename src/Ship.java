public class Ship {
    private int deck;
    private int xPosition;
    private int yPosition;
    private boolean Direction;


    Ship(int deck, int x, int y, boolean Direction) {
        this.deck = deck;
        this.xPosition = x;
        this.yPosition = y;
        this.Direction = Direction;
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
    public boolean isDirection(){
        return Direction;
    }
    @Override
    public String toString(){
        return "Ship{" +
                "deck = " + deck +
                ", x = " + xPosition +
                ", y = " + yPosition +
                ", rotate = " + Direction+
                "}";
    }
}