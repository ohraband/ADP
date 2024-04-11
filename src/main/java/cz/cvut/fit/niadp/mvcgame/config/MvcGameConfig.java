package cz.cvut.fit.niadp.mvcgame.config;

public class MvcGameConfig {

    public static final int MAX_X = 1920;
    public static final int MAX_Y = 1080;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 200;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int GAMEINFO_POS_X = 50;
    public static final int GAMEINFO_POS_Y = 50;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final int INIT_POWER = 10;
    public static final double INIT_ANGLE = 0;
    public static final double GRAVITY = 9.81;
    public static final int MAX_POWER = 50;
    public static final int MIN_POWER = 1;

    public static final int MIN_MISSILES = 1;
    public static final int MAX_MISSILES = (int) ( (Math.PI / 2)  / ANGLE_STEP);
    public static final int MISSILES_STEP = 1;

    public static final int INIT_MISSILES = 1;

    public static final String GAME_TITLE = "The NI-ADP MvcGame";

    public static final String UP_KEY = "UP";
    public static final String DOWN_KEY = "DOWN";
    public static final String EXIT_KEY = "ESCAPE";
    public static final String SHOOT_KEY = "SPACE";
    public static final String AIM_UP_KEY = "A";
    public static final String AIM_DOWN_KEY = "Y";
    public static final String POWER_UP_KEY = "F";
    public static final String POWER_DOWN_KEY = "D";
    public static final String MOVING_STRATEGY_KEY = "M";
    public static final String SHOOTING_MODE_KEY = "N";
    public static final String UNDO_LAST_COMMAND_KEY = "B";
    public static final String MISSILES_UP_KEY = "C";
    public static final String MISSILES_DOWN_KEY = "V";
    public static final String SECRET_CANNON_KEY = "ô";
    public static final String BACKGROUND_KEY = "Q";
    public static final String INVINCIBLE_MISSILE_KEY = "I";
    public static final String MISSILE_TYPE_KEY = "T";
    public static final String CANNON_IMAGE_RESOURCE = "images/cannon.png";
    public static final String MISSILE_IMAGE_RESOURCE = "images/missile1.png";
    public static final String ENEMY_1 = "images/enemy1.png";
    public static final String ENEMY_2 = "images/enemy2.png";
    public static final String COLLISION_IMAGE = "images/collision.png";
    public static final String BACKGROUND_1 = "images/enemy1.png";
    public static final String BACKGROUND_2 = "images/enemy2.png";
    public static final String BACKGROUND_3 = "images/collision.png";
    public static final String BACKGROUND_4 = "images/socket.png";
    public static final String BACKGROUND_5 = "images/background2.jpg";
    public static final String BACKGROUND_6 = "images/background1.png";
    public static final String MISSILE_IMAGE_RESOURCE_TYPE_A = "images/missile2.png";
    public static final String MISSILE_IMAGE_RESOURCE_TYPE_B = "images/missile3.png";








    public static final String LEVEL_1 = "src/main/resources/levels/level1.txt";
    public static final String LEVEL_2 = "src/main/resources/levels/level2.txt";
}