/**
 * A Pose is a Point with an associated heading.
 * 
 * This class was written by our professor as the primary focus of this project
 * was to learn object oriented design, while utilizing provided classes and libraries
 * 
 * @author Nathan Sprague
 * @version V2.0 10/15
 */
public class Pose extends Point
{
    private double heading;

    /**
     * Construct a Pose.
     * 
     * @param xPosition - position on the X-axis
     * @param yPosition - position on the Y-axis
     * @param heading - the heading (in radians)
     */
    public Pose(double xPosition, double yPosition, double heading)
    {
        super(xPosition, yPosition);
        this.heading = heading;
    }

    /**
     * Copy constructor.
     * 
     * @param other Pose to copy
     */
    public Pose(Pose other)
    {
        this(other.getX(), other.getY(), other.getHeading());
    }

    /**
     * @return the heading in radians
     */
    public double getHeading()
    {
        return heading;
    }

    /**
     * Set the heading.
     * 
     * @param newHeading The new heading in radians.
     */
    public void setHeading(double newHeading)
    {
        heading = newHeading;
    }

    /**
     * @return String representation of this pose as
     * "Pose[xPosition=x, yPosition=y, heading=h]"
     */
    public String toString()
    {
        return "Pose[xPosition=" + xPosition + ", yPosition="
                + yPosition + ", heading=" + heading + "]";
    }

}