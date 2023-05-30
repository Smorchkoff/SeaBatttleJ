import java.util.Arrays;

public class Field {
    static final int SIZE = 10;                 // фиксированный размер поля для игры
    char[][] cells = new char[SIZE][SIZE];      // создание поля
    Navy navy = new Navy();
    Ship[] arrShipOfCell;

    public int getCell(int row, int col){
        return cells[col][row];
    }
    public boolean isCellEmpty(int row, int col){
        return cells[col][row] == '.'; // magic 0
    }
    public boolean isShipHealthy(int row, int col){
        return cells[col][row] == 'o'; //magic 1
    }
    public boolean isShipWrecked(int row, int col){
        return cells[col][row] == 'x'; // magic 2
    }
    public boolean isFreeCell(int row, int col){
        return cells[col][row] == '.' || cells[col][row] == 'o';
    }

    public void fillField() {
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(cells[i], '.');
        }
        System.out.println();
    }

    public void viewField() {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    void autoInitField() {
        arrShipOfCell = navy.initShip();
    }
}