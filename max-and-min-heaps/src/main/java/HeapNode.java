public class HeapNode
{
    private int value;
    private HeapNode left;
    private HeapNode right;

    public HeapNode(int value)
    {
        this.value = value;
    }

    public boolean addChild(HeapNode node)
    {
        if (left == null)
        {
            left = node;
        }
        else if (right == null)
        {
            right = node;
        }

        return left == node || right == node;
    }

    public void pruneChild(HeapNode child)
    {
        if(left == child)
        {
            left = null;
        }
        else if(right == child)
        {
            right = null;
        }
        else throw new IllegalArgumentException("Can only prune direct children");
    }

    public void swapValueWith(HeapNode other)
    {
        int temp = other.value;
        other.value = this.value;
        this.value = temp;
    }

    public void replaceWithRightChild()
    {
        swapValueWith(right);
        right = null;
    }

    public void replaceWithLeftChild()
    {
        swapValueWith(left);
        left = null;
    }

    public boolean isChildless()
    {
        return left == null && right == null;
    }

    public HeapNode getLeft()
    {
        return left;
    }

    public HeapNode getRight()
    {
        return right;
    }

    public int getValue()
    {
        return value;
    }
}
