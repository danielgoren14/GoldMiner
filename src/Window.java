import javax.swing.*;
        import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static MainMenu menuPanel;
    public static GamePanel gamePanel;
    public static Shop shopPanel;
    public static KeyListener keyListener;

    public static void main(String[] args) {
        new Window();
    }

    public Window() {
        GoldMiner goldMiner = new GoldMiner();
        menuPanel = new MainMenu();
        gamePanel = new GamePanel();
        shopPanel = new Shop(goldMiner);
        changePanel(menuPanel, null);
        this.addKeyListener(keyListener);
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
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        // refreshKeyListener();


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