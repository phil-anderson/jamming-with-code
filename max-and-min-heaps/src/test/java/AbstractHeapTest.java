import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractHeapTest
{
    private HeapForTest testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new HeapForTest();
    }

    @Test
    public void canAddAndPopValue()
    {
        testCandidate.push(99);
        assertThat(testCandidate.pop(), is(99));
    }

    @Test
    public void canTellWhetherHeapIsEmpty()
    {
        assertThat(testCandidate.isEmpty(), is(true));

        testCandidate.push(99);
        assertThat(testCandidate.isEmpty(), is(false));

        testCandidate.pop();
        assertThat(testCandidate.isEmpty(), is(true));
    }

    @Test
    public void poppingHappensInSequenceOrder()
    {
        testCandidate.push(5).push(1).push(3).push(11).push(7);
        assertThat(testCandidate.pop(), is(11));
        assertThat(testCandidate.pop(), is(7));
        assertThat(testCandidate.pop(), is(5));
        assertThat(testCandidate.pop(), is(3));
        assertThat(testCandidate.pop(), is(1));
    }

    @Test(expected=IllegalStateException.class)
    public void throwsIfPopCalledWhenEmpty()
    {
        testCandidate.pop();
    }

    @Test
    public void largeRandomDataPushesAndPopsFine()
    {
        long seed = System.currentTimeMillis();
        System.out.println("Random seed = " + seed);
        Random random = new Random(seed);
        for(int i = 0; i < 10000; i++)
        {
            testCandidate.push(random.nextInt(10000));
        }
        int value = testCandidate.pop();
        while(!testCandidate.isEmpty())
        {
            assertThat(testCandidate.pop() <= value, is(true));
        }
    }

    private class HeapForTest extends AbstractHeap
    {
        @Override
        protected boolean isAfterOrEqual(HeapNode nodeBeingChecked, HeapNode nodeToCheckAgainst)
        {
            return nodeBeingChecked.getValue() >= nodeToCheckAgainst.getValue();
        }
    }
}

