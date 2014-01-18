import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IndexedHashSetTest
{
    private SampleRecord sampleRecord1 = new SampleRecord("001", "Michael Dolenz", 50);
    private SampleRecord sampleRecord2 = new SampleRecord("002", "David Jones", 50);

    @Test
    public void canAddAnObjectAndRetrieveByIndexValue()
    {

        IndexedHashSet<String, SampleRecord> indexedSet = new IndexedHashSet<>((SampleRecord record) -> record.employeeId);
        indexedSet.add(sampleRecord1);
        indexedSet.add(sampleRecord2);
        assertSingleResult(indexedSet.get("001"), sampleRecord1);
        assertSingleResult(indexedSet.get("002"), sampleRecord2);
    }

    @Test
    public void canAddMultipleRecordsWithSameIndexValue()
    {
        IndexedHashSet<Integer, SampleRecord> indexedSet = new IndexedHashSet<>((SampleRecord record) -> record.age);
        indexedSet.add(sampleRecord1);
        indexedSet.add(sampleRecord2);
        List<SampleRecord> retrieved = indexedSet.get(50);
        assertThat(retrieved.size(), is(2));
        assertThat(retrieved.contains(sampleRecord1), is(true));
        assertThat(retrieved.contains(sampleRecord2), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnAddingNull()
    {
        IndexedHashSet<String, String> indexedSet = new IndexedHashSet<>((String) -> "");
        indexedSet.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsOnAddingObjectWithIndexValueNull()
    {
        IndexedHashSet<String, SampleRecord> indexedSet = new IndexedHashSet<>((SampleRecord record) -> record.fullName);
        SampleRecord sampleRecord = new SampleRecord("003", null, 97);
        indexedSet.add(sampleRecord);
    }

    @Test
    public void backingStoreGrowsWithVolume()
    {
        IndexedHashSet<Integer, SampleRecord> indexedSet = new IndexedHashSet<>(2, (SampleRecord record) -> record.age);
        assertThat(indexedSet.getSizeOfBackingStore(), is(2));

        populateWithDummyData(indexedSet, 100);
        assertThat(indexedSet.getSizeOfBackingStore() > 2, is(true));
    }

    @Test
    public void canRetrieveCorrectlyAfterRehash()
    {
        IndexedHashSet<Integer, SampleRecord> indexedSet = new IndexedHashSet<>(2, (SampleRecord record) -> record.age);
        populateWithDummyData(indexedSet, 100);

        for(int i = 0; i < 100; i++)
        {
            List<SampleRecord> retrievedRecords = indexedSet.get(i);
            assertThat(retrievedRecords.size(), is(1));
            assertThat(retrievedRecords.get(0).employeeId, is("id:" + i));
        }
    }

    private void populateWithDummyData(IndexedHashSet<Integer, SampleRecord> indexedSet, int count)
    {
        for(int i = 0; i < count; i++)
        {
            indexedSet.add(new SampleRecord("id:" + i, "Full name", i));
        }
        System.out.println("dsfsdfsdaf");
    }

    private void assertSingleResult(List<SampleRecord> results, SampleRecord expectedResult)
    {
        assertThat(results.size(), is(1));
        assertThat(results.get(0), is(expectedResult));
    }

    private static class SampleRecord
    {
        private String employeeId;
        private String fullName;
        private int age;

        private SampleRecord(String employeeId, String fullName, int age)
        {
            this.employeeId = employeeId;
            this.fullName = fullName;
            this.age = age;
        }
    }
}
