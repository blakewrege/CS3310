/**
 * Created by cpg on 11/10/2015.
 */
public class Heap extends BinaryTree {
    public Heap(int capacity) {
        super(capacity);
    }

    public void insert(Customer node) {
        int cursor = getNodeCount() + 1;
        insert(getNodeCount() + 1, node, true);
        /* Compare to parent */
        while (cursor > 1 && node.compareTo(getNodeAt(cursor / 2)) < 0) {
            insert(cursor, getNodeAt(cursor / 2), true);
            cursor /= 2;
        }

        insert(cursor, node, true);
    }

    public Customer deleteMin() {

        Customer root = getNodeAt(1);
        /* Put the last node at the front */
        insert(1, getNodeAt(getNodeCount()), true);
        delete(getNodeCount());

        /* Percolate down */
        minHeapify(1);

        return root;
    }

    private void minHeapify(int cursor) {
        int left = 2 * cursor;
        int right = 2 * cursor + 1;
        int smallest = cursor;

        if (left <= getNodeCount() && getNodeAt(left).compareTo(getNodeAt(smallest)) < 0) {
            smallest = left;
        }
        if (right <= getNodeCount() && getNodeAt(right).compareTo(getNodeAt(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != cursor) {
            Customer atCursor = getNodeAt(cursor);
            insert(cursor, getNodeAt(smallest), true);
            insert(smallest, atCursor, true);
            minHeapify(smallest);
        }
    }
}
