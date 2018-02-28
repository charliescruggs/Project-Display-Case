/**
 * This class represents a ship object which is also a more specialized Animated
 * Elment.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Acknowledgement: I received help from TA's on this class
 * (Zamua, Kate, Kevin)
 */
public class Ship extends AnimatedElement
{
    /**
     * The Ship constructor calls its specialized super constructor in Animated
     * Element.
     */
    public Ship()
    {
        super(new Pose(480 / 2, 480 / 2, Math.PI / 2), new Vector2D(1, 0),
                GameConstants.SHIP_COLLISION_RADIUS);

    }

    /**
     * This method turns the Ship whenever the user presses the left or right
     * arrows. The turning is measuered in radian.
     * 
     * @param angle the current angle in radian of the ship
     */
    public void turn(double angle)
    {
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_LEFT))
        {

            pose.setHeading(angle + .1);
        }

        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_RIGHT))
        {

            pose.setHeading(angle - .1);
        }
    }

    /**
     * The thrust method handle thrusting when the user presses the down arrow.
     * 
     * @param acceleration the thrust value to pass to applyThrust method
     */
    public void thrust(double acceleration)
    {
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_DOWN))
        {
            GameUtils.applyThrust(velocity, pose.getHeading(), acceleration);

        }

        else
        {
            velocity.setMagnitude(velocity.getMagnitude() * 0.99);
        }
    }

    /**
     * This method updates the ship's location every time step.
     */
    public void update()
    {
        turn(pose.getHeading());
        thrust(GameConstants.SHIP_SPEED);
        move();
    }

    /**
     * This method draws the ship every time step.
     */
    public void draw()
    {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.WHITE);
        GameUtils.drawPoseAsTriangle(this.pose, GameConstants.SHIP_BASE, GameConstants.SHIP_HEIGHT);
    }
}
