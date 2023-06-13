import java.io.PrintStream;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Application extends Database {
    static int wins;
    static int loses;
    static User OneUser;
    static AI ai;

    public static void main(String[] args) throws Exception {
        Field c = new Field();
        fillField();
        c.viewField();
        OneUser = input();
        endGame();
        System.out.println(OneUser.wins);
    }
    public static void startGame() {
        Field c = new Field();
        Navy n = new Navy();
    }
    public static void fillField() {
        Field field = new Field();
        for (int i = 0; i < Field.SIZE; i++) {
            Arrays.fill(field.cells[i], '.');
        }
        System.out.println();
    }

    public boolean shoot() {
        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);
        String command = in.nextLine();
        Field fieldPlayer = new Field();
        Field fieldAI = new Field();
        Radar radar = new Radar(OneUser.nickName, fieldPlayer, fieldAI);

        if (Arrays.asList(radar.getCommands()).contains(command)) {
            out.println(command.toUpperCase().charAt(0));

            int row = radar.getCommandMap().get((Character.toString(command.toUpperCase().charAt(0))));
            int col = Integer.parseInt(Character.toString(command.charAt(1))) - 1;

            if (radar.getField().isFreeCell(row,col)) return radar.getField().regShot(row,col);
            else {
                out.println("Your order is not clear?\n Try again");
            }
        }
        shoot();
        return false;
    }

    //TODO Сделать реализацию окончания игры
    public static void endGame() throws SQLException, Exception {

        if (isWinner("YES")) OneUser.wins++;
        else OneUser.loses++;

        query = String.format("UPDATE userdb.users SET wins = %d, loses = %d WHERE nickname = '%s'",OneUser.wins, OneUser.loses, OneUser.nickName);
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    private static boolean isWinner(String Res) {
        return Res.equals("YES");
    }
    public void winLoseRatioCalc(int wins, int loses){
        User user = new User();
        try{
            user.winLoseRatio = ((double) wins /loses);
        }
        catch(Exception e){
            System.out.println(e.getMessage()+" - деление на ноль");
        }
    }
}
