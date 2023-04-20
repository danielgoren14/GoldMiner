import javax.swing.*;
import java.awt.*;

public class Shop extends JPanel {

    //שדות
    private ImageIcon backGround;
    private ImageIcon table;
    private ImageIcon shopKeeper;
    private ImageIcon tnt;
    private ImageIcon luck;
    private ImageIcon strength;
    private JButton buyTNT;
    private JButton buyStrength;
    private JButton buyLuck;

    //קבועים
    final int TABLE_WIDTH = Constants.WINDOW_WIDTH-200;
    final int TABLE_HEIGHT = 200;
    final int SHOPKEEPER_WIDTH = 800;

    public Shop() {
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.backGround = Utils.upscaleImage("src/ObjectPhotos/img.png",
                Constants.WINDOW_WIDTH,
                Constants.WINDOW_HEIGHT);
        this.setLayout(null);
        this.setVisible(false);

        this.table = Utils.upscaleImage("src/ObjectPhotos/shop_table.png",
                TABLE_WIDTH,
                TABLE_HEIGHT);

       this.shopKeeper = Utils.upscaleImage("src/ObjectPhotos/shopkeeper.jpg",
               250
               ,255);



    }

    public void paint(Graphics g) {
        super.paint(g);
        this.backGround.paintIcon(this, g, 0, 0);
        this.table.paintIcon(this,g,100,Constants.WINDOW_HEIGHT- TABLE_HEIGHT);
        this.shopKeeper.paintIcon(this,g,SHOPKEEPER_WIDTH,Constants.WINDOW_HEIGHT- TABLE_HEIGHT-shopKeeper.getIconHeight());

        repaint();
    }

}
