import java.util.Random;
public class AI {
    private Field field;
    private Field radar;

    public AI(Field field, Field radar){
        this.radar = radar;
        this.field = field;
    }
    public boolean shootAI(){
        Random random = new Random();
        int row = random.nextInt(10);
        int col = random.nextInt(10);

        if(radar.isFreeCell(row,col)){
            return radar.regShot(row,col);
        }

        shootAI();
        return false;
    }
}
