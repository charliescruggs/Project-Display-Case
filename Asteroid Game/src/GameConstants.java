import java.awt.Color;
import java.util.Random;

/**
 * This class contains ALL of the game's constant values used throughout the
 * program.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Acknowledgement: none
 */
public class GameConstants
{
    public static final Color SCREEN_COLOR = new Color(0, 0, 0);
    public static final int DRAW_DELAY = 10; // In milliseconds.
    public static final int SCREEN_WIDTH = 480;
    public static final int SCREEN_HEIGHT = 480;

    public static final Random RANDOM_GENERATOR = new Random();

    public static final int SCORE_FROM_LEFT = 60;
    public static final int SCORE_FROM_TOP = 20;
    public static final int LIVES_FROM_LEFT = 60;
    public static final int LIVES_FROM_TOP = 60;
    public static final double BULLET_RADIUS = 1.5;
    public static final double BULLET_SPEED = 20;
    public static final int BULLET_LIFE = 20;
    public static double STAR_RADIUS = 1;
    public static double ASTEROID_RADIUS = 30;
    public static double ASTEROID_SPEED = 1;
    public static int ASTEROID_SCORE = 20;
    public static double ASTEROID_COLLISION_RADIUS = 15;
    public static double SHIP_COLLISION_RADIUS = 10;
    public static int SAUCER_SPEED = 2;
    public static final int SAUCER_WIDTH = 20;
    public static final int SAUCER_HEIGHT = 10;
    public static final int SAUCER_SCORE = 200;
    public static final int SAUCER_COLLISION_RADIUS = 10;
    public static final double SHIP_SPEED = 0.1;
    public static final double SHIP_BASE = 10;
    public static final double SHIP_HEIGHT = 20;
    public static final int NUM_STARS = 100;
    public static final int NUM_ASTEROIDS = 10;
}