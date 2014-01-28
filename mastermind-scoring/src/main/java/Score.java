public class Score
{
    private int rightColourRightPlace;
    private int rightColourWrongPlace;

    public void addRightColourRightPlace()
    {
        rightColourRightPlace++;
    }

    public void addRightColourWrongPlace()
    {
        rightColourWrongPlace++;
    }

    public int getRightColourRightPlace()
    {
        return rightColourRightPlace;
    }

    public int getRightColourWrongPlace()
    {
        return rightColourWrongPlace;
    }
}