import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniqueRouteCounter2Test
{
    private UniqueRouteCounter2 testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new UniqueRouteCounter2();
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsIfDestinationBehind()
    {
        getRouteCountBetween(10, 10,  9, 10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void throwsIfDestinationAbove()
    {
        getRouteCountBetween(10, 10,  10, 9);
    }

    @Test
    public void startAndDestinationTheSameMeansOnlyOneRoute()
    {
        assertThat(getRouteCountBetween(10, 10,  10, 10), is(1));
    }

    @Test
    public void adjacentSquaresHandledCorrectly()
    {
        assertThat(getRouteCountBetween(10, 10,  11, 10), is(1));
        assertThat(getRouteCountBetween(10, 10,  10, 11), is(1));
        assertThat(getRouteCountBetween(10, 10, 11, 11), is(2));
    }

    @Test
    public void sameRowOrColumnMeansOnlyOneRoute()
    {
        assertThat(getRouteCountBetween(10, 10,  99, 10), is(1));
        assertThat(getRouteCountBetween(10, 10,  10, 99), is(1));
    }

    @Test
    public void handlesLargerGridsCorrectly()
    {
        assertThat(getRouteCountBetween(0, 0, 2, 3), is(10));
        assertThat(getRouteCountBetween(0, 0, 3, 3), is(20));
        assertThat(getRouteCountBetween(0, 0, 2, 4), is(15));
        assertThat(getRouteCountBetween(0, 0, 3, 4), is(35));
        assertThat(getRouteCountBetween(0, 0, 5, 4), is(126));
    }

    private int getRouteCountBetween(int x1, int y1, int x2, int y2)
    {
        return testCandidate.getNumberOUniquefRoutes(new Point(x1, y1), new Point(x2, y2));
    }
}
