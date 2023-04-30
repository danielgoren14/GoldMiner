import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GamePanel extends JPanel implements KeyListener {
    private static int currentLevel;
    private static ArrayList<Loot> lootList = new ArrayList<>();
    private GoldMiner player;
    private Hook hook;
    private static int timeCountDown;
    private int moneyAmountToPass;



    public GamePanel() {
        this.player = new GoldMiner();
        this.hook = new Hook();
        this.addKeyListener(this);
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setVisible(false);
        this.moneyAmountToPass=750;
        currentLevel = 1;
        newLevel(currentLevel);

    }

    public static void setTimeCountDown(int timeCountDown) {
        GamePanel.timeCountDown = timeCountDown;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        ImageIcon top = Utils.upscaleImage("src/ObjectPhotos/bg_top.png",
                Constants.WINDOW_WIDTH, 150);
        top.paintIcon(this, g, 0, 0);

        ImageIcon bottom = Utils.upscaleImage("src/ObjectPhotos/bg.jpg",
                Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT - 150);
        bottom.paintIcon(this, g, 0, 150);

        ImageIcon reel = Utils.upscaleImage("src/ObjectPhotos/reel.png", 75, 50);
        reel.paintIcon(this, g, this.hook.x - 35, this.hook.y - 45);
        g.setFont(new Font("Ariel", Font.BOLD, 15));
        Utils.drawString(g,  "time : " + timeCountDown+"\ngold : " + player.getCurrentMoney() +
                "\ntarget goal: "+moneyAmountToPass+ "\nTNT : " + player.getTntCount() +"\nlevel : " +currentLevel, 10, 10);
        paintAllLoot(g);
        hook.paintHook(this, g);
        if (gameOver() && timeCountDown == 0) {
            g.setFont(new Font("Ariel", Font.BOLD, 80));
            Utils.drawString(g , "GAME OVER", Constants.WINDOW_WIDTH/3 , Constants.WINDOW_HEIGHT/3);
            this.hook.freezeGame();
            //timeCountDown=Constants.GAME_TIME;
        }
        repaint();
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }
    public void nextLevelAdjustment(){
        this.moneyAmountToPass *= 1.5;
    }
    public void newLevel(int level){
        timeCountDown=Constants.GAME_TIME;
        lootList.clear();
        switch (level) {
            case 1 -> {
                createLoot(5, 4, 3,4,2,0);
            }
            case 2 -> {
                createLoot(4, 3, 3 , 3, 4, 0);
                nextLevelAdjustment();            }
            case 3 -> {
                createLoot(6, 3, 2,4,3,0);
                nextLevelAdjustment();
            }
            case 4 -> {
                createLoot(7, 3, 2,4,3,0);
                nextLevelAdjustment();
            }
            case 5 -> {
                createLoot(8, 3, 2,4,3,2);
                nextLevelAdjustment();
            }
            case 6 -> {
                createLoot(9, 3, 2,4,3,3);
                nextLevelAdjustment();
            }
            case 7 -> {
                createLoot(10, 3, 2,4,3,3);
                nextLevelAdjustment();
            }
        }
        countDown();
    }

    public  void createLoot(int miniGold, int midGold, int bigGold, int miniRock , int bigRock , int diamond) {
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

    public boolean countDown() {
        boolean levelEnded = false;
        new Thread(() -> {
            while (timeCountDown > 0) {
                Utils.sleep(1000);
                timeCountDown--;
                if (!this.gameOver()){
                    this.hook.freezeGame();
                    Window.changePanel(Window.shopPanel,this);
                    currentLevel++;
                }
            }
        }).start();
        if (!this.gameOver()){
            levelEnded = true;
        }
        return levelEnded;
    }

    public boolean gameOver() {
        return this.moneyAmountToPass >= this.player.getCurrentMoney() || timeCountDown != 0;
    }

    private void paintAllLoot(Graphics g) {
        try {
            for (Loot loot : lootList) {
                    paintItem(g, loot);
            }
        } catch (NullPointerException e) {
        } catch (ConcurrentModificationException e) {
        }
    }


    public void paintItem(Graphics g, Loot item) {
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
            case 40 -> {
                hook.setReeling(true);
            }
            default -> System.out.println(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
