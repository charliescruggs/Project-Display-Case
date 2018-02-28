/**
 * This class creates a nonmoving star GameElement
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Help: none
 */
public class Star extends GameElement
{
    /**
     * This constructor simply calls the super constructer (GameElement).
     */
    Star()
    {
        super();
    }

    /**
     * This method draws stars on the board.
     */
    public void draw()
    {
        StdDraw.filledCircle(pose.getX(), pose.getY(), GameConstants.STAR_RADIUS);
    }
}