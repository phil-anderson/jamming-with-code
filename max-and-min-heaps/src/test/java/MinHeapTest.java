import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinHeapTest
{
    @Test
    public void isAfterOrEqulReturnsTrueIfValueSmallerThanOrEqual()
    {
        MinHeap heap = new MinHeap();
        HeapNode small = new HeapNode(1);
        HeapNode large = new HeapNode(100);
        assertThat(heap.isAfterOrEqual(small, large), is(true));
        assertThat(heap.isAfterOrEqual(large, small), is(false));
    }
}
