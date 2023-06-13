import static java.lang.System.out;
import java.io.IOException;
import java.util.HashMap;

public class Display {
    private static final String columns = "   A B C D E F G H I J\n";
    private static final String row = "%2d %s %s %s %s %s %s %s %s %s %s\n";
    private static final HashMap<Integer, String> fieldSymbols = new HashMap<>(){{
        put(0, " ");  // пустая клетка
        put(1, "■");  // корабль
        put(2, "□"); // подбитый корабль
        put(3, "X"); // разрушенный корабль
        put(4, "o"); // промах
    }};
    private final HashMap<Integer, String> radarSymbols = new HashMap<>(){{
        put(0, " ");  // пустая клетка
        put(1, " ");  // корабль
        put(2, "□"); // подбитый корабль
        put(3, "X"); // разрушенный корабль
        put(4, "o"); // промах
    }};
    public void clearScreen() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else if (os.contains("nix") || os.contains("nux")) {
            new ProcessBuilder("terminal", "/c", "clear").inheritIO().start().waitFor();
        }
        out.println();
    }
    public void viewField(Field field) {
        StringBuilder fieldImage = new StringBuilder();
        fieldImage.append(columns);
        for (int i = 0; i < Field.size()[0]; i++) {
            fieldImage.append(String.format(row, i + 1,
                    fieldSymbols.get(field.getCell(i, 0)), fieldSymbols.get(field.getCell(i, 1)),
                    fieldSymbols.get(field.getCell(i, 2)), fieldSymbols.get(field.getCell(i, 3)),
                    fieldSymbols.get(field.getCell(i, 4)), fieldSymbols.get(field.getCell(i, 5)),
                    fieldSymbols.get(field.getCell(i, 6)), fieldSymbols.get(field.getCell(i, 7)),
                    fieldSymbols.get(field.getCell(i, 8)), fieldSymbols.get(field.getCell(i, 9))));
        }
        fieldImage.append("\n");
        out.print(fieldImage);
    }
    public void displayRadar(Field radar) {
        StringBuilder radarImage = new StringBuilder();
        radarImage.append(columns);
        for (int i = 0; i < Field.size()[0]; i++) {
            radarImage.append(String.format(row, i + 1,
                    radarSymbols.get(radar.getCell(i, 0)), radarSymbols.get(radar.getCell(i, 1)),
                    radarSymbols.get(radar.getCell(i, 2)), radarSymbols.get(radar.getCell(i, 3)),
                    radarSymbols.get(radar.getCell(i, 4)), radarSymbols.get(radar.getCell(i, 5)),
                    radarSymbols.get(radar.getCell(i, 6)), radarSymbols.get(radar.getCell(i, 7)),
                    radarSymbols.get(radar.getCell(i, 8)), radarSymbols.get(radar.getCell(i, 9))));
        }
        radarImage.append("\n");
        out.print(radarImage);
    }
}
