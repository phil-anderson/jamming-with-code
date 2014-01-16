import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PairSumFinderTest
{
    @Test
    public void findsUniquePairs()
    {
        int[] sourceList = {1, 2, 8, 9};
        List<Pair<Integer>> expected = buildListOfPairs(2, 8,   1, 9);
        List<Pair<Integer>> actual = new PairSumFinder().findPairs(sourceList, 10);
        assertPairListsAreEquivalent(expected, actual);
    }

    @Test
    public void ignoresNonPairingValues()
    {
        int[] sourceList = {1, 2, 8, 9, 3, 6};
        List<Pair<Integer>> expected = buildListOfPairs(2, 8,   1, 9);
        List<Pair<Integer>> actual = new PairSumFinder().findPairs(sourceList, 10);
        assertPairListsAreEquivalent(expected, actual);
    }

    @Test
    public void handlesMultipleEquivalentPairs()
    {
        int[] sourceList = {1, 2, 8, 9, 3, 6, 9, 2, 1, 6, 8};
        List<Pair<Integer>> expected = buildListOfPairs(2, 8,   1, 9,   1, 9,   2, 8);
        List<Pair<Integer>> actual = new PairSumFinder().findPairs(sourceList, 10);
        assertPairListsAreEquivalent(expected, actual);
    }

    @Test
    public void ignoresPairValueIfOtherAlreadyUsed()
    {
        int[] sourceList = {1, 9, 9, 9, 1};
        List<Pair<Integer>> expected = buildListOfPairs(1, 9,   1, 9);
        List<Pair<Integer>> actual = new PairSumFinder().findPairs(sourceList, 10);
        assertPairListsAreEquivalent(expected, actual);
    }

    private List<Pair<Integer>> buildListOfPairs(Integer... values)
    {
        List<Pair<Integer>> result = new ArrayList<>();
        for(int i=0; i<values.length; i+=2)
        {
            result.add(new Pair<>(values[i], values[i+1]));
        }
        return result;
    }

    private void assertPairListsAreEquivalent(List<Pair<Integer>> list1, List<Pair<Integer>> list2)
    {
        assertThat(list1.size(), is(list2.size()));
        Iterator<Pair<Integer>> list2Iter = list2.iterator();
        for(Pair<Integer> pair1 : list1)
        {
            Pair<Integer> pair2 = list2Iter.next();
            assertThat(pair1.equivalentTo(pair2), is(true));
        }
    }
}
