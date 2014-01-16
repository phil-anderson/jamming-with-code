public class IterableBTreeNode
{
    public IterableBTreeNode left, right;
    public int value;
    private IterableBTreeNode parent;

    public IterableBTreeNode(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public IterableBTreeNode getNext()
    {
        if (right != null) {
            return getMinChild(right);
        }
        IterableBTreeNode currentNode = this;
        while(parent != null) {
            if (parent.left == currentNode) {
                return parent;
            }
            currentNode = parent;
            parent = currentNode.parent;
        }
        return null;
    }

    private static IterableBTreeNode getMinChild(IterableBTreeNode startNode) {
        while (startNode.left != null) {
            startNode = startNode.left;
        }
        return startNode;
    }
}
