/**
 * The Enemy class represents either a saucer or an Asteroid, which both share
 * some characteristics.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017
 */
public abstract class Enemy extends AnimatedElement
{
    int pointValue;

    /**
     * The enemy constructor calls it specialized super constructor in
     * AnimatedElement and initializes it's point value. It passes a pose to its
     * specialized super constructor containing a random pose.
     * 
     * @param speed the speed of the element
     * @param collisionRadius the collisionRadius of the element
     * @param pointValue the point value of the element
     */
    public Enemy(double speed, double collisionRadius, int pointValue)
    {
        super(speed, collisionRadius);
        this.pointValue = pointValue;
    }

    /**
     * Gets the point value for the element.
     * 
     * @return the point value
     */
    public int getPointValue()
    {
        return pointValue;
    }
}