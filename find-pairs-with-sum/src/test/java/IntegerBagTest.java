import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegerBagTest
{
    private IntegerBag testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new IntegerBag();
    }

    @Test
    public void consumeReturnsFalseIfIntegerNotInBag()
    {
        assertThat(testCandidate.consume(1), is(false));
    }

    @Test
    public void consumeReturnsTrueIfIntegerIsInBag()
    {
        testCandidate.put(10);
        assertThat(testCandidate.consume(10), is(true));
    }

    @Test
    public void consumeRemovesIntegerFromBag()
    {
        testCandidate.put(10);
        assertThat(testCandidate.consume(10), is(true));
        assertThat(testCandidate.consume(10), is(false));
    }

    @Test
    public void canPutAndConsumeDuplicateValues()
    {
        testCandidate.put(10);
        testCandidate.put(10);
        testCandidate.put(10);
        assertThat(testCandidate.consume(10), is(true));
        assertThat(testCandidate.consume(10), is(true));
        assertThat(testCandidate.consume(10), is(true));
        assertThat(testCandidate.consume(10), is(false));
    }
}
