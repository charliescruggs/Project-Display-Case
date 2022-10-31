import java.util.ArrayList;
import java.util.Iterator;

/**
 * This Asteroid Game creates the moving and non moving objects on a game board
 * (black screen) , and provides an "update" method that updates the state of
 * all game elements at each time step, and a "draw" method that re-draws game
 * elements after they have been updated.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017
 *
 */
public class AsteroidsGame
{
    public ArrayList<GameElement> drawElements;
    public ArrayList<AnimatedElement> updateElements;
    public ArrayList<AnimatedElement> shipAndBullets;
    public ArrayList<AnimatedElement> enemies;
    public Ship ship;
    public Asteroid asteroid = new Asteroid();
    public int score;
    public int lives;
    public NumericDisplay scoreBoard;
    public NumericDisplay livesDisplay;

    /**
     * The AsteroidGame constructor is responsible for initializing the
     * array lists including all elements of the game except for bullets and
     * saucers, which will be handled in more in update.
     */
    public AsteroidsGame()
    {
        drawElements = new ArrayList<GameElement>();
        updateElements = new ArrayList<AnimatedElement>();
        shipAndBullets = new ArrayList<AnimatedElement>();
        enemies = new ArrayList<AnimatedElement>();
        lives = 3;
        score = 0;
        scoreBoard = new NumericDisplay(GameConstants.SCORE_FROM_LEFT,
                GameConstants.SCREEN_HEIGHT - GameConstants.SCORE_FROM_TOP, score, "Score: ");
        livesDisplay = new NumericDisplay(GameConstants.LIVES_FROM_LEFT,
                GameConstants.SCREEN_HEIGHT - GameConstants.LIVES_FROM_TOP, lives, "Lives: ");

        // Initialize drawElements

        // adds Ship to drawElements, updateBullets, and shipsAndBullets
        ship = new Ship();
        drawElements.add(ship);
        updateElements.add(ship);
        shipAndBullets.add(ship);

        // adds stars to drawElements
        for (int i = 0; i < GameConstants.NUM_STARS; i++)
        {
            Star star = new Star();
            drawElements.add(star);
        }

        // adds Asteroids to drawElements
        for (int i = 0; i < 10; i++)
        {
            Asteroid asteroid = new Asteroid();
            updateElements.add(asteroid);
            drawElements.add(asteroid);
            enemies.add(asteroid);
        }

        // adds scoreBoard to drawElements
        drawElements.add(scoreBoard);

        // adds livesDisplay to drawElements
        drawElements.add(livesDisplay);
    }

    /**
     * The update method essentially calls the update methods of all the
     * updateElements objects. This allows for a polymorphic response where
     * each game element can update itself in its own specialized way. further
     * behavior information of this method can be found in the update methods
     * for each game element.
     */
    public void update()
    {
        if (lives > 0)
        {
            if (StdDraw.hasNextKeyTyped()
                    && StdDraw.nextKeyTyped() == java.awt.event.KeyEvent.VK_SPACE)
            {
                Pose bulletPose = new Pose(ship.pose.getX(), ship.pose.getY(),
                        ship.pose.getHeading());
                Vector2D bulletVelocity = new Vector2D(ship.pose.getHeading(), 20);

                Bullet bullet = new Bullet(bulletPose, bulletVelocity);

                updateElements.add(bullet);
                drawElements.add(bullet);
                shipAndBullets.add(bullet);
            }

            if (GameConstants.RANDOM_GENERATOR.nextDouble() < .002)
            {
                Saucer saucer = new Saucer();
                drawElements.add(saucer);
                updateElements.add(saucer);
                enemies.add(saucer);
            }

            for (int i = 0; i < updateElements.size(); i++)
            {
                updateElements.get(i).update();
            }

            Iterator<AnimatedElement> sB = shipAndBullets.iterator();

            while (sB.hasNext())
            {
                AnimatedElement sbElement = sB.next();
                Iterator<AnimatedElement> e = enemies.iterator();

                if (sbElement instanceof Bullet && sbElement.destroyed)
                {
                    sB.remove();
                    updateElements.remove(sbElement);
                    drawElements.remove(sbElement);
                    continue;
                }

                while (e.hasNext())
                {
                    AnimatedElement eElement = e.next();

                    if (eElement instanceof Saucer && eElement.destroyed)
                    {
                        e.remove();
                    }

                    if (sbElement.checkCollision(eElement))
                    {

                        if (sbElement instanceof Ship)
                        {

                            Ship removeShip = (Ship) sbElement;

                            Enemy removeEnemy = (Enemy) eElement;

                            sB.remove();
                            shipAndBullets.remove(removeShip);
                            drawElements.remove(removeShip);
                            updateElements.remove(removeShip);
                            lives--;
                            livesDisplay.setValue(lives);

                            e.remove();
                            updateElements.remove(removeEnemy);
                            drawElements.remove(removeEnemy);
                            enemies.remove(removeEnemy);
                        }

                        else if (sbElement instanceof Bullet)
                        {

                            Enemy removeEnemy = (Enemy) eElement;

                            Bullet removeBullet = (Bullet) sbElement;

                            score += removeEnemy.getPointValue();
                            scoreBoard.setValue(score);

                            sB.remove();
                            drawElements.remove(removeBullet);
                            updateElements.remove(removeBullet);

                            e.remove();
                            updateElements.remove(removeEnemy);
                            drawElements.remove(removeEnemy);
                            enemies.remove(removeEnemy);
                        }
                        break;
                    }

                }

            }

            if (!shipAndBullets.contains(ship))
            {
                ship = new Ship();
                drawElements.add(ship);
                updateElements.add(ship);
                shipAndBullets.add(ship);
            }
        }

        if (enemies.isEmpty() || enemies == null)
        {
            for (int i = 0; i < 10; i++)
            {
                Asteroid asteroid = new Asteroid();
                updateElements.add(asteroid);
                drawElements.add(asteroid);
                enemies.add(asteroid);
            }
        }
    }

    /**
     * This method calls the draw methods of the game element objects. check
     * each objects update methods for further documentation of behavior.
     */
    public void draw()
    {
        for (int i = 0; i < drawElements.size(); i++)
        {
            drawElements.get(i).draw();
        }
    }
}