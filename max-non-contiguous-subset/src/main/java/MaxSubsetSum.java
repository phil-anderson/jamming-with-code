public class MaxSubsetSum
{
    public int largestNonContiguousSum(int[] numbers)
    {
        return calculateLargestSum(numbers, 0);
    }

    protected int calculateLargestSum(int[] numbers, int startIndex)
    {
        int largestSum;

        if(startIndex == numbers.length - 1) // Only one to choose from
        {
            largestSum = numbers[startIndex];
        }
        else if(startIndex == numbers.length - 2) // Two to choose from
        {
            int secondToLast = numbers[startIndex];
            int last = numbers[startIndex + 1];
            largestSum = Math.max(secondToLast, last);
        }
        else // Calculate result recursively both using and not using this number and choose highest.
        {
            int ifCurrentNumberUsed = calculateLargestSum(numbers, startIndex + 2) + numbers[startIndex];
            int ifCurrentNumberSkipped = calculateLargestSum(numbers, startIndex + 1);
            largestSum = Math.max(ifCurrentNumberUsed, ifCurrentNumberSkipped);
        }

        // Better off using none of the numbers if the largest sum is negative.
        return Math.max(largestSum, 0);
    }
}
