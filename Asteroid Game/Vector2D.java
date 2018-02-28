/**
 * Class representing a two-dimensional vector.
 * 
 * @author Nathan Sprague
 * @version 1.0, 10/15
 * 
 */
public class Vector2D
{

    private double xComponent;
    private double yComponent;

    /**
     * Construct a new vector from the provided heading and magnitude.
     * 
     * A heading of 0 represents a vector pointing directly to the right, the
     * heading increases as vectors are rotated in a counter-clockwise
     * direction.
     * 
     * @param heading Initial heading
     * @param magnitude Initial magnitude (length)
     */
    public Vector2D(double heading, double magnitude)
    {
        setMagnitude(magnitude);
        setHeading(heading);
    }

    /**
     * Copy constructor.
     * 
     * @param other The vector to copy
     */
    public Vector2D(Vector2D other)
    {
        this(other.getHeading(), other.getMagnitude());
    }

    /**
     * @return The X component of the vector
     */
    public double getX()
    {
        return xComponent;
    }

    /**
     * Set the X component of the vector.
     * 
     * @param xComponent The new X component
     */
    public void setX(double xComponent)
    {
        this.xComponent = xComponent;
    }

    /**
     * @return The Y component of the vector
     */
    public double getY()
    {
        return yComponent;
    }

    /**
     * Set the Y component of the vector.
     * 
     * @param yComponent The new Y component
     */
    public void setY(double yComponent)
    {
        this.yComponent = yComponent;
    }

    /**
     * @return The magnitude (length) of this vector
     */
    public double getMagnitude()
    {
        return Math.sqrt(xComponent * xComponent + yComponent * yComponent);
    }

    /**
     * @return The heading of this vector in radians
     */
    public double getHeading()
    {
        return Math.atan2(getY(), getX());
    }

    /**
     * Set the heading of this vector. Note that this operation will have no
     * effect if the magnitude of the vector is zero.
     * 
     * @param heading The new heading in radians
     */
    public void setHeading(double heading)
    {
        double magnitude = getMagnitude();
        setX(Math.cos(heading) * magnitude);
        setY(Math.sin(heading) * magnitude);
    }

    /**
     * Set the magnitude (length) of the vector.
     * 
     * @param magnitude The new magnitude
     */
    public void setMagnitude(double magnitude)
    {
        double oldMagnitude = getMagnitude();

        // Prevent divide by zero if we attempt to set the magnitude of a vector
        // with no heading.
        if (oldMagnitude < .0000001)
        {
            xComponent = magnitude;
        }
        else
        {
            xComponent *= magnitude / oldMagnitude;
            yComponent *= magnitude / oldMagnitude;
        }
    }

    /**
     * @return String representation of this point as
     * "Vector2D[xComponent=x, yComponent=y]"
     */
    public String toString()
    {
        return "Vector2D[xComponent=" + xComponent + ", yComponent=" + yComponent + "]";
    }

}