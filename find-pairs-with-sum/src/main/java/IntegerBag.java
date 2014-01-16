import java.util.HashMap;

public class IntegerBag
{
    private HashMap<Integer, Integer> store = new HashMap<>();

    public void put(int numberToAdd)
    {
        Integer countFromStore = store.get(numberToAdd);
        if (countFromStore == null)
        {
            countFromStore = 0;
        }
        store.put(numberToAdd, countFromStore + 1);
    }

    public boolean consume(int numberToConsume)
    {
        Integer countFromStore = store.get(numberToConsume);
        if (countFromStore == null)
        {
            return false;
        }

        if (countFromStore > 1)
        {
            store.put(numberToConsume, countFromStore - 1);
        }
        else
        {
            store.remove(numberToConsume);
        }
        return true;
    }
}
