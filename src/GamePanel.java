import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel  extends JPanel {
    public static int currentLevel;
    private ArrayList<Loot> lootList;
    private GoldMiner player;
    JLabel backroundImage;


    public GamePanel(){
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBackground(Color.red);
        this.setForeground(Color.black);


        ImageIcon image = Window.upscaleImage("src/ObjectPhotos/BackgroundOfGame.png", Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backroundImage = new JLabel();
        this.backroundImage.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backroundImage.setIcon(image);
        this.add(this.backroundImage);
        this.backroundImage.setVisible(true);


        this.setVisible(true);
    }

}
