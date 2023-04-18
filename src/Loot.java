public class Loot {
    private int elevatingTime;
    private boolean isCaught;
    private int width;
    private int height;
    private int xLocation;
    private int yLocation;
    private int moneyValue;

    public Loot(int elevatingTime, boolean isCaught, int width, int height, int xLocation, int yLocation, int moneyValue) {
        this.elevatingTime = elevatingTime;
        this.isCaught = isCaught;
        this.width = width;
        this.height = height;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.moneyValue = moneyValue;
    }

    @Override
    public String toString() {
        return "Loot{" +
                "elevatingTime=" + elevatingTime +
                ", isCaught=" + isCaught +
                ", width=" + width +
                ", height=" + height +
                ", xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                ", moneyValue=" + moneyValue +
                '}';
    }
}
