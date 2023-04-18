import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel{
    JButton startGameButton;
    JButton instructionsButton;
    JLabel backgroundImage;
    private boolean start;

    public boolean isStart() {
        return start;
    }

    public MainMenu() {
        this.start=false;
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        ImageIcon image = Window.upscaleImage("src/ObjectPhotos/MainMenuBackground.png", Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backgroundImage = new JLabel();
        this.backgroundImage.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backgroundImage.setIcon(image);
        this.add(this.backgroundImage);
        this.backgroundImage.setVisible(true);

        this.startGameButton = new JButton("Start");
        this.startGameButton.setBounds(525, 150, Constants.START_BUTTON_WIDTH, Constants.START_BUTTON_HEIGHT);
        this.startGameButton.setOpaque(false);
        this.startGameButton.setContentAreaFilled(false);
        this.startGameButton.setBorderPainted(false);

        this.startGameButton.addActionListener((event) -> {
            //this.add(gamePanel);
            Window.changePanel(Window.gamePanel,this);
        });
        this.add(startGameButton);


        this.instructionsButton = new JButton("Instructions");
        this.instructionsButton.setVisible(true);
        this.instructionsButton.setBounds(65, 595, 225, 45);
        this.instructionsButton.setOpaque(false);
        this.instructionsButton.setContentAreaFilled(false);
        this.instructionsButton.setBorderPainted(false);
        this.instructionsButton.addActionListener((event) -> {

        });
        this.add(instructionsButton);
    }

    /*public static ImageIcon upscaleImage(String source, int width , int height){
        return new ImageIcon(new ImageIcon(source).getImage().getScaledInstance(
                width, height, Image.SCALE_DEFAULT));
    }*/

}

