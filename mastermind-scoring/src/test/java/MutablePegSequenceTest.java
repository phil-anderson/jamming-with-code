import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class MutablePegSequenceTest
{
    @Test
    public void canRemovePegs()
    {
        MutablePegSequence testCandidate = new MutablePegSequence(Peg.Orange, Peg.Blue, Peg.Brown, Peg.Green);
        assertThat(testCandidate.getPegAt(0), is(Peg.Orange));

        testCandidate.removePegAt(0);
        assertThat(testCandidate.getPegAt(0), is(nullValue()));
    }
}
