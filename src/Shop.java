import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static java.awt.event.KeyEvent.VK_ENTER;

public class Shop extends JPanel implements KeyListener {
    private int tntPrice = 200;
    private int luckPrice = 3;
    private int strengthPrice = 5;
    private boolean tntInfo;
    private boolean luckInfo;
    private boolean strengthInfo;
    private GoldMiner player;
    private JButton buyTntButton;
    private JButton buyStrengthButton;
    private JButton buyLuckButton;
    private JButton nextLevelButton;
    //------------- FINALS-------------------
    private int START_X = 0;
    private int START_Y = 0;
    private final int STATS_ICON_X = 10;
    private final int STATS_GOLD_ICON_Y = 25;
    private final int STATS_LUCK_ICON_Y = 65;
    private final int STATS_STRENGTH_ICON_Y = 105;
    private final int STATS_TNT_ICON_Y = 145;
    private final int PRICE_MULTIPLIER = 2;
    private final int TNT_PRICE_INCREASE = 100;
    private final int INFO_SCREEN_X = 400;
    private final int INFO_SCREEN_TEXT_X = INFO_SCREEN_X + 25;
    private final int INFO_SCREEN_Y = 20;
    private final int PRODUCT_BUTTON_Y = 550;
    private final int TABLE_X = 100;
    private final int TABLE_HEIGHT = 500;
    private final int TABLE_WIDTH = Window.WINDOW_WIDTH - 200;
    private final int ITEM_WIDTH = 100;
    private final int ITEM_HEIGHT = 150;
    private final int SPACE_BETWEEN_PRODUCTS = 200;
    private final int SHOPKEEPER_X = 800;
    private final int TNT_X = SHOPKEEPER_X - SPACE_BETWEEN_PRODUCTS;
    private final int LUCK_X = TNT_X - SPACE_BETWEEN_PRODUCTS;
    private final int STRENGTH_X = LUCK_X - SPACE_BETWEEN_PRODUCTS;
    private final int CURRENT_STATS_X = 50;
    private final int CURRENT_STATS_Y = 10;
    private final int NEXT_LEVEL_BUTTON_WIDTH = 200;
    private final int NEXT_LEVEL_BUTTON_HEIGHT = 100;
    private final int NEXT_LEVEL_BUTTON_X = SHOPKEEPER_X + 45;
    private final int NEXT_LEVEL_BUTTON_Y = Window.WINDOW_HEIGHT - NEXT_LEVEL_BUTTON_HEIGHT - 50;
    private final int STATS_ICON_SIZE = 30;
    private final int BUTTON_WIDTH = 150;
    private final int BUTTON_HEIGHT = 50;
    private final ImageIcon NEXT_LEVEL_BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/Panel.png", NEXT_LEVEL_BUTTON_WIDTH, NEXT_LEVEL_BUTTON_HEIGHT);
    private final ImageIcon STATS_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/Gold.png", STATS_ICON_SIZE, STATS_ICON_SIZE);
    private final ImageIcon STATS_STRENGTH_ICON = Utils.upscaleImage("src/ObjectPhotos/Strength_Drink.png", STATS_ICON_SIZE, STATS_ICON_SIZE);
    private final ImageIcon STATS_TNT_ICON = Utils.upscaleImage("src/ObjectPhotos/TNT.png", STATS_ICON_SIZE, STATS_ICON_SIZE);
    private final ImageIcon STATS_LUCK_ICON = Utils.upscaleImage("src/ObjectPhotos/Lucky_Clover.png", STATS_ICON_SIZE, STATS_ICON_SIZE);
    private final ImageIcon STRENGTH_ICON = Utils.upscaleImage("src/ObjectPhotos/Strength_Drink.png", ITEM_WIDTH, ITEM_HEIGHT);
    private final ImageIcon TNT_ICON = Utils.upscaleImage("src/ObjectPhotos/TNT.png", ITEM_WIDTH, ITEM_HEIGHT);
    private final ImageIcon LUCK_ICON = Utils.upscaleImage("src/ObjectPhotos/Lucky_Clover.png", ITEM_WIDTH, ITEM_HEIGHT);
    private final ImageIcon SHOPKEEPER_ICON = Utils.upscaleImage("src/ObjectPhotos/Shopkeeper.jpg", 250, 255);
    private final ImageIcon TABLE = Utils.upscaleImage("src/ObjectPhotos/Shop_Table.png", TABLE_WIDTH, Window.WINDOW_HEIGHT - TABLE_HEIGHT);
    private final ImageIcon BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/Shop_Background.png", Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
    private final ImageIcon BUTTON_BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/Panel.png", BUTTON_WIDTH, BUTTON_HEIGHT);
    private final ImageIcon INFO_SCREEN = Utils.upscaleImage("src/ObjectPhotos/Panel.png", 550, 150);

    public Shop(GoldMiner player) {
        this.player = player;
        this.tntInfo = false;
        this.strengthInfo = false;
        this.luckInfo = false;
        this.setDoubleBuffered(true);
        this.setBounds(START_X, START_Y, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        this.setLayout(null);
        this.setVisible(false);
        this.addKeyListener(this);

        this.buyTntButton = new JButton("TNT " + tntPrice + "$");
        this.buyTntButton.setFont(new Font("Ariel", Font.BOLD, 17));
        this.buyTntButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyTntButton.setVerticalTextPosition(JButton.CENTER);
        this.buyTntButton.setBounds(TNT_X, PRODUCT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.buyTntButton.setIcon(BUTTON_BACKGROUND);
        this.buyTntButton.setContentAreaFilled(true);
        this.add(this.buyTntButton);
        this.buyTntButton.addActionListener((event) -> {
            if (player.canBuy(tntPrice)) {
                player.addTntCount();
                player.addCurrentMoney(-tntPrice);
                setTntPrice();
                this.buyTntButton.setText("TNT " + tntPrice + "$");
                MusicEffects.playMoneySound();
            }
        });
        this.buyTntButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                tntInfo = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                tntInfo = false;
            }
        });

