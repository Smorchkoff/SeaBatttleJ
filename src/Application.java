import java.sql.*;
import java.util.Scanner;

/*
* ЕСЛИ ЧТО-ТО КОММИТИШЬ, КОММИТЬ КОНКРЕТНЫЙ ФАЙЛ, А НЕ ВСЁ ПОДРЯД
*/



public class Application {
    static Field field = new Field();
    static String url = "jdbc:mysql://localhost:3306/userdb";
    static String user = "root";
    static String pass = "root";
    public static void main(String[] args) {
        field.fillField();
        field.viewField();
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
    public void shoot(){

    }

    public static void startGame() {
       Navy n = new Navy();
    }

    //TODO Сделать реализацию окончания игры
}

// TODO Пошёл нахуй