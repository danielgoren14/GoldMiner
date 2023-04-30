import javax.swing.*;
import java.awt.*;
import java.util.ConcurrentModificationException;

public class Hook extends Rectangle {
    private final ImageIcon icon = Utils.upscaleImage("src/ObjectPhotos/hook.png", Constants.HOOK_SIZE, Constants.HOOK_SIZE);
    private boolean rightDirection;
    private boolean moving;
    private boolean reeling;
    private boolean hitSomething;
    private double xHeadLine;
    private double yHeadLine;
   //private GoldMiner owner;
    private Loot lootCurrentlyPulling;

    public Hook() {
        super(Constants.WINDOW_WIDTH / 2, 120, Constants.HOOK_SIZE, Constants.HOOK_SIZE);
        this.moving = true;
        this.rightDirection = true;
        this.reeling = false;
        this.xHeadLine = this.x;
        this.yHeadLine = this.y + 20;
        this.hitSomething = false;
       // this.owner = owner;
        this.lootCurrentlyPulling = null;
        hookMovement();
    }

    public boolean isOutOfBorder() {
        return xHeadLine >= Constants.WINDOW_WIDTH || xHeadLine <= 0 || yHeadLine >= Constants.WINDOW_HEIGHT;
    }

    public void paintHook(JPanel panel, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        icon.paintIcon(panel, g, (int) this.xHeadLine - 5, (int) this.yHeadLine);
        line(g);
        panel.setDoubleBuffered(true);
    }

    public void line(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.x, this.y, (int) this.xHeadLine, (int) this.yHeadLine);
    }

    public void sendHook() {
        try {
            while (this.reeling) {
                hitLoot();
                Utils.sleep(1);
                if (!hitSomething) {
                    moveHookDownwards();
                } else {
                    moveHookForwards((int)((3*this.lootCurrentlyPulling.getWeight() ) / GoldMiner.getStrength()));
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
        this.xHeadLine += ((this.xHeadLine - this.x) / (this.yHeadLine - this.y));
        this.yHeadLine++;
        Utils.sleep(2);
    }
    private void moveHookForwards(int time) {
        while (!(this.xHeadLine == this.x && this.yHeadLine == this.y)) {
            this.xHeadLine -= ((this.xHeadLine - this.x) / (this.yHeadLine - this.y));
            this.yHeadLine--;
            Utils.sleep(time);
            if ((this.xHeadLine <= this.x && this.yHeadLine <= this.y)) {
                this.hitSomething = false;
                this.reeling = false;
                this.lootCurrentlyPulling =null;
                break;
            }
        }
    }

    public void hookMovement() {
        new Thread(() -> {
            while (true) {
                sendHook();
                this.yHeadLine = this.y +15;
                if (this.moving) {
                    if (this.rightDirection) {
                        this.xHeadLine++;
                        if (this.xHeadLine == this.x + 100) {
                            this.rightDirection = false;
                        }
                    } else {
                        this.xHeadLine--;
                        if (this.xHeadLine == this.x - 100) {
                            this.rightDirection = true;
                        }
                    }
                    Utils.sleep(8);
                }
            }
        }).start();
    }

    private void dragLoot(Loot loot) {
        new Thread(() -> {
            loot.x = (int) this.xHeadLine;
            loot.y = (int) this.yHeadLine;
        }).start();
    }


    public void setReeling(boolean reeling) {
        this.reeling = reeling;
    }

    private void hitLoot() {
        new Thread(() -> {
            try {
                for (Loot loot : GamePanel.getLootList()) {
                    if (new Rectangle((int) this.xHeadLine, (int) this.yHeadLine, Constants.HOOK_SIZE, Constants.HOOK_SIZE).intersects(loot)) {
                        this.hitSomething = true;
                        this.lootCurrentlyPulling = loot;
                        extracted(loot);
                        break;
                    }
                }
            }catch (ConcurrentModificationException e){
            }
        }).start();
    }

    private void extracted(Loot loot) {
        while (!(this.xHeadLine <= this.x && this.yHeadLine <= this.y)) {
            dragLoot(loot);
            if (new Rectangle(this.x, this.y, 180, 10).intersects(loot)) {
                GoldMiner.addCurrentMoney((int) ((loot.getMoneyValue() *GoldMiner.getLuck())));
                GamePanel.getLootList().remove(loot);
                MusicEffects.playMoneySound();
                break;
            }
        }
    }
    public void freezeGame(){
        this.xHeadLine=this.x;
        this.yHeadLine=this.y+20;
        this.reeling = false;
        this.moving = true;
        this.hitSomething = false;
        this.lootCurrentlyPulling =null;
    }
}
