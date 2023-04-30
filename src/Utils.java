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

    public static void sleep(int milSc){
        try {
            Thread.sleep(milSc);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static int randomMiniGold(){
        Random random = new Random();
        return random.nextInt(10,21);
    }
    public static int randomMidGold(){
        Random random = new Random();
        return random.nextInt(50,101);
    }
    public static int randomBigGold(){
        Random random = new Random();
        return random.nextInt(250,501);
    }
    public static int randomMiniRock(){
        Random random = new Random();
        return random.nextInt(5,16);
    }
    public static int randomBigRock(){
        Random random = new Random();
        return random.nextInt(25,41);
    }
    public static int randomX(){
        Random random = new Random();
       return random.nextInt(0,Constants.WINDOW_WIDTH-100);
    }
    public static int randomY(){
        Random random = new Random();
        return random.nextInt(160,Constants.WINDOW_HEIGHT-150);
    }

}
