import javax.swing.*;

public class Constants {

    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static final String GAME_RULE = """
             Controls 
             Drop claw by clicking down arrow , \n
             \n"toss dynamite with up arrow key (when dragging an item).
            """;


    public static final int BUTTON_EXIT_X = 535;
    public static final int BUTTON_EXIT_Y = 520;
    public static final int BUTTON_WIDTH = 150;
    public static final int BUTTON_HEIGHT = 50;
    public static final int START_BUTTON_WIDTH = 250;
    public static final int START_BUTTON_HEIGHT = 225;
    public static final int BUTTON_SPACE_HEIGHT = 50;


    public static final int MENU_LABEL_WIDTH = 300;
    public static final int MENU_LABEL_HEIGHT = 250;


    public static final int X_LOCATION_GOLD_MINER_CHARACTER = 350;
    public static final int Y_LOCATION_GOLD_MINER_CHARACTER = 50;
    public static final int HEIGHT_GOLD_MINER_CHARACTER = 50;
    public static final int WIDTH_GOLD_MINER_CHARACTER = 50;


    public static final int HEIGHT_OBSTACLE = 30;
    public static final int WIDTH_OBSTACLE = 30;

    public static final int INSTRUCTION_WINDOW_WIDTH = 800;
    public static final int INSTRUCTIONS_WINDOW_HEIGHT = 500;
    public static final int INSTRUCTION_WINDOW_X = 200;
    public static final int INSTRUCTION_WINDOW_Y = 100;
    public static final int INSTRUCTION_MARGIN_FROM_LEFT = 20;
    public static final int INSTRUCTION_MARGIN_FROM_UP = 50;
    public static final int MINI_GOLD_WEIGHT = 1;
    public static final int MINI_GOLD_SIZE = 15;
    public static final ImageIcon GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png", MINI_GOLD_SIZE, MINI_GOLD_SIZE);
    public static final int MID_GOLD_WEIGHT = 2;
    public static final int MID_GOLD_SIZE = 33;
    public static final ImageIcon MID_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png", MID_GOLD_SIZE, MID_GOLD_SIZE);
    public static final int BIG_GOLD_WEIGHT = 4;
    public static final int BIG_GOLD_SIZE = 65;
    public static final ImageIcon BIG_GOLD_ICON = Utils.upscaleImage("src/ObjectPhotos/nugget.png", BIG_GOLD_SIZE, BIG_GOLD_SIZE);


    public static final int BIG_ROCK_WEIGHT = 8;
    public static final int BIG_ROCK_SIZE = 65;
    public static final ImageIcon BIG_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/rock_big.png", BIG_ROCK_SIZE, BIG_ROCK_SIZE);


    public static final int MINI_ROCK_WEIGHT = 3;
    public static final int MINI_ROCK_SIZE = 25;
    public static final ImageIcon MINI_ROCK_ICON = Utils.upscaleImage("src/ObjectPhotos/rock_big.png", MINI_ROCK_SIZE, MINI_ROCK_SIZE);

}
