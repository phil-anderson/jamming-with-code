import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScoreTest
{
    @Test
    public void keepsTrackOfScores()
    {
        Score score = new Score();
        assertThat(score.getRightColourRightPlace(), is(0));
        assertThat(score.getRightColourWrongPlace(), is(0));

        score.addRightColourRightPlace();
        score.addRightColourRightPlace();
        score.addRightColourWrongPlace();

        assertThat(score.getRightColourRightPlace(), is(2));
        assertThat(score.getRightColourWrongPlace(), is(1));
    }
}
