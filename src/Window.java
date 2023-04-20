import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static JPanel menuPanel;
    public static JPanel gamePanel;
    public static JPanel shopPanel;
    //public  static JPanel shopPanel;

    public static void main(String[] args) {
         new Window();
    }

    public Window() {
        menuPanel = new MainMenu();
        gamePanel = new GamePanel();
        shopPanel = new Shop();
        this.setTitle("The Gold Miner");
        this.setIconImage(new ImageIcon("src/ObjectPhotos/GoldMinerIcon.jpg").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setFocusable(true);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.getContentPane().add(menuPanel);
        this.getContentPane().add(gamePanel);
        this.getContentPane().add(shopPanel);
        this.addKeyListener(new MainMenu());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void changePanel(JPanel newPanel, JPanel oldPanel) {
        newPanel.setVisible(true);
        oldPanel.setVisible(false);
    }






}