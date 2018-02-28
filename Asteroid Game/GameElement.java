/**
 * The Game element represents any element that must be drawn on the screen
 * including moving and nonmoving.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Acknowledgement: I received help from TA's on this class
 * (Zamua, Kate, Kevin)
 */
public abstract class GameElement
{
    protected Pose pose;

    /**
     * This constructor calls the overloaded constructor and passes in a pose
     * with a random heading.
     */
    public GameElement()
    {
        this(new Pose(GameConstants.RANDOM_GENERATOR.nextInt(GameConstants.SCREEN_HEIGHT),
                GameConstants.RANDOM_GENERATOR.nextInt(GameConstants.SCREEN_WIDTH), 0));
    }

    /**
     * This method simply initializes pose of GameElement.
     * 
     * @param pose the pose of the GameElement
     */
    public GameElement(Pose pose)
    {
        this.pose = pose;
    }

    /**
     * An abstract method for draw.
     */
    public abstract void draw();
}