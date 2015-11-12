
public class Heap {

	private Node[] nodes;

	public Heap(int maxN) {
		nodes = new Node[maxN];
	}

	public Node getNodeAt(int index) {
		return nodes[index];
	}

	public Node getLC(int index) {
		return getNodeAt(2 * index);
	}

	public Node getRC(int index) {
		return getNodeAt(2 * index + 1);
	}

	public Node getParent(int index) {
		return getNodeAt(index / 2);
	}

	public void switchNodes(int index1, int index2) {
		Node temp = nodes[index1];
		nodes[index1] = nodes[index2];
		nodes[index2] = temp;
	}

	public void insert(Node node, int N) {

		walkUp(N, node);

	}

	private void walkUp(int i, Node node) {
	//	System.out.println(node.getName()); 

		while (i > 0 && node.getPriorityValue() <  getParent(i-1).getPriorityValue()) {

			insert(i, getNodeAt((i-1) / 2), true);
			i = (i-1)/2;
			
		}
		insert(i, node, true);
	}

	public void insert(int index, Node node, boolean overwrite) {
		if (index >= index-2 || overwrite) {
			nodes[index] = node;
			//System.out.println(index);
		}
	}
	
}
