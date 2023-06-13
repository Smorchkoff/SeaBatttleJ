import java.util.Random;

public class Navy {
    static final int[] DECK = {1,1,1,1,2,2,2,3,3,4};
    Field field = new Field();
    Random random = new Random();
    Ship ship;
    Ship[] arrShips = new Ship[10];
    static Ship[] initShip() {
        return new Ship[0];
    }

    private boolean canSetShip(Ship ship){

        int minRow = Math.max(0, ship.getxPosition() - 1);
        int minCol = Math.max(0, ship.getyPosition() - 1);
        int maxRow = Math.min(field.cells.length - 1, ship.getxPosition()-1 + (ship.isDirection() ? 0 : ship.getDeck()));
        int maxCol = Math.min(field.cells.length - 1, ship.getyPosition()-1 + (ship.isDirection() ? ship.getDeck() : 0));

        if(ship.getxPosition() < 0 || ship.getyPosition() < 0
                || field.cells.length <= ship.getxPosition() || field.cells.length <= ship.getyPosition()){
            return false;
        }
        if(ship.isDirection() && field.cells.length <= ship.getyPosition() + ship.getDeck()){
            return false;
        }
        if(!ship.isDirection() && field.cells.length <= ship.getxPosition() + ship.getDeck()) {
            return false;
        }

        for(int row = minRow; row <= maxRow; row++){
            for(int col = minCol; col <= maxCol; col++){
                if(!field.isCellEmpty(row,col)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isShipAlive(int row, int col){
        int[] env = new int[]{-1,-1,-1,-1};

        if(row != 0) env[0] = row - 1;
        if(row != 9) env[1] = row - 1;
        if(col != 0) env[2] = col - 1;
        if(col != 9) env[3] = col - 1;
        // TODO Изменить логику отображение на логику класса Display
        if(isShipVertical(row, col)) {
            for(int i = 2; i < env.length; i++){
                if(env[i] != -1){
                    if(field.cells[env[i]][row] == 'o') return true;

                    else if(field.cells[env[i]][row] == 'x'){
                        int posCol = col;

                        while (posCol > 0 && (field.cells[posCol][row] != '.' && field.cells[posCol][row] != '*')){
                            if(field.cells[posCol][row] == 1) return true;
                            posCol--;
                        }
                        while (posCol < 9 && (field.cells[posCol][row] != '.' && field.cells[posCol][row] != '*')){
                            if(field.cells[posCol][row] == 1) return true;
                            posCol++;
                        }
                        if (field.cells[posCol][row] == 1) return true;
                    }
                } else {
                    for(i = 0; i < env.length; i++){
                        if(env[i] != 1) {
                            if (field.cells[col][env[i]] == 'o') return true;
                        } else if(field.cells[col][env[i]] == 'x'){
                            int posRow = row;

                            while(posRow > 0 && (field.cells[col][posRow] != '.' && field.cells[col][posRow] != '*')){
                                if(field.cells[col][posRow] == 'o') return true;
                                posRow--;
                            }
                            while(posRow < 9 && (field.cells[col][posRow] != '.' && field.cells[col][posRow] != '*')){
                                if(field.cells[col][posRow] == 'o') return true;
                                posRow++;
                            }

                            if(field.cells[col][posRow] == 'o') return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isShipVertical(int row, int col){
        int[] env = new int[] {-1, -1, -1, -1};

        if(row != 0) env[0] = row - 1;
        if(row != 9) env[1] = row + 1;
        if(col != 0) env[2] = col - 1;
        if(col != 9) env[3] = col + 1;

        for (int j : env) {
            if (j != -1) {
                if (field.cells[col][j] == 'o' || field.cells[col][j] == 'x') return false;
            }
        }
        return true;
    }
}
