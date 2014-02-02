public class MinHeap extends AbstractHeap
{
    @Override
    protected boolean isAfterOrEqual(HeapNode nodeBeingChecked, HeapNode nodeToCheckAgainst)
    {
        return nodeBeingChecked.getValue() <= nodeToCheckAgainst.getValue();
    }
}
