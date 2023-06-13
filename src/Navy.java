import java.util.Random;

public class Navy {
    public int[] DECK = {1,1,1,1,2,2,2,3,3,4};

    public boolean isShipAlive(int row, int col){
        int[] env = new int[]{-1,-1,-1,-1};

        if(row != 0) env[0] = row - 1;
        if(row != 9) env[1] = row - 1;
        if(col != 0) env[2] = col - 1;
        if(col != 9) env[3] = col - 1;
        // TODO Изменить логику отображение на логику класса Display
        if(isShipVertical(row, col)) {
            Field field = new Field();
            for(int i = 2; i < env.length; i++){
                if(env[i] != -1){
                    if(field.cells[env[i]][row] == 1) return true;

                    else if(field.cells[env[i]][row] == 2){
                        int posCol = col;

                        while (posCol > 0 && (field.cells[posCol][row] != 0 && field.cells[posCol][row] != 4)){
                            if(field.cells[posCol][row] == 1) return true;
                            posCol--;
                        }
                        while (posCol < 9 && (field.cells[posCol][row] != 0 && field.cells[posCol][row] != 1)){
                            if(field.cells[posCol][row] == 1) return true;
                            posCol++;
                        }
                        if (field.cells[posCol][row] == 1) return true;
                    }
                } else {
                    for(i = 0; i < env.length; i++){
                        if(env[i] != 1) {
                            if (field.cells[col][env[i]] == 1) return true;
                        } else if(field.cells[col][env[i]] == 2){
                            int posRow = row;

                            while(posRow > 0 && (field.cells[col][posRow] != 0 && field.cells[col][posRow] != 4)){
                                if(field.cells[col][posRow] == 1) return true;
                                posRow--;
                            }
                            while(posRow < 9 && (field.cells[col][posRow] !=0 && field.cells[col][posRow] != 4)){
                                if(field.cells[col][posRow] == 1) return true;
                                posRow++;
                            }

                            if(field.cells[col][posRow] == 1) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isShipVertical(int row, int col){
        Field field = new Field();
        int[] env = new int[] {-1, -1, -1, -1};

        if(row != 0) env[0] = row - 1;
        if(row != 9) env[1] = row + 1;
        if(col != 0) env[2] = col - 1;
        if(col != 9) env[3] = col + 1;

        for (int j : env) {
            if (j != -1) {
                if (field.cells[col][j] == 1 || field.cells[col][j] == 2) return false;
            }
        }
        return true;
    }
}
