import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OddAndEvenBitSwapperTest
{
    private OddAndEvenBitSwapper testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new OddAndEvenBitSwapper();
    }

    @Test
    public void allBitsTheSmeMeansNoChange()
    {
        int allZeros = 0;
        int allOnes = 0b11111111111111111111111111111111;

        assertThat(testCandidate.swapBits(allZeros), is(allZeros));
        assertThat(testCandidate.swapBits(allOnes), is(allOnes));
    }

    @Test
    public void alternateOnesAndZerosGetSwapped()
    {
        int numberToSwap =  0b0101010101010101;
        int expectedValue = 0b1010101010101010;

        assertThat(testCandidate.swapBits(numberToSwap), is(expectedValue));
    }

    @Test
    public void handlesHighBitSet()
    {
        int numberToSwap =  0b10000000000000000000000000000000;
        int expectedValue = 0b01000000000000000000000000000000;

        assertThat(testCandidate.swapBits(numberToSwap), is(expectedValue));
    }

    @Test
    public void handlesLowBitSet()
    {
        int numberToSwap =  0b01;
        int expectedValue = 0b10;

        assertThat(testCandidate.swapBits(numberToSwap), is(expectedValue));
    }
}
