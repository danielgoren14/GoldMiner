import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;

public class Hook extends Rectangle {
    private boolean swingingRightDirection;
    private boolean isSwinging;
    private boolean isReeling;
    private boolean hitSomething;
    private double xEnd;
    private double yEnd;


    private Loot lootCurrentlyPulling;

    //------------- FINALS-------------------

    private static final int HOOK_SIZE = 20;
    private static final int HOOK_X = Window.WINDOW_WIDTH / 2;
    private static final int HOOK_Y = 110;
    private static final int HOOK_Y_MARGIN = 20;
    private static final int HOOK_SWING_MARGIN = 100;
    private final ImageIcon icon = Utils.upscaleImage("src/ObjectPhotos/Hook.png", HOOK_SIZE, HOOK_SIZE);
    private final int CATCHER_WIDTH = 180;
    private final int CATCHER_HEIGHT = 40;

    public Hook() {
        super(HOOK_X, HOOK_Y, HOOK_SIZE, HOOK_SIZE);
        this.isSwinging = true;
        this.swingingRightDirection = true;
        this.isReeling = false;
        this.xEnd = this.x;
        this.yEnd = this.y + HOOK_Y_MARGIN;
        this.hitSomething = false;
        this.lootCurrentlyPulling = null;
        hookMovement();
    }

    public Loot getLootCurrentlyPulling() {
        return lootCurrentlyPulling;
    }

    public void setLootCurrentlyPulling(Loot loot) {
        this.lootCurrentlyPulling = loot;
    }

    private boolean isOutOfBorder() {
        return xEnd >= Window.WINDOW_WIDTH || xEnd <= 0 || yEnd >= Window.WINDOW_HEIGHT;
    }

    public void paintHook(JPanel panel, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        icon.paintIcon(panel, g, (int) this.xEnd - HOOK_SIZE / 2, (int) this.yEnd);
        line(g);
        panel.setDoubleBuffered(true);
    }

    private void line(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.x, this.y, (int) this.xEnd, (int) this.yEnd);
    }

    private void sendHook() {
        try {
            while (this.isReeling) {
                hitLoot();
                Utils.sleep(1);
                if (!hitSomething) {
                    moveHookDownwards();
                } else {
                    moveHookForwards((int) ((3 * this.lootCurrentlyPulling.getWeight()) / GoldMiner.getStrength()));
                }
                if (isOutOfBorder()) {
                    moveHookForwards(4);
                }
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private void moveHookDownwards() {
        this.xEnd += ((this.xEnd - this.x) / (this.yEnd - this.y));
        this.yEnd++;
        Utils.sleep(2);
    }

    private void moveHookForwards(int time) {
        while (!(this.xEnd == this.x && this.yEnd == this.y)) {
            this.xEnd -= ((this.xEnd - this.x) / (this.yEnd - this.y));
            this.yEnd--;
            Utils.sleep(time);
            if (this.xEnd <= this.xSave && this.yEnd <= this.y + HOOK_Y_MARGIN) {
                this.hitSomething = false;
                this.isReeling = false;
                this.lootCurrentlyPulling = null;
                break;
            }
        }
    }

    private void hookMovement() {
        new Thread(() -> {
            while (true) {
                sendHook();
                this.yEnd = this.y + HOOK_Y_MARGIN;
                hookSwing();
            }
        }).start();
    }

    private void hookSwing() {
        if (this.isSwinging) {
            if (this.swingingRightDirection) {
                this.xEnd++;
                if (this.xEnd == this.x + HOOK_SWING_MARGIN) {
                    this.swingingRightDirection = false;
                }
            } else {
                this.xEnd--;
                if (this.xEnd == this.x - HOOK_SWING_MARGIN) {
                    this.swingingRightDirection = true;
                }
            }
            Utils.sleep(8);
        }
    }

    private void dragLoot(Loot loot) {
        new Thread(() -> {
            loot.x = (int) this.xEnd;
            loot.y = (int) this.yEnd;
        }).start();
    }


    public void setReeling(boolean reeling) {
        this.isReeling = reeling;
    }

    private void hitLoot() {
        new Thread(() -> {
            try {
                for (Loot loot : GamePanel.getLootList()) {
                    if (new Rectangle((int) this.xEnd, (int) this.yEnd, HOOK_SIZE, HOOK_SIZE).intersects(loot)) {
                        this.hitSomething = true;
                        this.lootCurrentlyPulling = loot;
                        reachToMiner();
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void reachToMiner() {
        while (!(this.xEnd <= this.x && this.yEnd <= this.y)) {
            try {
                if (lootCurrentlyPulling != null) {
                    dragLoot(lootCurrentlyPulling);
                    if (new Rectangle(this.x, this.y, CATCHER_WIDTH, CATCHER_HEIGHT).intersects(lootCurrentlyPulling)) {
                        GoldMiner.addCurrentMoney((int) (lootCurrentlyPulling.getMoneyValue() * GoldMiner.getLuck()));
                        MusicEffects.playMoneySound();
                        GamePanel.getLootList().remove(lootCurrentlyPulling);
                        break;
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }


    public void refreshHook() {
        this.xEnd = this.xSave;
        this.yEnd = this.y + HOOK_Y_MARGIN;
        this.isReeling = false;
        this.isSwinging = true;
        this.hitSomething = false;
    }

    public void freezeHook() {
        this.xEnd = this.x;
        this.yEnd = this.y + HOOK_Y_MARGIN;
        this.isReeling = false;
        this.isSwinging = false;
        this.hitSomething = false;
        this.lootCurrentlyPulling = null;
    }

    public double getXEnd() {
        return xEnd;
    }

    public double getYEnd() {
        return yEnd;
    }

}
