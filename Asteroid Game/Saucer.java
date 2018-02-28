/**
 * This method represents the saucer in the Asteroid Game.
 * 
 * @author Charles Scruggs TA Acknowledgement: none
 */
public class Saucer extends Enemy
{
    /**
     * This constructor calls its correct specialized constructor in the super
     * class (AnimateElement).
     */
    public Saucer()
    {
        super(GameConstants.SAUCER_SPEED, GameConstants.SAUCER_COLLISION_RADIUS,
                GameConstants.SAUCER_SCORE);
    }

    /**
     * This method moves a saucer according to its specific behaviour of not
     * wrapping and choosing a random direction every so often.
     */
    public void move()
    {
        pose.move(velocity);
        if (GameConstants.RANDOM_GENERATOR.nextDouble() < .05)
        {
            velocity.setHeading(GameConstants.RANDOM_GENERATOR.nextDouble() * (2 * Math.PI));
        }
    }

    /**
     * This method updates the saucer by calling its overrided move() method.
     */
    public void update()
    {
        move();
        if (pose.getX() < 0)
        {
            destroyed = true;
        }

        if (pose.getX() > 480)
        {
            destroyed = true;
        }

        if (pose.getY() < 0)
        {
            destroyed = true;
        }

        if (pose.getY() > 480)
        {
            destroyed = true;
        }
    }

    /**
     * This method draws the saucer on the game board.
     */
    public void draw()
    {
        StdDraw.setPenRadius();
        StdDraw.rectangle(pose.getX(), pose.getY(), GameConstants.SAUCER_WIDTH / 2,
                GameConstants.SAUCER_HEIGHT / 2);
    }

}