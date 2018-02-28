/**
 * This class is an animated element which is a game element that moves.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017
 * 
 * TA Acknowledgement: I received help from TA's on this class (Zamua, Kate,
 * Kevin, Chunchi)
 */
public abstract class AnimatedElement extends GameElement
{
    protected Vector2D velocity;
    protected double collisionRadius;
    protected boolean destroyed;

    /**
     * This constructor initializes the parameters which it accepts. As well as
     * initializing a boolean to enable autimiatic collision checking.
     * 
     * @param pose the pose of the element
     * @param velocity the velocity of the element
     * @param collisionRadius the collision radius of the element
     */
    public AnimatedElement(Pose pose, Vector2D velocity, double collisionRadius)
    {
        this.pose = pose;
        this.velocity = velocity;
        this.collisionRadius = collisionRadius;
        destroyed = false;
    }

    /**
     * This constructor creates a random heading and initializes its velocity
     * with the random heading and a constant speed.
     * 
     * @param speed the speed of the element
     * @param collisionRadius the collisionRadius of the element
     */
    public AnimatedElement(double speed, double collisionRadius)
    {
        this.pose = new Pose(GameConstants.RANDOM_GENERATOR.nextInt(GameConstants.SCREEN_HEIGHT),
                GameConstants.RANDOM_GENERATOR.nextInt(GameConstants.SCREEN_WIDTH), 0);
        double heading = GameConstants.RANDOM_GENERATOR.nextDouble() * (2 * Math.PI);
        velocity = new Vector2D(heading, speed);
        this.collisionRadius = collisionRadius;
        destroyed = false;
    }

    /**
     * This method moves the animatedElement along the map according to its
     * velocity. It aslo performs wrapping of all moving objects except saucers.
     */
    protected void move()
    {
        pose.move(velocity);

        if (pose.getX() < 0)
        {
            pose.setX(480);
        }

        if (pose.getX() > 480)
        {
            pose.setX(0);
        }

        if (pose.getY() < 0)
        {
            pose.setY(480);
        }

        if (pose.getY() > 480)
        {
            pose.setY(0);
        }

    }

    /**
     * This method updates the animatedElement's location every time step.
     */
    public void update()
    {
        move();
    }

    /**
     * This method checks for a collision every time step by using the distance
     * formula to compare the collision radii of two AnimatedElement.
     * 
     * @param other the other animated element to compare to
     * @return true if there's a collision, false otherwise
     */
    public boolean checkCollision(AnimatedElement other)
    {
        double x = Math.pow(other.pose.getX() - this.pose.getX(), 2);
        double y = Math.pow(other.pose.getY() - this.pose.getY(), 2);

        if (Math.sqrt(x + y) <= (this.collisionRadius + other.collisionRadius))
        {
            destroyed = true;
            return destroyed;
        }
        return destroyed;
    }
}
