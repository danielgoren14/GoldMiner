import javax.swing.*;
import java.awt.*;

public  class Loot extends Rectangle {
    ImageIcon icon;
    private final int weight;
    private final int moneyValue;
    //-------------FINALS-------------------------//
    private static final int MINI_GOLD_WEIGHT = 1;
    private static final int MINI_GOLD_SIZE = 15;
    private static final int DIAMOND_SIZE = 15;
    private static final int DIAMOND_WEIGHT = 1;
    private static final ImageIcon GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/Gold.png",MINI_GOLD_SIZE,MINI_GOLD_SIZE);
    private static final int MID_GOLD_WEIGHT = 2;
    private static final int MID_GOLD_SIZE = 33;
    private static final ImageIcon MID_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/Gold.png",MID_GOLD_SIZE,MID_GOLD_SIZE);
    private static final int BIG_GOLD_WEIGHT = 4;
    private static final int BIG_GOLD_SIZE = 65;
    private static final ImageIcon BIG_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/Gold.png",BIG_GOLD_SIZE,BIG_GOLD_SIZE);
    private static final int BIG_ROCK_WEIGHT = 8;
    private static final int BIG_ROCK_SIZE = 65;
    private static final ImageIcon BIG_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/Rock.png",BIG_ROCK_SIZE,BIG_ROCK_SIZE);
    private static final int MINI_ROCK_WEIGHT = 3;
    private static final int MINI_ROCK_SIZE = 25;
    private static final ImageIcon MINI_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/Rock.png",MINI_ROCK_SIZE,MINI_ROCK_SIZE);
    private  static final ImageIcon DIAMOND_ICON = Utils.upscaleImage("src/ObjectPhotos/Diamond.png",DIAMOND_SIZE,DIAMOND_SIZE);

    public Loot(int weight, int width, int height, int xLocation, int yLocation, int moneyValue , ImageIcon icon)  {
        super(xLocation,yLocation,width,height);
        this.weight = weight;
        this.moneyValue = moneyValue;
        this.icon=icon;
    }
    public int getWeight() {
        return weight;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public static Loot crateMiniGold(){
       return new Loot(MINI_GOLD_WEIGHT, MINI_GOLD_SIZE, MINI_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceMiniGold() ,GOLD_ICON);
    }

    public static Loot crateMidGold(){
        return new Loot(MID_GOLD_WEIGHT, MID_GOLD_SIZE, MID_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceMidGold() ,MID_GOLD_ICON);
    }

    public static Loot crateBigGold(){
        return new Loot(BIG_GOLD_WEIGHT, BIG_GOLD_SIZE, BIG_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceBigGold() ,BIG_GOLD_ICON);
    }

    public static Loot crateBigRock(){
        return new Loot(BIG_ROCK_WEIGHT, BIG_ROCK_SIZE, BIG_ROCK_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceBigRock() ,BIG_ROCK_ICON);
    }

    public static Loot crateMiniRock(){
        return new Loot(MINI_ROCK_WEIGHT, MINI_ROCK_SIZE, MINI_ROCK_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceMiniRock() ,MINI_ROCK_ICON);
    }

    public static Loot createDiamond () {
        return new Loot(Constants.DIAMOND_WEIGHT, Constants.DIAMOND_SIZE, Constants.DIAMOND_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomDiamond(), Constants.DIAMOND_ICON);
    }

    @Override
    public String toString() {
        return "Loot{" + "icon=" + icon +
                ", weight=" + weight +
                ", width=" + width +
                ", height=" + height +
                ", moneyValue=" + moneyValue +
                '}';
    }

    public static void overLapping( ){
        for (Loot loots:GamePanel.getLootList()){
            for (Loot loot2:GamePanel.getLootList()) {
                while (!loots.equals(loot2) && (loot2.intersects(loots))) {
                    loot2.x = Utils.randomX();
                    loot2.y = Utils.randomY();
                }
            }
        }
    }
}
