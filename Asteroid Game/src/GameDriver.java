/**
 * Driver for the Asteroids application. 
 * 
 * This Driver class was provided by our professor to test our project
 * for functionality.
 * 
 * @author Nathan Sprague
 * @version V1.0, 10/15
 * 
 */
public class GameDriver
{
    /**
     * Create a game object and a display screen and execute the main update
     * loop.
     * 
     * @param args
     *            - command line arguments (ignored)
     */
    public static void main(String[] args) {
        AsteroidsGame sim;

        sim = new AsteroidsGame();

        StdDraw.setCanvasSize(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        StdDraw.setXscale(0, GameConstants.SCREEN_WIDTH);
        StdDraw.setYscale(0, GameConstants.SCREEN_HEIGHT);

        while (true) {
            StdDraw.clear(GameConstants.SCREEN_COLOR);
            sim.update();
            sim.draw();
            StdDraw.show(GameConstants.DRAW_DELAY);
        }

    }

}