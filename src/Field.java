import java.util.Arrays;

public class Field {
    static final int SIZE = 10;                 // фиксированный размер поля для игры
    char[][] cells = new char[SIZE][SIZE];      // создание поля
    Navy navy = new Navy();
    Ship[] arrShipOfCell;

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