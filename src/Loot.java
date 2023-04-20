import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public  class Loot extends Rectangle {
    ImageIcon icon;
    private int weight;
  //  private int width;
 //   private int height;
    //private int xLocation;
  //  private int yLocation;
    private int moneyValue;

    public Loot(int weight, int width, int height, int xLocation, int yLocation, int moneyValue , ImageIcon icon)  {
        super(xLocation,yLocation,width,height);
        this.weight = weight;
       // this.width = width;
      //  this.height = height;
    //    this.xLocation = xLocation;
     //   this.yLocation = yLocation;
        this.moneyValue = moneyValue;
        this.icon=icon;
    }
    public int getWeight() {
        return weight;
    }
    /*  public int getWidth() {
         return width;
      /*   public int getHeight() {
         return height;
     }*/
 /*   public int getXLocation() {
        return xLocation;
    }*/

  /*  public int getYLocation() {
        return yLocation;
    }*/

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
        return new Loot(Constants.MID_GOLD_WEIGHT, Constants.MID_GOLD_SIZE, Constants.MID_GOLD_SIZE, Utils.randomX(), Utils.randomY(), Utils.randomMidGold() ,Constants.MID_GOLD_ICON);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Loot{");
        sb.append("icon=").append(icon);
        sb.append(", weight=").append(weight);
        sb.append(", width=").append(width);
        sb.append(", height=").append(height);
      /*  sb.append(", xLocation=").append(xLocation);
        sb.append(", yLocation=").append(yLocation);*/
        sb.append(", moneyValue=").append(moneyValue);
        sb.append('}');
        return sb.toString();
    }

    public static void overLapping( ){
        for (Loot loots:GamePanel.getLootList()){
            for (Loot loot2:GamePanel.getLootList()) {
                boolean a =(loot2.intersects(loots));
                boolean b=   !loots.equals(loot2);
                boolean c=a&&b;
                while (!loots.equals(loot2) && (loot2.intersects(loots)) ) {
                    loot2.x = Utils.randomX();
                    loot2.y = Utils.randomY();
                    System.out.println("true");

                }
            }
        }
    }


}
