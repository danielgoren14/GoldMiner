import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.*;

public class MainMenu extends JPanel implements KeyListener {
    private JButton startGameButton;
    private JButton instructionsButton;
    private JButton closeInstructionButton;
    private boolean isClicked = false;

    public MainMenu() {
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.addKeyListener(this);
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        this.startGameButton = new JButton();
        this.startGameButton.setBounds(525, 150, Constants.START_BUTTON_WIDTH, Constants.START_BUTTON_HEIGHT);
        this.startGameButton.setOpaque(false);
        this.startGameButton.setContentAreaFilled(false);
        this.startGameButton.setBorderPainted(false);
        this.add(startGameButton);
        this.startGameButton.addActionListener((event) -> {
            Window.changePanel(Window.gamePanel, this);
            GamePanel.setTimeCountDown(GamePanel.GAME_TIME);
            MusicEffects.playTransition();
        });

        this.instructionsButton = new JButton();
        this.add(instructionsButton);
        this.instructionsButton.setVisible(true);
        this.instructionsButton.setBounds(65, 595, 225, 45);
        this.instructionsButton.setOpaque(false);
        this.instructionsButton.setContentAreaFilled(false);
        this.instructionsButton.setBorderPainted(false);
        this.instructionsButton.addActionListener((event) -> {
            MusicEffects.playTransition();
            isClicked = true;
            closeInstructionButton.setVisible(true);
            closeInstructionButton.setEnabled(true);
        });

        closeInstructionButton = new JButton("exit");
        closeInstructionButton.setFont(new Font("Arial", Font.BOLD, 40));
        closeInstructionButton.setOpaque(false);
        closeInstructionButton.setVisible(false);
        closeInstructionButton.setEnabled(false);
        closeInstructionButton.setContentAreaFilled(false);
        closeInstructionButton.setBorderPainted(false);
        closeInstructionButton.setBounds(BUTTON_EXIT_X, BUTTON_EXIT_Y,
                BUTTON_WIDTH, BUTTON_HEIGHT);
        this.add(closeInstructionButton);
        closeInstructionButton.addActionListener(e -> {
            MusicEffects.playTransition();
            isClicked = false;
            closeInstructionButton.setVisible(false);
            closeInstructionButton.setEnabled(false);
            this.instructionsButton.setEnabled(true);
            this.startGameButton.setEnabled(true);
        });
        this.setVisible(false);
    }

    public void instructions(Graphics graphics) {
        startGameButton.setEnabled(false);
        instructionsButton.setEnabled(false);
        ImageIcon panel = Utils.upscaleImage("src/ObjectPhotos/panel.png", Constants.INSTRUCTION_WINDOW_WIDTH,
                Constants.INSTRUCTIONS_WINDOW_HEIGHT);
        panel.paintIcon(this, graphics, Constants.INSTRUCTION_WINDOW_X, Constants.INSTRUCTION_WINDOW_Y);
        graphics.setFont(new Font("Arial", Font.BOLD, 35));
        graphics.drawString("Rules and Instructions:", Constants.WINDOW_WIDTH / 3,
                Constants.INSTRUCTION_WINDOW_Y + Constants.INSTRUCTION_MARGIN_FROM_UP);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        Utils.drawString(graphics,
                "Use your claw and reel to mine gold and other treasures underneath the \nground, your claw will swing back and forth. " +
                        "Press the down arrow to lower it. \nOnce your claw has grabbed something it will begin to reel up . Heavy objects \nlike rocks and large pieces of gold will be harder to reel up." +
                        "between levels you \ncan buy item that can help you. \n\nCollect the target amount of money by the end of the level , " +
                        "if you dont meet\nyour end goal ,it is game over, Your money carries with you from one level to\nthe next.",
                Constants.WINDOW_WIDTH / 4 - 65, Constants.INSTRUCTION_WINDOW_Y + Constants.INSTRUCTION_MARGIN_FROM_UP + 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        ImageIcon image = Utils.upscaleImage("src/ObjectPhotos/Main_Menu_Background.png",
                Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        image.paintIcon(this, g, 0, 0);
        if (isClicked) {
            instructions(g);
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_ENTER -> {
                startGameButton.doClick();
                closeInstructionButton.doClick();
            }
            case VK_ESCAPE -> {
                closeInstructionButton.doClick();
            }
            case VK_I -> {
                instructionsButton.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}

