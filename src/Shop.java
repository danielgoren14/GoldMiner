import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public void setTntPrice() {
        this.tntPrice += Constants.SHOP_TNT_PRICE_INCREASE;
    }

    public void setLuckPrice() {
        this.luckPrice = this.luckPrice * Constants.SHOP_PRICE_MULTIPLIER;
    }

    public void setStrengthPrice() {
        this.strengthPrice = this.strengthPrice * Constants.SHOP_PRICE_MULTIPLIER;
    }

    public Shop(GoldMiner player) {
        this.player = player;
        MusicEffects musicEffects = new MusicEffects();
        this.tntInfo = false;
        this.strengthInfo = false;
        this.luckInfo = false;
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setLayout(null);
        this.setVisible(false);
        this.addKeyListener(this);


        this.buyTntButton = new JButton("TNT " + tntPrice + "$");
        this.buyTntButton.setFont(new Font("Ariel", Font.BOLD, 17));
        this.buyTntButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyTntButton.setVerticalTextPosition(JButton.CENTER);
        this.buyTntButton.setBounds(Constants.TNT_X, Constants.SHOP_PRODUCT_BUTTON_Y, Constants.SHOP_BUTTON_WIDTH, Constants.SHOP_BUTTON_HEIGHT);
        this.buyTntButton.setIcon(Constants.SHOP_BUTTON_BACKGROUND);
        this.buyTntButton.setContentAreaFilled(true);
        this.add(this.buyTntButton);
        this.buyTntButton.addActionListener((event) -> {
            if (player.canBuy(tntPrice)) {
                player.addTntCount();
                player.addCurrentMoney(-tntPrice);
                setTntPrice();
                this.buyTntButton.setText("TNT " + tntPrice + "$");
                musicEffects.playMoneySound();
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
        this.buyLuckButton.setBounds(Constants.LUCK_X, Constants.SHOP_PRODUCT_BUTTON_Y, Constants.SHOP_BUTTON_WIDTH, Constants.SHOP_BUTTON_HEIGHT);
        this.buyLuckButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyLuckButton.setVerticalTextPosition(JButton.CENTER);
        this.buyLuckButton.setIcon(Constants.SHOP_BUTTON_BACKGROUND);
        this.buyLuckButton.setContentAreaFilled(true);
        this.add(this.buyLuckButton);
        this.buyLuckButton.addActionListener((event) -> {
            if (player.canBuy(luckPrice)) {
                player.addLuck();
                player.addCurrentMoney(-luckPrice);
                setLuckPrice();
                this.buyLuckButton.setText("LUCK " + luckPrice + "$");
                musicEffects.playMoneySound();
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
        this.buyStrengthButton.setBounds(Constants.STRENGTH_X, Constants.SHOP_PRODUCT_BUTTON_Y, Constants.SHOP_BUTTON_WIDTH,
                Constants.SHOP_BUTTON_HEIGHT);
        this.buyStrengthButton.setHorizontalTextPosition(JButton.CENTER);
        this.buyStrengthButton.setVerticalTextPosition(JButton.CENTER);
        this.buyStrengthButton.setIcon(Constants.SHOP_BUTTON_BACKGROUND);
        this.buyStrengthButton.setContentAreaFilled(true);
        this.add(this.buyStrengthButton);
        this.buyStrengthButton.addActionListener((event) -> {
            if (player.canBuy(strengthPrice)) {
                player.addStrength();
                player.addCurrentMoney(-strengthPrice);
                setStrengthPrice();
                this.buyStrengthButton.setText("STRENGTH " + strengthPrice + "$");

                musicEffects.playMoneySound();
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
        this.nextLevelButton.setBounds(Constants.SHOP_NEXT_LEVEL_BUTTON_X, Constants.SHOP_NEXT_LEVEL_BUTTON_Y,
                Constants.SHOP_NEXT_LEVEL_BUTTON_WIDTH, Constants.SHOP_NEXT_LEVEL_BUTTON_HEIGHT);
        this.nextLevelButton.setHorizontalTextPosition(JButton.CENTER);
        this.nextLevelButton.setVerticalTextPosition(JButton.CENTER);
        this.nextLevelButton.setIcon(Constants.SHOP_NEXT_LEVEL_BACKGROUND);
        this.nextLevelButton.setContentAreaFilled(true);
        this.add(nextLevelButton);
        this.nextLevelButton.addActionListener((event) -> {
            //TODO
        });

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Constants.SHOP_BACKGROUND.paintIcon(this, g, 0, 0);
        Constants.SHOP_TABLE.paintIcon(this, g, Constants.SHOP_TABLE_X, Constants.SHOP_TABLE_HEIGHT);
        Constants.SHOPKEEPER_ICON.paintIcon(this, g, Constants.SHOPKEEPER_X, Constants.SHOPKEEPER_ICON.getIconHeight());
        Constants.SHOP_TNT_ICON.paintIcon(this, g, Constants.TNT_X, Constants.SHOP_TABLE_HEIGHT - Constants.SHOP_ITEM_HEIGHT);
        Constants.SHOP_LUCK_ICON.paintIcon(this, g, Constants.LUCK_X, Constants.SHOP_TABLE_HEIGHT - Constants.SHOP_ITEM_HEIGHT);
        Constants.SHOP_STRENGTH_ICON.paintIcon(this, g, Constants.STRENGTH_X, Constants.SHOP_TABLE_HEIGHT - Constants.SHOP_ITEM_HEIGHT);
        Constants.SHOP_STATS_GOLD_ICON.paintIcon(this, g, Constants.SHOP_STATS_ICON_X, Constants.SHOP_STATS_GOLD_ICON_Y);
        Constants.SHOP_STATS_LUCK_ICON.paintIcon(this, g, Constants.SHOP_STATS_ICON_X, Constants.SHOP_STATS_LUCK_ICON_Y);
        Constants.SHOP_STATS_STRENGTH_ICON.paintIcon(this, g, Constants.SHOP_STATS_ICON_X, Constants.SHOP_STATS_STRENGTH_ICON_Y);
        Constants.SHOP_STATS_TNT_ICON.paintIcon(this, g, Constants.SHOP_STATS_ICON_X, Constants.SHOP_STATS_TNT_ICON_Y);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 35));
        Utils.drawString(g, "Gold: " + player.getCurrentMoney() + "$\nLuck: " + player.getLuck() + "\nStrength: " + player.getStrength() + "\nTNT: " +
                player.getTntCount(), Constants.SHOP_CURRENT_STATS_X, Constants.SHOP_CURRENT_STATS_Y);
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

    public void setInfoTab(Graphics g, String text) {
        Constants.SHOP_INFO_SCREEN.paintIcon(this, g, Constants.SHOP_INFO_SCREEN_X, Constants.SHOP_INFO_SCREEN_Y);
        Utils.drawString(g, text, Constants.SHOP_INFO_SCREEN_TEXT_X, Constants.SHOP_INFO_SCREEN_Y);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'z' -> System.out.println("buyTNT");
            case 'x' -> System.out.println("buy luck");
            case 'c' -> System.out.println("buy");
        }
    }
}
