import java.util.*;
public class Player {
    Scanner in = new Scanner(System.in);
    private Field field;
    private Field radar;
    final private String name;
    final public String[] commands = new String[]{
            "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10",
            "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
            "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10",
            "D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
            "E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10",
            "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10",
            "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
            "H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10",
            "I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10",
            "J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10",
    };
    final private HashMap<String, Integer> commandMap = new HashMap<>(){{
        put("A", 0); put("B", 1);
        put("C", 2); put("D", 3);
        put("E", 4); put("F", 5);
        put("G", 6); put("H", 7);
        put("I", 8); put("J", 9);
    }};
    public Player(String name, Field field, Field radar){
        this.name = name;
        this.field = field;
        this.radar = radar;
    }
    public boolean shoot() {
        String command = in.nextLine();

        if (Arrays.asList(commands).contains(command)) {
            System.out.println(command.toUpperCase().charAt(0));

            int row = commandMap.get((Character.toString(command.toUpperCase().charAt(0))));
            int col = Integer.parseInt(Character.toString(command.charAt(1)))-1;

            if (radar.isFreeCell(row, col)) return radar.regShot(row, col);
            else {
                System.out.println("Your order is not clear?\n Try again");
            }
        }
        shoot();
        return false;
    }
    public Field getField() { return field; }
    public Field getRadar() { return radar; }
    public String[] getCommands() { return commands; }
    public HashMap<String, Integer> getCommandMap() { return commandMap; }
}
