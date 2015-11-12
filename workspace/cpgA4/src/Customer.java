/**
 * Created by cpg on 11/9/2015.
 */
public class Customer {
    private String name;
    private int priority;

    public Customer(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    /**
     * Compares a node to another node.
     * @param node The node to compare this one to.
     * @return A positive value when this node is "greater" than the argument, and vice-versa for "lesser" nodes. 0 for ties.
     */
    int compareTo(Customer node) {
        return priority - node.getPriority();
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", name, priority);
    }
}
