import java.sql.*;
import java.util.Scanner;

public class Application {
    static String url = "jdbc:mysql://localhost:3306/userdb";
    static String user = "root";
    static String pass = "root";
    static Connection conn;
    static String query;
    static PreparedStatement stmt;
    static ResultSet rs;
    static String UserNickname;
    static int wins;
    static int loses;
    static User OneUser;

    public static void main(String[] args) throws Exception {
        Field c = new Field();
        c.fillField();
        c.viewField();
        OneUser = input();
        endGame();
        System.out.println(OneUser.wins);
    }

    public static User input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите свой никнейм: ");
        UserNickname = sc.nextLine();
        User u = new User(UserNickname);
        query = String.format("SELECT * FROM userdb.users where nickname = '%s'", UserNickname);
        try {
            conn = DriverManager.getConnection(url,user, pass);
            stmt = conn.prepareStatement(query);

            rs = stmt.executeQuery(query);
            rs.next();
            if (rs.getRow() != 0) {
                u.nickName = rs.getString("nickname");
                u.wins = rs.getInt("wins");
                u.loses = rs.getInt("loses");
                System.out.println(u.nickName);
                System.out.println(u.wins);
                System.out.println(u.loses);
            } else {
                stmt = conn.prepareStatement(String.format("INSERT INTO userdb.users VALUES (DEFAULT,'%s',0,0)",UserNickname));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    //TODO Сделать реализацию выстрела
    public void shoot(){

    }

    public static void startGame() {
       Field c = new Field();
       Navy n = new Navy();
    }

    //TODO Сделать реализацию окончания игры
    public static void endGame() throws SQLException,Exception {

        if (isWinner("YES")){
            OneUser.wins++;
        } else{
            OneUser.loses++;
        }

        query = String.format("UPDATE userdb.users SET wins = %d, loses = %d WHERE nickname = '%s'",OneUser.wins, OneUser.loses, OneUser.nickName);
        stmt = conn.prepareStatement(query);
        stmt.executeUpdate();
    }

    private static boolean isWinner(String Res) {
        if (Res == "YES") {
            return true;
        }
        return false;
    }
}

// TODO Пошёл нахуй