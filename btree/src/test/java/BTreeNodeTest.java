import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BTreeNodeTest
{
    private static final int LARGE_TREE_SIZE = 10000;

    private BTreeNode sampleTree;

    @Before
    public void setUp()
    {
        //      10
        //     /
        //    5
        //     \
        //      8
        //     / \
        //    6   9
        sampleTree = new BTreeNode(10);
        sampleTree.add(5);
        sampleTree.add(8);
        sampleTree.add(9);
        sampleTree.add(6);
    }

    @Test
    public void addPutsNodeOnCorrectSide()
    {
        BTreeNode node = new BTreeNode(10);
        node.add(9);
        node.add(11);
        assertThat(node.getLessThan().getValue(), is(9));
        assertThat(node.getGreaterThan().getValue(), is(11));
    }

    @Test
    public void addGetsPropagateDownTreeToCorrectPlace()
    {
        BTreeNode shouldBeSix = sampleTree.getLessThan().getGreaterThan().getLessThan();
        assertThat(shouldBeSix.getValue(), is(6));
    }

    @Test
    public void addingAndRemovingDuplicateValuesAdjustsCountForTheRelevantNode()
    {
        BTreeNode nine = sampleTree.findChildWithValue(9);
        assertThat(nine.getCount(), is(1));
        sampleTree.add(9);
        assertThat(nine.getCount(), is(2));
        nine.remove();
        assertThat(nine.getCount(), is(1));
    }

    @Test
    public void isLeafIdentifiesLeafNodes()
    {
        assertThat(sampleTree.isLeaf(), is(false));
        assertThat(sampleTree.findChildWithValue(5).isLeaf(), is(false));
        assertThat(sampleTree.findChildWithValue(8).isLeaf(), is(false));
        assertThat(sampleTree.findChildWithValue(6).isLeaf(), is(true));
        assertThat(sampleTree.findChildWithValue(9).isLeaf(), is(true));
    }

    @Test
    public void findChildWithValueReturnsCorrectNode()
    {
        BTreeNode found = sampleTree.findChildWithValue(9);
        assertThat(found.getValue(), is(9));
    }

    @Test
    public void canRemoveLeafNodes()
    {
        sampleTree.findChildWithValue(6).remove();
        sampleTree.findChildWithValue(9).remove();
        BTreeNode removedNodesParent = sampleTree.findChildWithValue(8);
        assertThat(removedNodesParent.isLeaf(), is(true));
    }

    @Test
    public void canRemoveNodeWithNoLessThans()
    {
        sampleTree.findChildWithValue(5).remove();
        assertThat(sampleTree.getLessThan().getValue(), is(8));
        assertSortedValuesAre(6, 8, 9, 10);
    }

    @Test
    public void canRemoveNodeWithNoGreaterThans()
    {
        sampleTree.remove();
        assertThat(sampleTree.getValue(), is(9));
        assertSortedValuesAre(5, 6, 8, 9);
    }

    @Test
    public void canRemoveNodeWithLessThansAndGreaterThans()
    {
        sampleTree.findChildWithValue(8).remove();
        assertSortedValuesAre(5, 6, 9, 10);
    }

    @Test
    public void smallTreeCanBeSorted()
    {
        assertSortedValuesAre(5, 6, 8, 9, 10);
    }

    @Test
    public void sortedValuesIncludesDuplicates()
    {
        sampleTree.add(8);
        sampleTree.add(8);
        sampleTree.add(10);
        assertSortedValuesAre(5, 6, 8, 8, 8, 9, 10, 10);
    }

    @Test
    // Could fail intermittently, but that would still indicate a bug. Should add logging the random seed used in
    // order to be able to reproduce any failures.
    public void largeRandomBtreeCanBeSorted()
    {
        BTreeNode root = new BTreeNode(LARGE_TREE_SIZE / 2);
        for(int i=1; i<100; i++) {
            root.add(randNum(LARGE_TREE_SIZE));
        }

        assertListIsInOrder(root.sortedValues());
    }

    private static int randNum(int max) {
        return (int) (Math.random()*(max + 1));
    }

    private void assertSortedValuesAre(int... values)
    {
        List<Integer> sortedValues = sampleTree.sortedValues();
        assertThat(sortedValues.size(), is(values.length));
        for(int i=0; i<values.length; i++)
        {
            assertThat(sortedValues.get(i), is(values[i]));
        }
    }

    private void assertListIsInOrder(List<Integer> listToCheck)
    {
        for(int i=1; i<listToCheck.size(); i++)
        {
            assertThat(listToCheck.get(i) > listToCheck.get(i - 1), is(true));
        }
    }
}
