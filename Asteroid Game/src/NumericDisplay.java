/**
 * This class represents the Lives and Scores of the player.
 * 
 * @author Charles Scruggs
 * @version 4/3/2017
 */
public class NumericDisplay extends GameElement
{
    int value;
    String prefix;

    /**
     * This constructor passes a new pose into its super constructor in
     * GameELement and initializes value and prefix.
     * 
     * @param xPos the x coordinate
     * @param yPos the y coordinate
     * @param value the value of score or lives
     * @param prefix the string of score or lives
     */
    public NumericDisplay(int xPos, int yPos, int value, String prefix)
    {
        super(new Pose(xPos, yPos, 0));
        this.value = value;
        this.prefix = prefix;
    }

    /**
     * Draws the scoreDisplay.
     */
    public void draw()
    {
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(pose.getX(), pose.getY(), prefix + value);
    }

    /**
     * Gets the value.
     * 
     * @return the score or life value of the numeric display element
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Sets the value of the life or score.
     * 
     * @param value the life or score
     */
    public void setValue(int value)
    {
        this.value = value;
    }
}