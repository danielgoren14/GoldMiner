import javax.swing.*;
import java.awt.*;

public  class Loot extends Rectangle {
    ImageIcon icon;
    private final int weight;
    private final int moneyValue;

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
       return new Loot(Constants.MINI_GOLD_WEIGHT, Constants.MINI_GOLD_SIZE, Constants.MINI_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomMiniGold() ,Constants.GOLD_ICON);
    }
    public static Loot crateMidGold(){
        return new Loot(MID_GOLD_WEIGHT, MID_GOLD_SIZE, MID_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomPriceMidGold() ,MID_GOLD_ICON);
    }
    public static Loot crateBigGold(){
        return new Loot(Constants.BIG_GOLD_WEIGHT, Constants.BIG_GOLD_SIZE, Constants.BIG_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomBigGold() ,Constants.BIG_GOLD_ICON);
    }
    public static Loot crateBigRock(){
        return new Loot(Constants.BIG_ROCK_WEIGHT, Constants.BIG_ROCK_SIZE, Constants.BIG_ROCK_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomBigRock() ,Constants.BIG_ROCK_ICON);

    }
    public static Loot crateMiniRock(){
        return new Loot(Constants.MINI_ROCK_WEIGHT, Constants.MINI_ROCK_SIZE, Constants.MINI_ROCK_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomMiniRock() ,Constants.MINI_ROCK_ICON);
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
