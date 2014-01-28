import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MastermindScorerTest
{
    @Test
    public void noMatches()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Red, Peg.Red, Peg.Red, Peg.Red);
        assertScore(actual, guess, 0, 0);
    }

    @Test
    public void oneMatchWrongPlace()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Yellow, Peg.Red, Peg.Red, Peg.Red);
        assertScore(actual, guess, 0, 1);
    }

    @Test
    public void oneMatchRightPlace()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Blue, Peg.Red, Peg.Red, Peg.Red);
        assertScore(actual, guess, 1, 0);
    }

    @Test
    public void allMatchButInWrongPlaces()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Green, Peg.Blue, Peg.Yellow, Peg.Blue);
        assertScore(actual, guess, 0, 4);
    }

    @Test
    public void allMatchInRightPlaces()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Blue, Peg.Green, Peg.Blue, Peg.Yellow);
        assertScore(actual, guess, 4, 0);
    }

    @Test
    public void allMatchButTwoInWrongPlace()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Orange, Peg.Yellow);
        PegSequence guess = new PegSequence(Peg.Green, Peg.Blue, Peg.Orange, Peg.Yellow);
        assertScore(actual, guess, 2, 2);
    }

    @Test
    public void pegsAreOnlyUsedOnceAndRightPlaceTakesPriority()
    {
        PegSequence actual = new PegSequence(Peg.Blue, Peg.Green, Peg.Green, Peg.Green);
        PegSequence guess = new PegSequence(Peg.Green, Peg.Green, Peg.Red, Peg.Red);
        assertScore(actual, guess, 1, 1);
    }

    private void assertScore(PegSequence actual, PegSequence guess, int rightColourRightPlace, int rightColourWrongPlace)
    {
        MastermindScorer scorer = new MastermindScorer(actual, guess);
        Score score = scorer.calculateScore();
        assertThat(score.getRightColourRightPlace(), is(rightColourRightPlace));
        assertThat(score.getRightColourWrongPlace(), is(rightColourWrongPlace));
    }
}
