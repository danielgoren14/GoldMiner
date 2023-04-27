public class GoldMiner {
    private int currentMoney;
    private int tntCount;
    private double strength;
    private double luck;

    public GoldMiner() {
        this.currentMoney = 1000000;
        this.tntCount = 0;
        this.strength = 1;
        this.luck = 1;
    }

    public void addCurrentMoney(int amount) {
        this.currentMoney += amount;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void addTntCount() {
        this.tntCount++;
    }

    public int getTntCount() {
        return tntCount;
    }

    public double getStrength() {
        double temp =Math.round(strength*100);
        return temp /100;
    }

    public double getLuck() {
        double temp =Math.round(luck*100);
        return temp /100;
    }

    public void addStrength() {
        this.strength += 0.05;
    }

    public void addLuck() {
        this.luck += 0.05;
    }
    public boolean canBuy(int cost){
        return cost<currentMoney;
    }
}
