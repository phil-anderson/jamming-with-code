import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PerformanceTest
{
    // Somewhat inexact as doesn't take GC's into account, doesn;t do JIT compiler warm-up etc. etc.
    // Empirical performance testing in Java is really hard and not in scope here... A good project
    // for another time perhaps. This ought to be good enough for now.
    @Test
    public void performantVersionIsFasterThanStandardOne()
    {
        MaxSubsetSum standard = new MaxSubsetSum();
        MaxSubsetSum morePerformant = new MaxSubsetSumMorePerformant();

        int[] numbersToCheck = buildRandomArray(40);

        long startTime = System.currentTimeMillis();
        int standardResult = standard.largestNonContiguousSum(numbersToCheck);
        long standardDuration = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        int morePerformantResult = morePerformant.largestNonContiguousSum(numbersToCheck);
        long morePerformantDuration = System.currentTimeMillis() - startTime;

        assertThat(morePerformantResult, is(equalTo(standardResult)));
        assertThat(morePerformantDuration < standardDuration, is(true));

        System.out.println("Standard approach:        " + standardDuration + "ms");
        System.out.println("More performant approach: " + morePerformantDuration + "ms");
    }

    @Test
    public void performantVersionCanHandleLargeListOfNumbers()
    {
        int[] numbersToCheck = buildRandomArray(5000);
        MaxSubsetSumMorePerformant testCandidate = new MaxSubsetSumMorePerformant();
        long startTime = System.currentTimeMillis();
        testCandidate.largestNonContiguousSum(numbersToCheck);
        long duration = System.currentTimeMillis() - startTime;
        assertThat(duration < 50, is(true));
        System.out.println("Large array:              " + duration + "ms");
    }

    private int[] buildRandomArray(int length)
    {
        int[] reult = new int[length];
        Arrays.setAll(reult, (int i) -> (int) (Math.random() * length));
        return reult;
    }
}
