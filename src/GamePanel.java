import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {
    public static int currentLevel;
    private static ArrayList<Loot> lootList = new ArrayList<>();
    private GoldMiner player;
    private Hook hook;
    private int timeCountDown;
    private boolean gameInProgress;
    private int moneyAmountToPass;
    private boolean test = false;


    public GamePanel() {
        hook = new Hook();
        this.addKeyListener(this);
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setDoubleBuffered(true);
        this.setLayout(null);
        this.setVisible(false);
//        if (gameInProgress == false){
//            Window.changePanel(Window.shopPanel,this);
//        }
        crateLoot();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        ImageIcon top = Utils.upscaleImage("src/ObjectPhotos/bg_top.png",
                Constants.WINDOW_WIDTH, 150);
        top.paintIcon(this, g, 0, 0);

        ImageIcon bottom = Utils.upscaleImage("src/ObjectPhotos/bg_level_A.png",
                Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT - 150);
        bottom.paintIcon(this, g, 0, 150);

        ImageIcon reel = Utils.upscaleImage("src/ObjectPhotos/reel.png",
                30, 50);
        reel.paintIcon(this, g, Constants.WINDOW_WIDTH / 2 - 50, 100);

        hook.paintHook(this, g);

        paintAllLoot(g);
        repaint();
    }

    public void crateLoot() {
        for (int i = 0; i < 15; i++) {
            lootList.add(Loot.crateBigGold());
            lootList.add(Loot.crateMidGold());
            lootList.add(Loot.crateMiniGold());
            lootList.add(Loot.crateMiniRock());
            lootList.add(Loot.crateBigRock());
        }

        for (int i = 0; i < 5; i++) {
            Loot.overLapping();
        }
    }

    public boolean countDown() {
        boolean levelEnded = false;
        new Thread(() -> {
            switch (currentLevel) {
                case 0 -> {
                    while (this.gameInProgress) {
                        try {
                            Thread.sleep(1000);
                            this.timeCountDown--;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        return levelEnded;
    }

    public boolean gameOver() {
        //check the !this.gameInProgress == false condition
        if (!this.gameInProgress == false && this.moneyAmountToPass > player.getCurrentMoney()) {
            return false;
        } else return true;
    }

    private void paintAllLoot(Graphics g) {
        try {
            for (Loot loot : lootList
            ) {
                paintItem(g, loot);
            }
        } catch (NullPointerException e) {
        }
    }

    /*public void paintHook(Graphics g) {
        hook.getIcon().paintIcon(this, g, hook.x, hook.y);
    }*/

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
               hook.reeling=true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
