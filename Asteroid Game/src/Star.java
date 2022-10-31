/**
 * This class creates a non-moving star GameElement
 * 
 * @author Charles Scruggs
 * @version 4/3/2017 TA Help: none
 */
public class Star extends GameElement
{
    /**
     * This constructor simply calls the super constructor from GameElement.
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