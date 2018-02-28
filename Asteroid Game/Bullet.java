/**
 * This class represents the bullet which can be shot from a ship by pressing
 * space.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Acknowledgement: I received help from TA's on this class
 * (Zamua, Kate, Kevin)
 */
public class Bullet extends AnimatedElement
{
    private int bulletLife = GameConstants.BULLET_LIFE;
    private boolean bulletHere = false;

    /**
     * This constructor passes parameters to its correct specialized super
     * constructor in AnimatedElment.
     * 
     * @param pose the bullet's pose
     * @param velocity the bullet's velocity
     */
    public Bullet(Pose pose, Vector2D velocity)
    {
        super(pose, velocity, GameConstants.BULLET_RADIUS);
    }

    /**
     * Updates the bullets life span and moves the bullet.
     */
    public void update()
    {
        bulletLife--;

        if (bulletLife == 0)
        {
            destroyed = true;
        }
        move();
    }

    /**
     * This method draws a bullet.
     */
    public void draw()
    {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenRadius();
        StdDraw.filledCircle(pose.getX(), pose.getY(), GameConstants.BULLET_RADIUS);
    }

    /**
     * This method returns bulletHere.
     * 
     * @return returns true if the bullet is still going
     */
    public boolean isBulletThere()
    {
        return bulletHere;
    }
}