        this.buyLuckButton = new JButton("LUCK " + luckPrice + "$");
        this.buyLuckButton.setFont(new Font("Ariel", Font.BOLD, 16));
        this.buyLuckButton.setBounds(LUCK_X, PRODUCT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.buyLuckButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyLuckButton.setVerticalTextPosition(JButton.CENTER);
        this.buyLuckButton.setIcon(BUTTON_BACKGROUND);
        this.buyLuckButton.setContentAreaFilled(true);
        this.add(this.buyLuckButton);
        this.buyLuckButton.addActionListener((event) -> {
            if (player.canBuy(luckPrice)) {
                player.addLuck();
                player.addCurrentMoney(-luckPrice);
                setLuckPrice();
                this.buyLuckButton.setText("LUCK " + luckPrice + "$");
                MusicEffects.playMoneySound();
            }
        });
        this.buyLuckButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                luckInfo = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                luckInfo = false;
            }
        });

        this.buyStrengthButton = new JButton("STRENGTH " + strengthPrice + "$");
        this.buyStrengthButton.setBounds(STRENGTH_X, PRODUCT_BUTTON_Y, BUTTON_WIDTH,
                BUTTON_HEIGHT);
        this.buyStrengthButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyStrengthButton.setVerticalTextPosition(JButton.CENTER);
        this.buyStrengthButton.setIcon(BUTTON_BACKGROUND);
        this.buyStrengthButton.setContentAreaFilled(true);
        this.add(this.buyStrengthButton);
        this.buyStrengthButton.addActionListener((event) -> {
            if (player.canBuy(strengthPrice)) {
                player.addStrength();
                player.addCurrentMoney(-strengthPrice);
                setStrengthPrice();
                this.buyStrengthButton.setText("STRENGTH " + strengthPrice + "$");

                MusicEffects.playMoneySound();
            }
        });
        this.buyStrengthButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                strengthInfo = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                strengthInfo = false;
            }
        });

        this.nextLevelButton = new JButton("START NEXT LEVEL");
        this.nextLevelButton.setFont(new Font("Ariel", Font.BOLD, 17));
        this.nextLevelButton.setBounds(NEXT_LEVEL_BUTTON_X, NEXT_LEVEL_BUTTON_Y,
                NEXT_LEVEL_BUTTON_WIDTH, NEXT_LEVEL_BUTTON_HEIGHT);
        this.nextLevelButton.setHorizontalTextPosition(JButton.CENTER);
        this.nextLevelButton.setVerticalTextPosition(JButton.CENTER);
        this.nextLevelButton.setIcon(NEXT_LEVEL_BACKGROUND);
        this.nextLevelButton.setContentAreaFilled(true);
        this.add(nextLevelButton);
        this.nextLevelButton.addActionListener((event) -> {
            Window.gamePanel.newLevel(Window.gamePanel.getCurrentLevel());
            Window.changePanel(Window.gamePanel, this);
        });
    }

    private void setTntPrice() {
        this.tntPrice += TNT_PRICE_INCREASE;
    }

    private void setLuckPrice() {
        this.luckPrice = this.luckPrice * PRICE_MULTIPLIER;
    }

    private void setStrengthPrice() {
        this.strengthPrice = this.strengthPrice * PRICE_MULTIPLIER;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BACKGROUND.paintIcon(this, g, 0, 0);
        TABLE.paintIcon(this, g, TABLE_X, TABLE_HEIGHT);
        SHOPKEEPER_ICON.paintIcon(this, g, SHOPKEEPER_X, SHOPKEEPER_ICON.getIconHeight());
        TNT_ICON.paintIcon(this, g, TNT_X, TABLE_HEIGHT - ITEM_HEIGHT);
        LUCK_ICON.paintIcon(this, g, LUCK_X, TABLE_HEIGHT - ITEM_HEIGHT);
        STRENGTH_ICON.paintIcon(this, g, STRENGTH_X, TABLE_HEIGHT - ITEM_HEIGHT);
        STATS_GOLD_ICON.paintIcon(this, g, STATS_ICON_X, STATS_GOLD_ICON_Y);
        STATS_LUCK_ICON.paintIcon(this, g, STATS_ICON_X, STATS_LUCK_ICON_Y);
        STATS_STRENGTH_ICON.paintIcon(this, g, STATS_ICON_X, STATS_STRENGTH_ICON_Y);
        STATS_TNT_ICON.paintIcon(this, g, STATS_ICON_X, STATS_TNT_ICON_Y);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        Utils.drawString(g, "Gold: " + player.getCurrentMoney() + "$\nLuck: " + player.getLuck() +
                "\nStrength: " + player.getStrength() + "\nTNT: " +
                player.getTntCount(), CURRENT_STATS_X, CURRENT_STATS_Y);
        if (luckInfo) {
            setInfoTab(g, "help you earn more loot per \nobject\nprice: " + luckPrice + " $");
        }
        if (tntInfo) {
            setInfoTab(g, "you can blow up objects you \ndont want \nprice: " + tntPrice + " $");
        }
        if (strengthInfo) {
            setInfoTab(g, "help you reel loot faster \nprice: " + strengthPrice + " $");
        }
        repaint();
    }

    private void setInfoTab(Graphics g, String text) {
        INFO_SCREEN.paintIcon(this, g, INFO_SCREEN_X, INFO_SCREEN_Y);
        Utils.drawString(g, text, INFO_SCREEN_TEXT_X, INFO_SCREEN_Y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case VK_ENTER -> nextLevelButton.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
