import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Field c = new Field();
        c.fillField();
        c.viewField();
    }

    public static void input() {
        String UserNickname;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите свой никнейм: ");
        UserNickname = sc.nextLine();
        User u = new User(UserNickname);
    }

    //TODO Сделать реализацию выстрела
    public void shoot(){

    }

    public static void startGame() {
       Field c = new Field();
       Navy n = new Navy();
    }

    //TODO Сделать реализацию окончания игры
}

// TODO Пошёл нахуй