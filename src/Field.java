public class Field implements Cloneable {
    static final int SIZE = 10;                 // фиксированный размер поля для игры
    public final char[][] cells = new char[SIZE][SIZE];      // создание поля
    public Field(){
        randomShipsFill();
    }
    public Field clone() throws CloneNotSupportedException{
        return (Field) super.clone();
    }

    public int getCell(int row, int col) {
        return cells[col][row];
    }
    public static int[] size(){
        return new int[]{10,10};
    }
    public static char[][] getCells;
    public boolean isCellEmpty(int row, int col) {
        return cells[col][row] == 0; // magic 0
    }
    public boolean isShipHealthy(int row, int col) {
        return cells[col][row] == 1; //magic 1
    }
    public boolean isShipWrecked(int row, int col) {
        return cells[col][row] == 2; // magic 2
    }
    public boolean isFreeCell(int row, int col) {
        return cells[col][row] == 0 || cells[col][row] == 1;
    }

    public void randomShipsFill() {
        java.util.Random random = new java.util.Random();
        Navy navy = new Navy();
        for (int size : navy.DECK) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            int rotate = random.nextInt(2);
            Ship ship = new Ship(size, row, col, rotate == 1);
            while (!canSetShip(ship)) {
                row = random.nextInt(10);
                col = random.nextInt(10);
                rotate = random.nextInt(2);
                ship = new Ship(size, row, col, rotate == 1);
            }

            for (int i = 0; i < size; i++) {
                if (ship.isDirection()) {
                    cells[col + i][row] = 1;
                } else {
                    cells[col][row + i] = 1;
                }
            }
        }
    }
    public boolean canSetShip(Ship ship){
        if(ship.getxPosition() < 0 || ship.getyPosition() < 0 || cells.length <= ship.getxPosition() || cells.length <= ship.getyPosition()){
            return false;
        }
        if(ship.isDirection() && cells.length <= ship.getyPosition() + ship.getDeck()){
            return false;
        }
        if(!ship.isDirection() && cells.length <= ship.getxPosition() + ship.getDeck()) {
            return false;
        }

        int minRow = Math.max(0, ship.getxPosition() - 1);
        int minCol = Math.max(0, ship.getyPosition() - 1);
        int maxRow = Math.min(cells.length - 1, ship.getxPosition()+1 + (ship.isDirection() ? 0 : ship.getDeck()));
        int maxCol = Math.min(cells.length - 1, ship.getyPosition()+1 + (ship.isDirection() ? ship.getDeck() : 0));

        for(int row = minRow; row <= maxRow; row++){
            for(int col = minCol; col <= maxCol; col++){

                if(!isCellEmpty(row,col)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean regShot(int row, int col) {
        Navy navy = new Navy();
        if (isCellEmpty(row, col)) {
            cells[row][col] = 4;
            return false;
        } else if(isShipHealthy(row, col)){
            cells[row][col] = 2;
            if(!navy.isShipAlive(row, col)) markKilledShip(row, col);
        }
        return true;
    }
    private void markKilledShip(int row, int col) {
        Navy navy = new Navy();
        if (navy.isShipVertical(row, col)) {
            int posCol = col;
            while (posCol > 0 && (cells[posCol][row] != 0 && cells[posCol][row] != 4)) posCol--;
            if (cells[posCol][row] != 1 && cells[posCol][row] != 2) cells[posCol][row]++;

            while (posCol < 9 && (cells[posCol][row] != 0 && cells[posCol][row] != 4)) {
                cells[posCol][row] = 3;

                if (posCol > 0) {
                    if (cells[posCol][row] == 0) cells[posCol][row] = 4;
                    if (row - 1 >= 0)
                        if (cells[posCol - 1][row - 1] == 0) cells[posCol - 1][row - 1] = 4;
                    if (row - 1 >= 0)
                        if (cells[posCol + 1][row - 1] == 0) cells[posCol + 1][row - 1] = 4;
                    if (row + 1 <= 9)
                        if (cells[posCol - 1][row + 1] == 0) cells[posCol - 1][row + 1] = 4;
                    if (row + 1 <= 9)
                        if (cells[posCol + 1][row + 1] == 0) cells[posCol + 1][row + 1] = 4;
                }
                if (row > 0)
                    if (cells[posCol][row - 1] == 0) cells[posCol][row - 1] = 4;
                if (row < 9)
                    if (cells[posCol][row + 1] == 0) cells[posCol][row + 1] = 4;
                if (cells[posCol + 1][row + 1] == 0) cells[posCol + 1][row + 1] = 4;
                posCol++;
            }
        } else {
            int posRow = row;
            while (posRow > 0 && cells[col][posRow] != 0 && cells[col][posRow] != 2) posRow--;
            if (cells[col][posRow] != 1 && cells[col][posRow] != 2) cells[col][posRow]++;

            while (posRow < 9 && (cells[col][posRow] != 0 && cells[col][posRow] != 4)) {
                cells[col][posRow] = 2;

                if (posRow > 0) {
                    if (cells[col][posRow - 1] == 0) cells[col][posRow - 1] = 4;
                    if (col - 1 >= 0)
                        if (cells[col - 1][posRow - 1] == 0) cells[col - 1][posRow - 1] = 4;
                    if (col - 1 >= 0)
                        if (cells[col + 1][posRow - 1] == 0) cells[col + 1][posRow - 1] = 4;
                    if (col + 1 <= 9)
                        if (cells[col - 1][posRow + 1] == 0) cells[col - 1][posRow + 1] = 4;
                    if (col + 1 <= 9)
                        if (cells[col + 1][posRow + 1] == 0) cells[col + 1][posRow + 1] = 4;
                }
                if (col > 0)
                    if (cells[col-1][posRow] == 0) cells[col-1][posRow] = 4;
                if (col < 9)
                    if (cells[col+1][posRow] == 0) cells[col+1][posRow] = 4;
                if (cells[col + 1][posRow + 1] == 0) cells[col + 1][posRow + 1] = 4;
                posRow++;
            }
        }
    }
}