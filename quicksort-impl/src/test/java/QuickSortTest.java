import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuickSortTest
{
    private QuickSort testCandidate;

    @Before
    public void setUp()
    {
        testCandidate = new QuickSort();
    }

    @Test
    public void sortsBasicArray()
    {
        int[] numbers = { 1, 7, 3, 9, 2, 5};
        sortAndAssertResult(numbers);
    }

    @Test
    public void sortsReverseOrderedArray()
    {
        int[] numbers = { 6, 5, 4, 3, 2, 1 };
        sortAndAssertResult(numbers);
    }

    @Test
    public void sortsArrayWithDuplicates()
    {
        int[] numbers = { 1, 3, 3, 9, 3, 5};
        sortAndAssertResult(numbers);
    }

    @Test
    public void handlesArrayWithAllDuplicates()
    {
        int[] numbers = { 5, 5, 5, 5, 5, 5 };
        sortAndAssertResult(numbers);
    }

    @Test
    public void handlesAlreadySortedArray()
    {
        int[] numbers = { 1, 2, 3, 4, 5, 6 };
        sortAndAssertResult(numbers);
    }

    @Test
    public void sortsLargeRandomArray()
    {
        int[] numbers = buildRandomArray(10000);
        sortAndAssertResult(numbers);
    }

    public void sortAndAssertResult(int[] numbers)
    {
        int[] jdkSorted = Arrays.copyOf(numbers, numbers.length);
        Arrays.sort(jdkSorted);
        testCandidate.sort(numbers);
        assertThat(Arrays.equals(numbers, jdkSorted), is(true));
    }

    private int[] buildRandomArray(int length)
    {
        int[] reult = new int[length];
        Arrays.setAll(reult, (int i) -> (int) (Math.random() * length));
        return reult;
    }
}
