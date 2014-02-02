public abstract class AbstractHeap
{
    private HeapNode root;
    private boolean sideToggle;

    public boolean isEmpty()
    {
        return root == null;
    }

    public AbstractHeap push(int value)
    {
        HeapNode node = new HeapNode(value);
        if(root == null)
        {
            root = node;
        }
        else
        {
            addTo(root, node);
        }
        return this;
    }

    public int pop()
    {
        if(root == null) throw new IllegalStateException("Cannot get the head of an empty heap");

        int result = root.getValue();
        removeNode(root);
        return result;
    }

    // Assumes that targetNode's parent is before nodeToAdd in the collating sequence.
    private void addTo(HeapNode targetNode, HeapNode nodeToAdd)
    {
        if(isAfterOrEqual(targetNode, nodeToAdd))
        {
            if(!targetNode.addChild(nodeToAdd))
            {
                addAsIndirectChild(targetNode, nodeToAdd);
            }
        }
        else
        {
            targetNode.swapValueWith(nodeToAdd);
            addTo(targetNode, nodeToAdd);
        }
    }

    // Adds to either left or right in a (hopefully) balanced way.
    private void addAsIndirectChild(HeapNode targetNode, HeapNode nodeToAdd)
    {
        sideToggle = !sideToggle;
        if(sideToggle)
        {
            addTo(targetNode.getLeft(), nodeToAdd);
        }
        else
        {
            addTo(targetNode.getRight(), nodeToAdd);
        }
    }

    private void removeNode(HeapNode node)
    {
        if(node.isChildless())
        {
            // As removeNode is always called from the root down in a controlled manner, this should never happen.
            if(node != root) throw new IllegalArgumentException("Can't remove childless node directly unless root");

            root = null;
        }
        else if(node.getLeft() == null)
        {
            node.replaceWithRightChild();
        }
        else if(node.getRight() == null)
        {
            node.replaceWithLeftChild();
        }
        else
        {
            HeapNode highestChild = isAfterOrEqual(node.getLeft(), node.getRight()) ? node.getLeft() : node.getRight();
            {
                // Really only need to set value here, but swapping does no harm.
                node.swapValueWith(highestChild);
                if(highestChild.isChildless())
                {
                    node.pruneChild(highestChild);
                }
                else
                {
                    removeNode(highestChild);
                }
            }
        }
    }

    // Implement this to establish the collating sequence of the heap.
    protected abstract boolean isAfterOrEqual(HeapNode nodeBeingChecked, HeapNode nodeToCheckAgainst);
}
