public class GoldMiner {
    private int requiredMoney;
    private int currentMoney;
    private int tntCount;
    private float strength;
    private float luck;

    public GoldMiner() {
        this.currentMoney = 0;
        this.tntCount = 0;
        this.strength = 1;
        this.luck = 1;
    }

    public void setCurrentMoney(int amount) {
        this.currentMoney += amount;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setTntCount() {
        this.tntCount++;
    }

    public void setStrength() {
        this.strength += 0.05;
    }

    public void setLuck(int luck) {
        this.luck +=0.05;
    }
}
