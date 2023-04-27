import javax.swing.*;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    public static JPanel menuPanel;
    public static JPanel gamePanel;
    public static JPanel shopPanel;
  public  static KeyListener keyListener;

    public static void main(String[] args) {
         new Window();
    }

    public Window() {
        menuPanel = new MainMenu();
        gamePanel = new GamePanel();
        shopPanel = new Shop();
        changePanel(menuPanel,null);
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
        dd();




    }

    public static void changePanel(JPanel newPanel, JPanel oldPanel) {
        try {
            newPanel.setVisible(true);
            oldPanel.setVisible(false);
        }catch (NullPointerException e){

        }
        try {
            changeKeys(newPanel.getKeyListeners()[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

    }
    public static void changeKeys(KeyListener newKeyListener){
        keyListener= newKeyListener;

    }
    public  void dd() {
        new Thread(() -> {
            while (true){
                this.removeKeyListener(keyListener);
                this.addKeyListener(keyListener);
            }
        }).start();

    }






}