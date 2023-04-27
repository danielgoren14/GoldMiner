import javax.swing.*;
import java.awt.*;

public class Hook extends Rectangle {
    private final ImageIcon icon = Utils.upscaleImage("src/ObjectPhotos/hook.png", Constants.HOOK_SIZE, Constants.HOOK_SIZE);
    private int hookDegree;
    boolean rightDirection;
    boolean moving;
    boolean reeling;
    private int xHeadLine;
    private int yHeadLine;


    public Hook() {
        super(Constants.WINDOW_WIDTH / 2, 120, Constants.HOOK_SIZE, Constants.HOOK_SIZE);
        this.hookDegree = 0;
        this.moving = true;
        this.rightDirection = true;
        this.reeling = false;
        this.xHeadLine = this.x - 20;
        this.yHeadLine = this.y + 10;
        hookSwing();
    }

    public void paintHook(JPanel panel, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
     /*   g.setColor(Color.BLACK);
        g.drawArc(x, y, 20, 20, 0, -180);
        g.setColor(Color.RED);
        g.drawArc(x, y, 20, 20, -hookDegree, 1);*/
        icon.paintIcon(panel, g, this.xHeadLine - 5, this.yHeadLine);
        if (reeling) {
            g.setColor(Color.BLACK);
            reel(g);
            try {
                Thread.sleep(100);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        line(g);

    }


//    public void rotateHook() {
//        new Thread(() -> {
//            while (true) {
//                if (this.moving) {
//                    if (this.rightDirection) {
//                        this.hookDegree++;
//                    } else {
//                        this.hookDegree--;
//                    }
//                    if (this.hookDegree == 180) {
//                        this.rightDirection = false;
//                    } else if (this.hookDegree == 0) {
//                        this.rightDirection = true;
//                    }
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            }
//        }).start();
//    }


    public void line(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(this.x, this.y, this.xHeadLine, this.yHeadLine);
    }

    public void reel(Graphics g) {
        moving = false;

            drawlineaaa(g,xHeadLine*10,yHeadLine*10);


        g.drawLine(this.x, this.y, this.xHeadLine, this.yHeadLine);

        reeling = false;
        moving = true;

    }
    public void drawlineaaa(Graphics g, int xx , int yy){
        double xRatio = xRatio();
  //     g.drawLine(this.x, this.y, (int) xx*xRatio, (int) yy*xRatio);
    }

    //    public static void sendHookToMine () {
//        new Thread(() -> {
//            while (true) {
//
//
//
//    }
    public void hookSwing() {
        new Thread(() -> {
            while (true) {
                while (this.moving) {
                    if (this.rightDirection) {
                        this.xHeadLine++;
                        if (this.xHeadLine == this.x + 60) {
                            this.rightDirection = false;
                        }
                    } else {
                        this.xHeadLine--;
                        if (this.xHeadLine == this.x - 60) {
                            this.rightDirection = true;
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public int distance(int x1, int x2, int y1, int y2) {
        return (int) Math.sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2);
    }

    //    double getX1() {
//        return x + d * Math.cos(hookDegree);
//    }
//
//    double getY1() {
//        return y + d * Math.sin(hookDegree);
//    }
//
//    public void whenDownArrowIsPressed() {
//        d += 20;
//    }
    public double xRatio() {
        return  (double) xHeadLine/x;
    }



}
