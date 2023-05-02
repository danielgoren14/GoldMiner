import javax.swing.*;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static Window TheWindow = new Window();
    public static MainMenu menuPanel;
    public static GamePanel gamePanel;
    public static Shop shopPanel;
    private static KeyListener keyListener;

    //------------- FINALS-------------------
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;

    public static void main(String[] args) {
    }

    public Window() {
        GoldMiner goldMiner = new GoldMiner();
        menuPanel = new MainMenu();
        gamePanel = new GamePanel();
        shopPanel = new Shop(goldMiner);
        changePanel(menuPanel, null);
        this.addKeyListener(keyListener);
        this.setTitle("The Gold Miner");
        this.setIconImage(new ImageIcon("src/ObjectPhotos/Game_Icon.jpg").getImage());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setFocusable(true);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.getContentPane().add(menuPanel);
        this.getContentPane().add(gamePanel);
        this.getContentPane().add(shopPanel);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void changePanel(JPanel newPanel, JPanel oldPanel) {
        try {
            newPanel.setVisible(true);
            newPanel.requestFocusInWindow();
            oldPanel.setVisible(false);
        } catch (NullPointerException e) {
        }
        try {
            changeKeys(newPanel.getKeyListeners()[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public static void changeKeys(KeyListener newKeyListener) {
        keyListener = newKeyListener;
    }

}