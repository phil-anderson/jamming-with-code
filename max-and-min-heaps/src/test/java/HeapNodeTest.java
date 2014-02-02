import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class HeapNodeTest
{
    @Test
    public void hasValueAndChildren()
    {
        HeapNode node = new HeapNode(10);
        assertNodeValue(node, 10);

        node.addChild(new HeapNode(3));
        node.addChild(new HeapNode(9));
        assertChildValues(node, 3, 9);
    }

    @Test
    public void canTellWhetherNodeHasChildren()
    {
        HeapNode node = new HeapNode(10);
        assertThat(node.isChildless(), is(true));

        HeapNode other = new HeapNode(3);
        node.addChild(other);
        assertThat(node.isChildless(), is(false));

        node.pruneChild(other);
        assertThat(node.isChildless(), is(true));
    }

    @Test
    public void cannotAddMoreThanTwoChildren()
    {
        HeapNode node = new HeapNode(0);
        assertThat(node.addChild(new HeapNode(1)), is(true));
        assertThat(node.addChild(new HeapNode(2)), is(true));
        assertThat(node.addChild(new HeapNode(3)), is(false));

        assertChildValues(node, 1, 2);
    }

    @Test
    public void canPruneChildNodes()
    {
        HeapNode root = new HeapNode(10);
        HeapNode left = new HeapNode(3);
        HeapNode right = new HeapNode(7);

        root.addChild(left);
        root.addChild(right);
        assertChildValues(root, 3, 7);

        root.pruneChild(left);
        assertChildValues(root, null, 7);
        root.pruneChild(right);
        assertChildValues(root, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotPruneNonChildNodes()
    {
        HeapNode root = new HeapNode(10);
        HeapNode left = new HeapNode(3);
        HeapNode right = new HeapNode(7);

        root.addChild(left);
        root.addChild(right);
        assertChildValues(root, 3, 7);

        HeapNode separate = new HeapNode(7);
        root.pruneChild(separate);
    }

    @Test
    public void canSwapValueWithAnotherNode()
    {
        HeapNode node1 = new HeapNode(2);
        HeapNode node2 = new HeapNode(1);
        node1.swapValueWith(node2);

        assertNodeValue(node1, 1);
        assertNodeValue(node2, 2);
    }


    private void assertChildValues(HeapNode node, Integer leftValue, Integer rightValue)
    {
        assertNodeValue(node.getLeft(), leftValue);
        assertNodeValue(node.getRight(), rightValue);
    }

    private void assertNodeValue(HeapNode node, Integer expectedValue)
    {
        if(expectedValue == null)
        {
            assertThat(node, is(nullValue()));
        }
        else
        {
            assertThat(node.getValue(), is(expectedValue));
        }
    }
}
