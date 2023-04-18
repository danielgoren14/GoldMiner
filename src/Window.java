import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    public  static JPanel menuPanel;
    public  static JPanel gamePanel;
    //public  static JPanel shopPanel;

    public static void main(String[] args) {
    Window window = new Window();
}
    public Window(){
        menuPanel = new MainMenu();
        gamePanel = new GamePanel();
        this.setTitle("The Gold Miner");
        Image icon = new ImageIcon("ObjectPhotos/Icon.png").getImage();
        this.setIconImage(icon);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        setFocusable(true);
        this.setResizable(false);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        this.getContentPane().add(menuPanel);
     this.getContentPane().add(gamePanel);
        this.setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

    }
    public  static void  changePanel(JPanel newPanel , JPanel oldPanel){
        newPanel.setVisible(true);
        oldPanel.setVisible(false);
    }

    public static ImageIcon upscaleImage(String source, int width , int height){
        return new ImageIcon(new ImageIcon(source).getImage().getScaledInstance(
                width, height, Image.SCALE_DEFAULT));
    }






}