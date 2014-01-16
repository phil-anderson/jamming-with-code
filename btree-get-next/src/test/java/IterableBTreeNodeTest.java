import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class IterableBTreeNodeTest
{
    @Test
    public void returnsNullIfNoNextNode()
    {
        assertThat(new IterableBTreeNode(1).getNext(), is(nullValue()));
    }

    @Test
    public void getNextReturnsNextNodeInOrder()
    {
        IterableBTreeNode root = new IterableBTreeNode(15);
        IterableBTreeNode a = new IterableBTreeNode(10);
        IterableBTreeNode b = new IterableBTreeNode(20);
        IterableBTreeNode c = new IterableBTreeNode(11);
        IterableBTreeNode d = new IterableBTreeNode(30);
        IterableBTreeNode e = new IterableBTreeNode(12);
        root.left = a;
        root.right = b;
        b.left = c;
        b.right = d;
        c.left = e;

        int[] expected = {10, 11, 12, 15, 20, 30};
        IterableBTreeNode node = a;
        int index = 0;
        do
        {
            assertThat(node.getValue(), is(expected[index++]));
            node = node.getNext();
        } while(node != null);
    }
}
