import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MaxHeapTest
{
    @Test
    public void isAfterOrEqulReturnsTrueIfValueGreaterThanOrEqual()
    {
        MaxHeap heap = new MaxHeap();
        HeapNode small = new HeapNode(1);
        HeapNode large = new HeapNode(100);
        assertThat(heap.isAfterOrEqual(large, small), is(true));
        assertThat(heap.isAfterOrEqual(small, large), is(false));
    }
}
