/**
 * This class represents an asteroid which can destroy a ship if collision
 * occurs. The Asteroid is worth 20 points if destroyed.
 * 
 * @author Charles Scruggs
 * @version 4/4/2017
 */
public class Asteroid extends Enemy
{
    /**
     * This constructor passes arguments to its correct specialized constructor
     * in AnimatedElement.
     */
    public Asteroid()
    {
        super(GameConstants.ASTEROID_SPEED, GameConstants.ASTEROID_COLLISION_RADIUS,
                GameConstants.ASTEROID_SCORE);
    }

    /**
     * This method draws the Asteroid according to its radius and X & Y
     * location.
     */
    public void draw()
    {
        StdDraw.setPenRadius();
        StdDraw.circle(pose.getX(), pose.getY(), GameConstants.ASTEROID_RADIUS);
    }
}