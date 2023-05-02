import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_UP;

public class GamePanel extends JPanel implements KeyListener {
    private static int currentLevel;
    private static int timeCountDown;
    private int moneyAmountToPass;
    private Hook hook;
    private GoldMiner player;
    private static ArrayList<Loot> lootList = new ArrayList<>();
    private JButton restartGame;
    private boolean tntUsed;
    //---------------finals---------------
    public static final int GAME_TIME = 40;
    private final int TOP_HEIGHT = 150;
    private final int SECOND = 1000;
    private final int GOLD_MINER_X = 535;
    private final int GOLD_MINER_Y = 10;
    private final int GAME_STATS_X = 10;
    private final int GAME_STATS_Y = 10;
    private final int GAME_OVER_X = 325;
    private final int GAME_OVER_Y = 200;
    private static final int MONEY_TARGET = 650;
    private static final int START_LEVEL = 1;
    private final int START_X_WINDOW = 0;
    private final int START_Y_WINDOW = 0;
    private final int GAME_OVER_LETTERS_SIZE = 80;
    private static ImageIcon kaboom = Utils.upscaleImage("src/ObjectPhotos/Kaboom_Effect.png",
            100,
            100);
    private final ImageIcon bottomBackground = Utils.upscaleImage("src/ObjectPhotos/Background.jpg",
            Window.WINDOW_WIDTH,
            Window.WINDOW_HEIGHT - TOP_HEIGHT);
    private final ImageIcon topBarBackground = Utils.upscaleImage("src/ObjectPhotos/Background_Top.png",
            Window.WINDOW_WIDTH,
            TOP_HEIGHT);
    private final ImageIcon BUTTON_BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/Panel.png",
            150,
            100);


    public GamePanel() {
        this.player = new GoldMiner();
        this.hook = new Hook();
        this.addKeyListener(this);
        this.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setVisible(false);
        timeCountDown = Integer.MAX_VALUE;
        this.moneyAmountToPass = MONEY_TARGET;
        currentLevel = this.START_LEVEL;
        newLevel(currentLevel);

        this.restartGame = new JButton("RESTART GAME");
        this.restartGame.setBounds(500, 400, 150, 100);
        this.restartGame.setVisible(false);
        this.restartGame.setEnabled(false);
        this.restartGame.setHorizontalTextPosition(JButton.CENTER);
        this.restartGame.setVerticalTextPosition(JButton.CENTER);
        this.restartGame.setIcon(BUTTON_BACKGROUND);
        this.restartGame.setContentAreaFilled(true);
        this.add(restartGame);
        this.restartGame.addActionListener((event) -> {
            this.restartGame.setVisible(false);
            this.restartGame.setEnabled(false);
            GamePanel newGame = new GamePanel();
            Shop newShop = new Shop(newGame.player);
            Window.TheWindow.getContentPane().add(newGame);
            Window.TheWindow.getContentPane().remove(this);
            Window.gamePanel = newGame;
            Window.TheWindow.getContentPane().add(newShop);
            Window.TheWindow.getContentPane().remove(Window.shopPanel);
            Window.shopPanel = newShop;
            Window.changePanel(newGame, this);
            newGame.timeCountDown = GAME_TIME;
        });
    }

    public static void setTimeCountDown(int timeCountDown) {
        GamePanel.timeCountDown = timeCountDown;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        this.topBarBackground.paintIcon(this, g, START_X_WINDOW, START_Y_WINDOW);
        this.bottomBackground.paintIcon(this, g, START_X_WINDOW, TOP_HEIGHT);
        this.player.minerImage.paintIcon(this, g, GOLD_MINER_X, GOLD_MINER_Y);
        g.setFont(new Font("Ariel", Font.BOLD, 15));
        Utils.drawString(g, "time : " + timeCountDown + "\ngold : " + player.getCurrentMoney() +
                "\ntarget goal: " + moneyAmountToPass + "\nTNT : " + player.getTntCount() + "\nlevel : " + currentLevel, GAME_STATS_X, GAME_STATS_Y);
        this.hook.paintHook(this, g);
        paintAllLoot(g);
        if (tntUsed) {
            System.out.println("hey");
            useTNT(g);
            tntUsed = false;
        }
        if (passLevel() && timeCountDown == 0) {
            this.restartGame.setVisible(true);
            this.restartGame.setEnabled(true);
            g.setFont(new Font("Ariel", Font.BOLD, GAME_OVER_LETTERS_SIZE));
            Utils.drawString(g, "GAME OVER", GAME_OVER_X, GAME_OVER_Y);
            this.hook.freezeHook();
        }
        repaint();
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public void nextLevelAdjustment() {
        this.moneyAmountToPass *= 1.5;
    }

    public void newLevel(int level) {
        if (level != START_LEVEL) {
            timeCountDown = GAME_TIME;
        }
        lootList.clear();
        switch (level) {
            case START_LEVEL -> createLoot(5, 4, 3, 4, 2, 0);
            case 2 -> {
                createLoot(4, 3, 3, 3, 4, 0);
                nextLevelAdjustment();
            }
            case 3 -> {
                createLoot(6, 3, 2, 4, 3, 0);
                nextLevelAdjustment();
            }
            case 4 -> {
                createLoot(7, 3, 2, 4, 3, 0);
                nextLevelAdjustment();
            }
            case 5 -> {
                createLoot(8, 3, 2, 4, 3, 2);
                nextLevelAdjustment();
            }
            case 6 -> {
                createLoot(9, 3, 2, 4, 3, 3);
                nextLevelAdjustment();
            }
            case 7 -> {
                createLoot(10, 3, 2, 4, 3, 3);
                nextLevelAdjustment();
            }
            default -> {
                createLoot(10, 5, 3, 4, 5, 3);
                nextLevelAdjustment();
            }
        }
        countDown();
        this.hook.refreshHook();
    }

    public void createLoot(int miniGold, int midGold, int bigGold, int miniRock, int bigRock, int diamond) {
        for (int i = 0; i < miniGold; i++) {
            lootList.add(Loot.crateMiniGold());
        }
        for (int i = 0; i < midGold; i++) {
            lootList.add(Loot.crateMidGold());
        }
        for (int i = 0; i < bigGold; i++) {
            lootList.add(Loot.crateBigGold());
        }
        for (int i = 0; i < miniRock; i++) {
            lootList.add(Loot.crateMiniRock());
        }
        for (int i = 0; i < bigRock; i++) {
            lootList.add(Loot.crateBigRock());
        }
        for (int i = 0; i < diamond; i++) {
            lootList.add(Loot.createDiamond());
        }
        for (int i = 0; i < 10; i++) {
            Loot.overLapping();
        }
    }

    public void countDown() {
        new Thread(() -> {
            while (timeCountDown > 0) {
                Utils.sleep(1000);
                timeCountDown--;
                if (!this.gameOver()){
                    this.hook.freezeGame();
                    Window.changePanel(Window.shopPanel,this);
                    currentLevel++;
                }
                if (timeCountDown == 10) {
                    MusicEffects.playTimeSound();
                }
            }
        }).start();
    }

    private boolean passLevel() {
        return this.moneyAmountToPass > this.player.getCurrentMoney() || timeCountDown != 0;
    }

    private void paintAllLoot(Graphics g) {
        try {
            ArrayList<Loot> temp = lootList;
            for (Loot loot : temp) {
                paintItem(g, loot);
            }
        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }


    private void paintItem(Graphics g, Loot item) {
        item.getIcon().paintIcon(this, g, item.x, item.y);
    }

    public static ArrayList<Loot> getLootList() {
        return lootList;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case VK_DOWN -> hook.setReeling(true);
            case VK_UP -> {
                if (player.getTntCount() >= 1 && this.hook.getLootCurrentlyPulling() != null) {
                    tntUsed = true;
                }
            }
            default -> System.out.println(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
