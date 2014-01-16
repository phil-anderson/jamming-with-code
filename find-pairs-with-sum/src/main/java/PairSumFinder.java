import java.util.ArrayList;
import java.util.List;

public class PairSumFinder
{
    public List<Pair<Integer>> findPairs(int[] listToCheck, int targetNumber)
    {
        List<Pair<Integer>> result = new ArrayList<>();
        if (listToCheck == null || listToCheck.length <= 1)
        {
            throw new IllegalArgumentException("Must be an array of length > 1");
        }

        IntegerBag bag = new IntegerBag();
        for(int number : listToCheck)
        {
            int numberNeeded = targetNumber - number;
            if (!bag.consume(numberNeeded))
            {
                bag.put(number);
            }
            else
            {
                result.add(new Pair<>(number, numberNeeded));
            }
        }
        return result;
    }
}
