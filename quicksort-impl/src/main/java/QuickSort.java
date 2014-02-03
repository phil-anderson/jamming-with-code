public class QuickSort
{
    public void sort(int[] numbersToSort)
    {
        organise(numbersToSort, 0, numbersToSort.length - 1);
    }

    // Organises a subset of an array around an arbitrary pivot value such that everything to left of pivot
    // value is lower than it, and everything to right is greater. Then it recurses for the halves either side
    // of the pivot point.
    private void organise(int[] numbers, int startIndex, int endIndex)
    {
        // Arbitrary start - Picking a good pivot value is a dark art I don't plan to cover here.
        int pivotValue = numbers[endIndex];

        // Perform pivot operation
        int placementPoint = startIndex;
        for (int i = startIndex; i <= endIndex; i++)
        {
            if (numbers[i] < pivotValue)
            {
                swap(numbers, i, placementPoint);
                placementPoint = placementPoint < endIndex ? placementPoint + 1: endIndex;
            }
        }
        swap(numbers, placementPoint, endIndex);

        // Recurse for left and right sides
        // TODO: If multiple pivot values, then this will include some of them in the subsets being recursed.
        // Would be better to narrow the subsets to either side of ALL pivot values
        if (placementPoint - 1 > startIndex)
        {
            organise(numbers, startIndex, placementPoint - 1);
        }

        if (placementPoint + 1 < endIndex + 1)
        {
            organise(numbers, placementPoint + 1, endIndex);
        }
    }

    private void swap(int[] numbers, int from, int to)
    {
        if (from == to)
        {
            return;
        }

        int temp = numbers[from];
        numbers[from] = numbers[to];
        numbers[to] = temp;
    }
}
