import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.util.Random;

public class Utils {
    public static boolean checkCollision (Rectangle rect1, Rectangle rect2) {
        boolean result = false;
        if (rect1.intersects(rect2)) {
            result = true;
        }
        return result;
    }
    public static ImageIcon upscaleImage(String source, int width, int height) {
        return new ImageIcon(new ImageIcon(source).getImage().getScaledInstance(
                width, height, Image.SCALE_DEFAULT));
    }

    public static void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

    public static void sleep(int milSc) {
        try {
            Thread.sleep(milSc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomPriceMiniGold() {
        return new Random().nextInt(MIN_MINI_GOLD, MAX_MINI_GOLD);
    }

    public static int randomPriceMidGold() {
        return new Random().nextInt(MIN_MID_GOLD, MAX_MID_GOLD);
    }

    public static int randomPriceBigGold() {
        return new Random().nextInt(MIN_BIG_GOLD, MAX_BIG_GOLD);
    }

    public static int randomPriceMiniRock() {
        return new Random().nextInt(MIN_MINI_ROCK, MAX_MINI_ROCK);
    }

    public static int randomPriceBigRock() {
        return new Random().nextInt(MIN_BIG_ROCK, MAX_BIG_ROCK);
    }

    public static int randomPriceDiamond() {
        return new Random().nextInt(MIN_DIAMOND, MAX_DIAMOND);
    }

    public static int randomX() {
        return new Random().nextInt(MIN_X, MAX_X);
    }

    public static int randomY() {
        return new Random().nextInt(MIN_Y, MAX_Y);
    }
}
