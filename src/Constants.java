import javax.swing.*;

public class Constants {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static final int BUTTON_EXIT_X = 535;
    public static final int BUTTON_EXIT_Y = 520;
    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 50;
    public static final int START_BUTTON_WIDTH = 250;
    public static final int START_BUTTON_HEIGHT = 225;
    public static final int INSTRUCTION_WINDOW_WIDTH = 800;
    public static final int INSTRUCTIONS_WINDOW_HEIGHT = 500;
    public static final int INSTRUCTION_WINDOW_X = 200;
    public static final int INSTRUCTION_WINDOW_Y = 100;
    public static final int INSTRUCTION_MARGIN_FROM_UP = 50;
    public static final int MINI_GOLD_WEIGHT = 1;
    public static final int MINI_GOLD_SIZE = 15;
    public static final int DIAMOND_SIZE = 15;
    public static final int DIAMOND_WEIGHT = 1;
    public static final ImageIcon GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png"
            , MINI_GOLD_SIZE,
            MINI_GOLD_SIZE);
    public static final int MID_GOLD_WEIGHT = 2;
    public static final int MID_GOLD_SIZE = 33;
    public static final ImageIcon MID_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png"
            , MID_GOLD_SIZE,
            MID_GOLD_SIZE);
    public static final int BIG_GOLD_WEIGHT = 4;
    public static final int BIG_GOLD_SIZE = 65;
    public static final ImageIcon BIG_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png",
            BIG_GOLD_SIZE,
            BIG_GOLD_SIZE);
    public static final int BIG_ROCK_WEIGHT = 8;
    public static final int BIG_ROCK_SIZE = 65;
    public static final ImageIcon BIG_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/rock_big.png",
            BIG_ROCK_SIZE,
            BIG_ROCK_SIZE);
    public static final int MINI_ROCK_WEIGHT = 3;
    public static final int MINI_ROCK_SIZE = 25;
    public static final ImageIcon MINI_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/rock_big.png",
            MINI_ROCK_SIZE,
            MINI_ROCK_SIZE);

    public static final ImageIcon DIAMOND_ICON = Utils.upscaleImage("src/ObjectPhotos/Diamond.png",
            DIAMOND_SIZE,
            DIAMOND_SIZE);
    public static final int HOOK_SIZE = 20;
    public static final int SHOP_PRODUCT_BUTTON_Y = 550;
    public static final int SHOP_TABLE_X = 100;
    public static final int SHOP_TABLE_HEIGHT = 500;
    public static final int SHOP_TABLE_WIDTH = Constants.WINDOW_WIDTH - 200;
    public static final int SHOP_ITEM_WIDTH = 100;
    public static final int SHOP_ITEM_HEIGHT = 150;
    public static final int SPACE_BETWEEN_PRODUCTS = 200;
    public static final int SHOPKEEPER_X = 800;
    public static final int TNT_X = SHOPKEEPER_X - SPACE_BETWEEN_PRODUCTS;
    public static final int LUCK_X = TNT_X - SPACE_BETWEEN_PRODUCTS;
    public static final int STRENGTH_X = LUCK_X - SPACE_BETWEEN_PRODUCTS;
    public static final ImageIcon SHOP_STRENGTH_ICON = Utils.upscaleImage("src/ObjectPhotos/strength_drink.png",
            Constants.SHOP_ITEM_WIDTH,
            Constants.SHOP_ITEM_HEIGHT);
    public static final ImageIcon SHOP_TNT_ICON = Utils.upscaleImage("src/ObjectPhotos/tnt.png",
            Constants.SHOP_ITEM_WIDTH,
            Constants.SHOP_ITEM_HEIGHT);
    public static final ImageIcon SHOP_LUCK_ICON = Utils.upscaleImage("src/ObjectPhotos/lucky_clover.png",
            Constants.SHOP_ITEM_WIDTH,
            Constants.SHOP_ITEM_HEIGHT);
    public static final ImageIcon SHOPKEEPER_ICON = Utils.upscaleImage("src/ObjectPhotos/shopkeeper.jpg",
            250,
            255);
    public static final ImageIcon SHOP_TABLE = Utils.upscaleImage("src/ObjectPhotos/shop_table.png",
            Constants.SHOP_TABLE_WIDTH,
            Constants.WINDOW_HEIGHT - Constants.SHOP_TABLE_HEIGHT);
    public static final ImageIcon SHOP_BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/img.png",
            Constants.WINDOW_WIDTH,
            Constants.WINDOW_HEIGHT);
    public static final int SHOP_BUTTON_WIDTH = 150;
    public static final int SHOP_BUTTON_HEIGHT = 50;
    public static final ImageIcon SHOP_BUTTON_BACKGROUND = Utils.upscaleImage("src/ObjectPhotos/panel.png",
            SHOP_BUTTON_WIDTH,
            SHOP_BUTTON_HEIGHT);
    public static final ImageIcon SHOP_INFO_SCREEN = Utils.upscaleImage("src/ObjectPhotos/panel.png",
            550,
            150);
    public static final int SHOP_INFO_SCREEN_X = 400;
    public static final int SHOP_INFO_SCREEN_TEXT_X = SHOP_INFO_SCREEN_X + 25;
    public static final int SHOP_INFO_SCREEN_Y = 20;
    public static final int SHOP_CURRENT_STATS_X = 50;
    public static final int SHOP_CURRENT_STATS_Y = 10;
    public static final int SHOP_NEXT_LEVEL_BUTTON_WIDTH = 200;
    public static final int SHOP_NEXT_LEVEL_BUTTON_HEIGHT = 100;
    public static final int SHOP_NEXT_LEVEL_BUTTON_X = SHOPKEEPER_X+45;
    public static final int SHOP_NEXT_LEVEL_BUTTON_Y = WINDOW_HEIGHT - SHOP_NEXT_LEVEL_BUTTON_HEIGHT-50;
    public static final ImageIcon SHOP_NEXT_LEVEL_BACKGROUND= Utils.upscaleImage("src/ObjectPhotos/panel.png",
            SHOP_NEXT_LEVEL_BUTTON_WIDTH,
            SHOP_NEXT_LEVEL_BUTTON_HEIGHT);
    public static final int SHOP_STATS_ICON_SIZE = 30;
    public static final ImageIcon SHOP_STATS_GOLD_ICON= Utils.upscaleImage("src/ObjectPhotos/nugget.png",
            SHOP_STATS_ICON_SIZE,
            SHOP_STATS_ICON_SIZE);
    public static final ImageIcon SHOP_STATS_STRENGTH_ICON= Utils.upscaleImage("src/ObjectPhotos/strength_drink.png",
            SHOP_STATS_ICON_SIZE,
            SHOP_STATS_ICON_SIZE);
    public static final ImageIcon SHOP_STATS_TNT_ICON= Utils.upscaleImage("src/ObjectPhotos/tnt.png",
            SHOP_STATS_ICON_SIZE,
            SHOP_STATS_ICON_SIZE);
    public static final ImageIcon SHOP_STATS_LUCK_ICON= Utils.upscaleImage("src/ObjectPhotos/lucky_clover.png",
            SHOP_STATS_ICON_SIZE,
            SHOP_STATS_ICON_SIZE);
    public static final int SHOP_STATS_ICON_X= 10;
    public static final int SHOP_STATS_GOLD_ICON_Y= 25;
    public static final int SHOP_STATS_LUCK_ICON_Y= 65;
    public static final int SHOP_STATS_STRENGTH_ICON_Y= 105;
    public static final int SHOP_STATS_TNT_ICON_Y= 145;
    public static final int SHOP_PRICE_MULTIPLIER= 2;
    public static final int SHOP_TNT_PRICE_INCREASE= 100;
    public static final int GAME_TIME=60;
}
