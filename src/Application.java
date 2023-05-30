import javax.xml.transform.Result;
import java.sql.*;
import java.util.Scanner;

public class Application {
    static String url = "jdbc:mysql://localhost:3306/userdb";
    static String user = "root";
    static String pass = "root";
    public static void main(String[] args) {
        Cell c = new Cell();
        c.fillField();
        c.viewField();
        String query = "SELECT * FROM userdb.users";
        try {
            Connection conn = DriverManager.getConnection(url,user, pass);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void input() {
        String UserNickname;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите свой никнейм: ");
        UserNickname = sc.nextLine();
        User u = new User(UserNickname);
        String query;
    }

    //TODO Сделать реализацию выстрела

    public static void startGame() {
       Cell c = new Cell();
       Navy n = new Navy();
    }

    //TODO Сделать реализацию окончания игры
}

// TODO Пошёл нахуй