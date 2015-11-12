/**
 * Created by cpg on 11/9/2015.
 */
import java.util.Arrays;

public class BinaryTree {
    private Customer[] nodes;

    public BinaryTree(int capacity) {
        this.nodes = new Customer[capacity];
    }

    public int getCapacity() {
        return nodes.length;
    }

    public Customer getNodeAt(int index) {
        return nodes[index];
    }

    public boolean isEmpty() {
        return getNodeCount() == 0;
    }

    public void insert(int index, Customer node, boolean overwrite) {
        if (index >= getNodeCount() || overwrite) {
            nodes[index] = node;
        }
    }

    public int getNodeCount() {
        int lastNonNull = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] != null) {
                lastNonNull = i;
            }
        }
        return lastNonNull;
    }

    public void delete(int index) {
        nodes[index] = null;
    }

    public Customer getLeftChild(int index) {
        return getNodeAt(2 * index);
    }

    public Customer getRightChild(int index) {
        return getNodeAt(2 * index + 1);
    }

    public Customer getParent(int index) {
        return getNodeAt(index / 2);
    }

    public void swapNodes(int index1, int index2) {
        Customer original1 = nodes[index1];
        nodes[index1] = nodes[index2];
        nodes[index2] = original1;
    }
}
