import javax.swing.*;
import java.awt.*;

public class Hook extends Rectangle {
    private final ImageIcon icon = Utils.upscaleImage("src/ObjectPhotos/hook.png", Constants.HOOK_SIZE, Constants.HOOK_SIZE);
    //private int hookDegree;
    boolean rightDirection;
    boolean moving;
    boolean reeling;
    private double xHeadLine;
    private double yHeadLine;


    public Hook() {
        super(Constants.WINDOW_WIDTH / 2, 120, Constants.HOOK_SIZE, Constants.HOOK_SIZE);
        //  this.hookDegree = 90;
        this.moving = true;
        this.rightDirection = true;
        this.reeling = false;
        this.xHeadLine = this.x /*- 20*/;
        this.yHeadLine = this.y + 10;
        //sendHook();
        hookMovement();

    }

    public boolean isCollies() {
        boolean result = xHeadLine >= Constants.WINDOW_WIDTH || xHeadLine <= 0 || yHeadLine >= Constants.WINDOW_HEIGHT;
        return result;
    }

    public void paintHook(JPanel panel, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        icon.paintIcon(panel, g, (int) this.xHeadLine - 5, (int) this.yHeadLine);
        line(g);
    }

    public void line(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.x, this.y, (int) this.xHeadLine, (int) this.yHeadLine);
    }

    public void sendHook() {

        try {
            while (this.reeling) {
                this.xHeadLine += ((this.xHeadLine - this.x) / (this.yHeadLine - this.y));
                this.yHeadLine++;
                Utils.sleep(2);
                if (isCollies()) {
                    while (!(this.xHeadLine == this.x || this.yHeadLine == this.y)) {
                        this.xHeadLine -= ((this.xHeadLine - this.x) / (this.yHeadLine - this.y));
                        this.yHeadLine--;
                        Utils.sleep(5);
                        if ((this.xHeadLine <= this.x && this.yHeadLine <= this.y)) {
                            this.reeling = false;
                            break;
                        }
                    }
                }
            }
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }
    }

    public void hookMovement() {
        new Thread(() -> {
            while (true) {
                sendHook();
                this.yHeadLine = this.y + 10;
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
                    Utils.sleep(10);
                }
            }
        }).start();
    }


}
