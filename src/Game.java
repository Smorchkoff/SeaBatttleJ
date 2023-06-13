import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Game extends Database {
    Scanner in = new Scanner(System.in);
    PrintStream out = new PrintStream(System.out);
    static User OneUser = new User();
    Player playerOne;
    AI aiOne;
    public Game(Player playerOne, AI aiOne){
        this.playerOne = playerOne;
        this.aiOne = aiOne;
    }

    public void startGame() throws IOException, InterruptedException {
        Display display = new Display();
        while (true) {
            display.clearScreen();
            display.viewField(playerOne.getField());
            display.displayRadar(playerOne.getRadar());
            if (playerOne.shoot()) {
                out.println("Nice cock!");
                continue;
            }
            while (true) {
                if (aiOne.shootAI()) out.println("Out ship is compromised!");
                else break;
            }
        }
    }

    //TODO Сделать реализацию окончания игры
    public static void endGame() throws SQLException, Exception {

        if (isWinner("YES")) OneUser.wins++;
        else OneUser.loses++;

        query = String.format("UPDATE userdb.users SET wins = %d, loses = %d WHERE nickname = '%s'", OneUser.wins, OneUser.loses, OneUser.nickName);
        statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

    private static boolean isWinner(String Res) {
        return Res.equals("YES");
    }

    public void winLoseRatioCalc(int wins, int loses) {
        User user = new User();
        try {
            user.winLoseRatio = ((double) wins / loses);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " - деление на ноль");
        }
    }
}
