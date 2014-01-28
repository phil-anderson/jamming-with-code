import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PegSequenceTest
{
    private PegSequence testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new PegSequence(Peg.Blue, Peg.Brown, Peg.Green, Peg.Orange);
    }

    @Test
    public void holdsASequenceOfPegsInOrder()
    {
        assertThat(testCandidate.getPegAt(0), is(Peg.Blue));
        assertThat(testCandidate.getPegAt(1), is(Peg.Brown));
        assertThat(testCandidate.getPegAt(2), is(Peg.Green));
        assertThat(testCandidate.getPegAt(3), is(Peg.Orange));
    }

    @Test
    public void canGetLengthOfSequence()
    {
        assertThat(testCandidate.getLength(), is(4));
    }

    @Test
    public void canCompareLengths()
    {
        PegSequence sameLength = new PegSequence(Peg.Red, Peg.Red, Peg.Red, Peg.Red);
        PegSequence differentLength = new PegSequence(Peg.Red);
        assertThat(testCandidate.hasSameLengthAs(sameLength), is(true));
        assertThat(testCandidate.hasSameLengthAs(differentLength), is(false));
    }

    @Test
    public void canFindFirstOccurrenceOfPeg()
    {
        assertThat(testCandidate.firstIndexOf(Peg.Green), is(2));
    }

    @Test
    public void canDetermineValidPegNumbers()
    {
        assertThat(testCandidate.isValidPegNumber(-1), is(false));
        assertThat(testCandidate.isValidPegNumber(4), is(false));
        assertThat(testCandidate.isValidPegNumber(0), is(true));
        assertThat(testCandidate.isValidPegNumber(3), is(true));
    }

    @Test
    public void canGetAnUnconnectedMutableCopy()
    {
        MutablePegSequence copy = testCandidate.mutableCopy();
        copy.removePegAt(0);
        assertThat(copy.getPegAt(0), is(nullValue()));
        assertThat(testCandidate.getPegAt(0), is(not(nullValue())));
    }
}
