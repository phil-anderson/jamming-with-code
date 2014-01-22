public class MaxSubsetSumMorePerformant extends MaxSubsetSum
{
    private int[] resultsCache;

    @Override
    public int largestNonContiguousSum(int[] numbers)
    {
        resultsCache = new int[numbers.length];
        return super.largestNonContiguousSum(numbers);
    }

    @Override
    protected int calculateLargestSum(int[] numbers, int startIndex)
    {
        int result = resultsCache[startIndex];
        if(result == 0)
        {
            result = super.calculateLargestSum(numbers, startIndex);
            resultsCache[startIndex] = result;
        }
        return result;
    }
}
