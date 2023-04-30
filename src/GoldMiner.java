public class  GoldMiner {
    private  static  int currentMoney;
    private static int tntCount;
    private static double strength;
    private static double luck;

    public GoldMiner() {
        currentMoney = 0;
        tntCount = 0;
        strength = 1;
        luck = 1;
    }

    public static void addCurrentMoney(int amount) {
        currentMoney += amount;
    }

    public  int getCurrentMoney() {
        return currentMoney;
    }

    public void addTntCount() {
        tntCount++;
    }

    public int getTntCount() {
        return tntCount;
    }

    public  static double getStrength() {
        double temp = Math.round(strength*100);
        return temp / 100;
    }

    public static double getLuck() {
        double temp = Math.round(luck*100);
        return temp /100;
    }



    public void addStrength() {
        strength += 0.15;
    }

    public void addLuck() {
        luck += 0.10;
    }
    public boolean canBuy(int cost){
        return cost<currentMoney;
    }
}
