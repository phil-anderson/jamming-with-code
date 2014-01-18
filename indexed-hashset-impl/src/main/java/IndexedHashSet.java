import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Can't create generic arrays - http://docs.oracle.com/javase/tutorial/java/generics/restrictions.html#createArrays
@SuppressWarnings("unchecked")
public class IndexedHashSet<K, V>
{
    private static final int DEFAULT_SIZE = 256;
    private static final double GROWTH_THRESHOLD = 0.8;

    private IndexValueGetter<K, V> indexValueGetter;
    private int numObjectsInMap = 0;
    private Set<V>[] data;

    // Takes an IndexValueGetter (probably defined as a lambda) that takes an object of type V and returns a value of type K to index the object by.
    public IndexedHashSet(IndexValueGetter<K, V> indexValueGetter)
    {
        this(DEFAULT_SIZE, indexValueGetter);
    }

    public IndexedHashSet(int initialSize, IndexValueGetter<K, V> indexValueGetter)
    {
        this.data = new Set[initialSize];
        this.indexValueGetter = indexValueGetter;
    }

    public void add(V objectToAdd)
    {
        if(objectToAdd == null) throw new IllegalArgumentException("Object to add can't be null");

        if(numObjectsInMap > data.length * GROWTH_THRESHOLD)
        {
            reHash();
        }

        K indexValue = indexValueGetter.getIndexValue(objectToAdd);
        if(indexValue == null) throw new IllegalArgumentException("Index value can't be null");

        int hashSlot = findHashSlot(indexValue);
        Set<V> values = data[hashSlot];
        if(values == null)
        {
            values = new HashSet();
            data[hashSlot] = values;
        }

        if(!values.contains(objectToAdd))
        {
            values.add(objectToAdd);
            numObjectsInMap++;
        }
    }

    public void addAll(Iterable<V> objectsToAdd)
    {
        if(objectsToAdd == null) return;

        for(V record : objectsToAdd)
        {
            add(record);
        }
    }

    public List<V> get(K value)
    {
        Set<V> fromHash = data[findHashSlot(value)];
        if(fromHash != null)
        {
            return fromHash.stream().filter((V record) -> indexValueGetter.getIndexValue(record).equals(value))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private void reHash()
    {
        int oldSize = data.length;
        int newSize = (int) (oldSize * 1.5);
        if(newSize == oldSize)
        {
            newSize++;
        }

        Set[] oldData = data;
        data = new Set[newSize];
        for(Set<V> records : oldData)
        {
            addAll(records);
        }
    }

    private int findHashSlot(K indexValue)
    {
        return indexValue.hashCode() % data.length;
    }

    int getSizeOfBackingStore()
    {
        return data.length;
    }

    public static interface IndexValueGetter<K, V>
    {
        K getIndexValue(V object);

    }
}
