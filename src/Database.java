import java.sql.*;
import java.util.Scanner;

public class Database {
    static String url = "jdbc:mysql://localhost:3306/userdb";
    static String user = "root";
    static String pass = "root";
    static Connection connection;
    static String query;
    static PreparedStatement statement;
    static ResultSet resultSet;
    static String UserNickname;
    public static User input() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите свой никнейм: ");
        UserNickname = sc.nextLine();
        User u = new User(UserNickname);
        query = String.format("SELECT * FROM userdb.users where nickname = '%s'", UserNickname);
        try {
            connection = DriverManager.getConnection(url, user, pass);
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getRow() != 0) {
                u.nickName = resultSet.getString("nickname");
                u.wins = resultSet.getInt("wins");
                u.loses = resultSet.getInt("loses");
                System.out.println(u.nickName);
                System.out.println(u.wins);
                System.out.println(u.loses);
            } else {
                statement = connection.prepareStatement(String.format("INSERT INTO userdb.users VALUES (DEFAULT,'%s',0,0)", UserNickname));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }
}
