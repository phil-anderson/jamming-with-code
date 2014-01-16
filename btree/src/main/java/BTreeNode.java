import java.util.ArrayList;
import java.util.List;

public class BTreeNode
{
    private int value;
    private int count;

    private BTreeNode lessThan;
    private BTreeNode greaterThan;
    private BTreeNode parent;

    public BTreeNode(int value)
    {
        // If no parent supplied, create a root node
        this(value, null);
    }

    public BTreeNode(int value, BTreeNode parent)
    {
        this.value = value;
        this.count = 1;
        this.parent = parent;
    }

    public int getValue()
    {
        return value;
    }

    public int getCount()
    {
        return count;
    }

    public BTreeNode getLessThan()
    {
        return lessThan;
    }

    public BTreeNode getGreaterThan()
    {
        return greaterThan;
    }

    public boolean isLeaf()
    {
        return lessThan == null && greaterThan == null;
    }

    public void add(int number)
    {
        if (number < value)
        {
            lessThan = addToNodeOrCreate(lessThan, number);
        }
        else if (number > value)
        {
            greaterThan = addToNodeOrCreate(greaterThan, number);
        }
        else
        {
            count++;
        }
    }

    private BTreeNode addToNodeOrCreate(BTreeNode node, int number)
    {
        if (node != null)
        {
            node.add(number);
        }
        else
        {
            node = new BTreeNode(number, this);
        }
        return node;
    }

    public void remove()
    {
        if (count > 1)
        {
            count--;
        }
        else if (isLeaf())
        {
            removeOrDecrementCount();
        }
        else if (lessThan != null)
        {
            removeFromLessThan();
        }
        else
        {
            replaceWith(greaterThan);
        }
    }

    private void removeOrDecrementCount()
    {
        if (count > 1)
        {
            count--;
        }
        else if (value < parent.getValue())
        {
            parent.lessThan = null;
        }
        else
        {
            parent.greaterThan = null;
        }
    }

    private void removeFromLessThan()
    {
        BTreeNode maxChild = lessThan.getMaxChild();
        copyValuesFrom(maxChild);
        if(maxChild == lessThan)
        {
            lessThan = null;
        }
        else
        {
            maxChild.parent.greaterThan = null;
        }
    }

    private void copyValuesFrom(BTreeNode source)
    {
        this.value = source.value;
        this.count = source.count;
    }

    private BTreeNode getMaxChild()
    {
        return greaterThan == null ? this : greaterThan.getMaxChild();
    }

    private void replaceWith(BTreeNode otherNode)
    {
        greaterThan = otherNode.greaterThan;
        lessThan = otherNode.lessThan;
        copyValuesFrom(otherNode);
    }

    public BTreeNode findChildWithValue(int valueToFind)
    {
        if (valueToFind < value)
        {
            return lessThan != null ? lessThan.findChildWithValue(valueToFind) : null;
        }
        else if (valueToFind > value)
        {
            return greaterThan != null ? greaterThan.findChildWithValue(valueToFind) : null;
        }
        return this;
    }

    public List<Integer> sortedValues()
    {
        List<Integer> result = new ArrayList<>();
        sortChildValuesInto(result);
        return result;
    }

    private void sortChildValuesInto(List<Integer> resultHolder)
    {
        callSortIfNotNull(lessThan, resultHolder);
        for (int i = 0; i < count; i++)
        {
            resultHolder.add(value);
        }
        callSortIfNotNull(greaterThan, resultHolder);
    }

    private void callSortIfNotNull(BTreeNode node, List<Integer> resultHolder)
    {
        if (node != null)
        {
            node.sortChildValuesInto(resultHolder);
        }
    }
}