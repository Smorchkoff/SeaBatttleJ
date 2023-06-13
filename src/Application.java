import java.util.Scanner;

public class Application extends Database {
    static User OneUser;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        Field fieldOne = new Field();
        Field fieldTwo = new Field();
        OneUser = input();
        Player playerOne = new Player(OneUser.nickName, fieldOne, fieldTwo);
        AI aiOne = new AI(fieldTwo, fieldOne);
        Game gameSession = new Game(playerOne, aiOne);
        gameSession.startGame();

        System.out.println(OneUser.wins);
    }
}