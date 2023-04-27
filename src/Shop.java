import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Shop extends JPanel implements MouseListener, KeyListener {

    //שדות
    private ImageIcon backGround;
    private ImageIcon table;
    private ImageIcon shopKeeper;
    private ImageIcon tnt;
    private ImageIcon luck;
    private ImageIcon strength;
    private JLabel showLocationOnTNT;
    private JLabel showLocationOnStrength;
    private JLabel showLocationOnLuck;
    private JButton buyTNT;
    private JButton buyStrength;
    private JButton buyLuck;
    private boolean tntIsBought;
    private boolean strengthIsBought;
    private int currentSelect;
    private ImageIcon textPanel;

    //קבועים
    final int TABLE_WIDTH = Constants.WINDOW_WIDTH - 200;
    final int TABLE_HEIGHT = 200;
    final int SHOPKEEPER_WIDTH = 800;
    //final int SHOPKEEPER_HIGH =Constants.WINDOW_HEIGHT- TABLE_HEIGHT-shopKeeper.getIconHeight();
    final int TNT_WIDTH = 100;
    final int TNT_HEIGHT = 150;
    final int SPACE_BETWEEN_PRODUCTS = 200;
    final int TNT_X = SHOPKEEPER_WIDTH - SPACE_BETWEEN_PRODUCTS;
    final int LUCK_X = TNT_X - SPACE_BETWEEN_PRODUCTS;
    final int STRENGTH_X = LUCK_X - SPACE_BETWEEN_PRODUCTS;
    final int STRENGTH_ID = 1;
    final int LUCK_ID = 2;
    final int TNT_ID = 3;
    final int PRODUCT_Y = 550;

    public Shop() {
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backGround = Utils.upscaleImage("src/ObjectPhotos/img.png",
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);
        this.setLayout(null);


        this.table = Utils.upscaleImage("src/ObjectPhotos/shop_table.png", TABLE_WIDTH, TABLE_HEIGHT);

        this.shopKeeper = Utils.upscaleImage("src/ObjectPhotos/shopkeeper.jpg", 250, 255);

        this.tnt = Utils.upscaleImage("src/ObjectPhotos/tnt.png", TNT_WIDTH, TNT_HEIGHT);

//        this.showLocationOnTNT = new JLabel();
//        this.showLocationOnTNT.setBounds(500,500,1,1);
//        this.showLocationOnTNT.setText("Press enter \n to buy TNT");
//        this.showLocationOnTNT.setHorizontalTextPosition(JLabel.BOTTOM);
//        this.showLocationOnTNT.setVerticalTextPosition(JLabel.CENTER);
//        this.add(showLocationOnTNT);
//        this.showLocationOnTNT.setVisible(true);

        this.luck = Utils.upscaleImage("src/ObjectPhotos/lucky_clover.png",
                TNT_WIDTH,
                TNT_HEIGHT);

        this.strength = Utils.upscaleImage("src/ObjectPhotos/strength_drink.png",
                TNT_WIDTH,
                TNT_HEIGHT);

        // this.addMouseListener(new MainMenu());
        this.setVisible(false);
        this.addKeyListener(this);

        this.textPanel = Utils.upscaleImage("src/ObjectPhotos/panel.png", 150, 50);
        //this.textPanel.
        this.buyTNT = new JButton();
        this.buyTNT.setBounds(TNT_X, PRODUCT_Y, 150, 50);
        this.buyTNT.setIcon(textPanel);
        this.buyTNT.setContentAreaFilled(true);
        this.add(this.buyTNT);

        this.buyLuck = new JButton();
        this.buyLuck.setBounds(LUCK_X, PRODUCT_Y, 150, 50);
        this.buyLuck.setIcon(textPanel);
        this.buyLuck.setContentAreaFilled(true);
        this.add(this.buyLuck);

        this.buyStrength = new JButton();
        this.buyStrength.setBounds(STRENGTH_X, PRODUCT_Y, 150, 50);
        this.buyStrength.setIcon(textPanel);
        this.buyStrength.setContentAreaFilled(true);
        this.add(this.buyStrength);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.backGround.paintIcon(this, g, 0, 0);
        this.table.paintIcon(this, g, 100, Constants.WINDOW_HEIGHT - TABLE_HEIGHT);
        this.shopKeeper.paintIcon(this, g, SHOPKEEPER_WIDTH, Constants.WINDOW_HEIGHT - TABLE_HEIGHT - shopKeeper.getIconHeight());
        this.tnt.paintIcon(this, g, TNT_X, Constants.WINDOW_HEIGHT - TABLE_HEIGHT - shopKeeper.getIconHeight() + 120);
        this.luck.paintIcon(this, g, LUCK_X, Constants.WINDOW_HEIGHT - TABLE_HEIGHT - shopKeeper.getIconHeight() + 120);
        this.strength.paintIcon(this, g, STRENGTH_X, Constants.WINDOW_HEIGHT - TABLE_HEIGHT - shopKeeper.getIconHeight() + 120);
        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HI",TNT_X,PRODUCT_Y);
        //Graphics2D g2 = (Graphics2D) g;
        repaint();
    }

    private boolean isBought() {
        boolean result = false;

        return result;
    }

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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


    private int getCurrentSelect(int changeInValue) {
        int temp = this.currentSelect + changeInValue;
        int result = temp;
        if (temp == 0) {
            result = 1;
        } else if (temp == 4) {
            result = 3;
        }
        return result;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'z':
                System.out.println("buyTNT");
                break;
            case 'x':
                System.out.println("buy luck");
                break;
            case 'c':
                System.out.println("buy");
                break;
        }

    }

}
