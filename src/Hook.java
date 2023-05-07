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
    private double xSave;
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
        startHook();
    }

    public Loot getLootCurrentlyPulling() {
        return lootCurrentlyPulling;
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
        this.xSave = this.xEnd;
        try {
            while (this.isReeling) {
                checkIfHookHitSomething();
                Utils.sleep(1);
                if (!hitSomething) {
                    reelingHookDown();
                } else {
                    reelingHookUp((int) ((3 * this.lootCurrentlyPulling.getWeight()) / GoldMiner.getStrength()));
                }
                if (isOutOfBorder()) {
                    reelingHookUp(4);
                }
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    private void reelingHookDown() {
        this.xEnd += ((this.xEnd - this.x) / (this.yEnd - this.y));
        this.yEnd++;
        Utils.sleep(2);
    }

    private void reelingHookUp(int time) {
        while (!(this.xEnd == this.xSave && this.yEnd == this.y + HOOK_Y_MARGIN)) {
            this.xEnd -= ((this.xEnd - this.x) / (this.yEnd - this.y));
            this.yEnd--;
            Utils.sleep(time);
            if (this.xEnd <= this.xSave && this.yEnd <= this.y + HOOK_Y_MARGIN) {
                this.hitSomething = false;
                this.isReeling = false;
                break;
            }
        }
    }

    private void startHook() {
        new Thread(() -> {
            while (true) {
                sendHook();
                this.yEnd = this.y + HOOK_Y_MARGIN;
                this.xEnd = this.xSave;
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
            loot.x = (int) this.xEnd - (loot.icon.getIconWidth() / 2);
            loot.y = (int) this.yEnd;
        }).start();
    }


    public void setReeling(boolean reeling) {
        this.isReeling = reeling;
    }

    private void checkIfHookHitSomething() {
        new Thread(() -> {
            try {
                for (Loot loot : GamePanel.getLootList()) {
                    if (new Rectangle((int) this.xEnd, (int) this.yEnd, HOOK_SIZE, HOOK_SIZE).intersects(loot)) {
                        this.hitSomething = true;
                        this.lootCurrentlyPulling = loot;
                        reelingLootReachedMiner();
                        Utils.sleep(1);
                        this.lootCurrentlyPulling = null;
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void reelingLootReachedMiner() {
        while (!(this.xEnd <= this.xSave && this.yEnd <= this.y + HOOK_Y_MARGIN)) {
            try {
                    if (lootCurrentlyPulling != null) {
                        dragLoot(lootCurrentlyPulling);
                        if (new Rectangle((int) this.xEnd, this.y + HOOK_Y_MARGIN, CATCHER_WIDTH, CATCHER_HEIGHT).intersects(lootCurrentlyPulling) && lootCurrentlyPulling != null) {
                            GoldMiner.addCurrentMoney((int) (lootCurrentlyPulling.getMoneyValue() * GoldMiner.getLuck()));
                            GamePanel.getLootList().remove(lootCurrentlyPulling);
                            MusicEffects.playMoneySound();
                            break;
                        }
                    }

            } catch (NullPointerException e) {
                e.printStackTrace();
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
        this.xEnd = this.xSave;
        this.yEnd = this.y + HOOK_Y_MARGIN;
        this.isReeling = false;
        this.isSwinging = false;
        this.hitSomething = false;
    }
}
