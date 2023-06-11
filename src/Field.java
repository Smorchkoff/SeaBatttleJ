import java.util.Arrays;

public class Field {
    static final int SIZE = 10;                 // фиксированный размер поля для игры
    char[][] cells = new char[SIZE][SIZE];      // создание поля

    Ship[] arrShipOfCell;

    public int getCell(int row, int col) {
        return cells[col][row];
    }

    public boolean isCellEmpty(int row, int col) {
        return cells[col][row] == '.'; // magic 0
    }

    public boolean isShipHealthy(int row, int col) {
        return cells[col][row] == 'o'; //magic 1
    }

    public boolean isShipWrecked(int row, int col) {
        return cells[col][row] == 'x'; // magic 2
    }

    public boolean isFreeCell(int row, int col) {
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

    public boolean regShot(int row, int col) {
        if (isCellEmpty(row, col)) {
            cells[row][col] = '*';
            return false;
        }
        cells[row][col] = 'x';
        return true;
    }

    private void markKilledShip(int row, int col) {
        Navy navy = new Navy();
        if (navy.isShipVertical(row, col)) {
            int posCol = col;
            while (posCol > 0 && (cells[posCol][row] != '.' && cells[posCol][row] != '*')) posCol--;
            if (cells[posCol][row] != 'o' && cells[posCol][row] != 'x') cells[posCol][row]++;

            while (posCol < 9 && (cells[posCol][row] != 0 && cells[posCol][row] != 4)) {
                cells[posCol][row] = 3;

                if (posCol > 0) {
                    if (cells[posCol][row] == '.') cells[posCol][row] = '*';
                    if (row - 1 >= 0)
                        if (cells[posCol - 1][row - 1] == '.') cells[posCol - 1][row - 1] = '*';
                    if (row - 1 >= 0)
                        if (cells[posCol + 1][row - 1] == '.') cells[posCol + 1][row - 1] = '*';
                    if (row + 1 <= 9)
                        if (cells[posCol - 1][row + 1] == '.') cells[posCol - 1][row + 1] = '*';
                    if (row + 1 <= 9)
                        if (cells[posCol + 1][row + 1] == '.') cells[posCol + 1][row + 1] = '*';
                }
                if (row > 0)
                    if (cells[posCol][row - 1] == '.') cells[posCol][row - 1] = '*';
                if (row < 9)
                    if (cells[posCol][row + 1] == '.') cells[posCol][row + 1] = '*';
                if (cells[posCol + 1][row + 1] == '.') cells[posCol + 1][row + 1] = '*';
                posCol++;
            }
        } else {
            int posRow = row;
            while (posRow > 0 && cells[col][posRow] != '.' && cells[col][posRow] != 'x') posRow--;
            if (cells[col][posRow] != 'o' && cells[col][posRow] != 'x') cells[col][posRow]++;

            while (posRow < 9 && (cells[col][posRow] != '.' && cells[col][posRow] != '*')) {
                cells[col][posRow] = 'x';

                if (posRow > 0) {
                    if (cells[col][posRow - 1] == '.') cells[col][posRow - 1] = '*';
                    if (col - 1 >= 0)
                        if (cells[col - 1][posRow - 1] == '.') cells[col - 1][posRow - 1] = '*';
                    if (col - 1 >= 0)
                        if (cells[col + 1][posRow - 1] == '.') cells[col + 1][posRow - 1] = '*';
                    if (col + 1 <= 9)
                        if (cells[col - 1][posRow + 1] == '.') cells[col - 1][posRow + 1] = '*';
                    if (col + 1 <= 9)
                        if (cells[col + 1][posRow + 1] == '.') cells[col + 1][posRow + 1] = '*';
                }
                if (col > 0)
                    if (cells[col-1][posRow] == '.') cells[col-1][posRow] = '*';
                if (col < 9)
                    if (cells[col+1][posRow] == '.') cells[col+1][posRow] = '*';
                if (cells[col + 1][posRow + 1] == '.') cells[col + 1][posRow + 1] = '*';
                posRow++;
            }
        }
    }
}