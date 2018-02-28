/**
 * Utility methods for working with Poses Points and Vectors in Asteroids.
 * 
 * @author Nathan Sprague
 * @version V1.0, 10/15
 *
 */
public class GameUtils
{

    /**
     * Update the provided velocity according to the indicated thrust value and
     * heading. The thrust will be applied so as to push the velocity value in
     * the direction of the provided heading.
     * 
     * @param velocity Velocity vector to update
     * @param heading Direction to push the velocity
     * @param thrust Magnitude of the thrust in pixels / update^2
     */
    public static void applyThrust(Vector2D velocity, double heading, double thrust)
    {
        Vector2D thrustVector = new Vector2D(heading, thrust);
        velocity.setX(velocity.getX() + thrustVector.getX());
        velocity.setY(velocity.getY() + thrustVector.getY());
    }

    /**
     * Draw an isosceles triangle at the location and heading of the provided
     * Pose. The triangle will be centered at the x,y position of the Pose
     * object.
     *
     * @param pose location and heading for the triangle
     * @param width the width of the base of the triangle
     * @param height the height of the triangle
     */
    public static void drawPoseAsTriangle(Pose pose, double width, double height)
    {

        Point llPoint = transformPoseToScreen(pose, new Point(-.5 * height, -.5 * width));
        Point lrPoint = transformPoseToScreen(pose, new Point(-.5 * height, .5 * width));
        Point endPoint = transformPoseToScreen(pose, new Point(.5 * height, 0));

        double[] xs = new double[3];
        double[] ys = new double[3];

        xs[0] = llPoint.getX();
        xs[1] = endPoint.getX();
        xs[2] = lrPoint.getX();
        ys[0] = llPoint.getY();
        ys[1] = endPoint.getY();
        ys[2] = lrPoint.getY();

        StdDraw.polygon(xs, ys);

    }

    // -------------------------------------------------
    // HELPER METHOD BELOW THIS POINT
    // -------------------------------------------------

    /**
     * Perform a transform from the coordinate frame defined by a Pose object to
     * screen coordinates.
     * 
     * @param pose - the coordinate frame to transform from
     * @param point - the point to transform (in pose coordinates)
     * @return - the transformed point (in screen coordinates)
     */
    private static Point transformPoseToScreen(Pose pose, Point point)

    {
        double newX;
        double newY;
        newX = point.getX() * Math.cos(pose.getHeading()) - point.getY()
                * Math.sin(pose.getHeading())
                + pose.getX();
        newY = point.getX() * Math.sin(pose.getHeading()) + point.getY()
                * Math.cos(pose.getHeading())
                + pose.getY();
        return new Point(newX, newY);
    }

}