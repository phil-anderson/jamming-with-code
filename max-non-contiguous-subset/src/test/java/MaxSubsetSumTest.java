import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaxSubsetSumTest
{
    protected MaxSubsetSum testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new MaxSubsetSum();
    }

    @Test
    public void listOfOneReturnsThatOne()
    {
        int[] numbersToCheck = {5};
        assertMaxSubset(numbersToCheck, 5);
    }

    @Test
    public void listOfTwoReturnsLargest()
    {
        int[] largestFirst = {9, 3};
        assertMaxSubset(largestFirst, 9);

        int[] largestSecond = {5, 10};
        assertMaxSubset(largestSecond, 10);
    }

    @Test
    public void listsOfThreeHandledCorrectly()
    {
        int[] outerNumbers = {10, 1, 10};
        assertMaxSubset(outerNumbers, 20);

        int[] middleNumber = {1, 10, 1};
        assertMaxSubset(middleNumber, 10);

        int[] justFirst = {10, -1, -1};
        assertMaxSubset(justFirst, 10);

        int[] justLast = {-1, -1, 10};
        assertMaxSubset(justLast, 10);
    }

    @Test
    public void simpleListLargerThanThreeHandledCorrectly()
    {
        int [] numbersToCheck = { 1, 10, 2, 9, 6, 10 };
        assertMaxSubset(numbersToCheck, 29);
    }

    @Test
    public void skipsMoreThanOneIfRequired()
    {
        int[] numbersToCheck = { 10, -1, -1, -1, -1, 9 };
        assertMaxSubset(numbersToCheck, 19);
    }

    @Test
    public void skipsLargeNumberIfRequired()
    {
        int [] numbersToCheck = { 1, 2, 6, 10, 6, 2, 2};
        assertMaxSubset(numbersToCheck, 15);
    }

    @Test
    public void returnsZeroIfAllNumbersAreNegative()
    {
        int [] numbersToCheck = { -1, -10, -2, -9, -6, -10 };
        assertMaxSubset(numbersToCheck, 0);
    }

    private void assertMaxSubset(int[] numbersToCheck, int expectedResult)
    {
        int result = testCandidate.largestNonContiguousSum(numbersToCheck);
        assertThat(result, is(expectedResult));
    }
}
