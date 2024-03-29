//CLASS: Heap
//AUTHOR: Blake Wrege 

//************************************  Assignment 4  **********************************
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
	


	public void delete(int N) {

	        Node base = getNodeAt(0);
	        /* Put the last node at the front */
	        insert(0, getNodeAt(N-1), true);
	        remove(N);
			walkDown(N); 
//
//	        /* Percolate down */
//	        minHeapify(1);
//
//	        return root;
	    }
    private void walkDown(int n) {
int i=0;
int N = i;
//        int left = 2 * n;
//        int right = 2 * n + 1;
//        int smallest = n;
//        if (left <= n && getNodeAt(left).compareTo(getNodeAt(smallest)) < 0) {
//            smallest = left;
//        }
//        if (right <= n && getNodeAt(right).compareTo(getNodeAt(smallest)) < 0) {
//            smallest = right;
//        }
//        if (smallest != n) {
//            Node atCursor = getNodeAt(n);
//            insert(n, getNodeAt(smallest), true);
//            insert(smallest, atCursor, true);
//           walkDown(smallest);
//        }
    	

		int smallestChild = getSmallestChild(i,N);
    	while((2*i+1) <= (n-1) && (getNodeAt(i).getPriorityValue()  > getNodeAt(smallestChild).getPriorityValue()))
		{
    		System.out.println(getNodeAt(i).getPriorityValue()+ " "+ getNodeAt(smallestChild).getPriorityValue());
  //  	      insert(i, getNodeAt(smallestChild), true);
    	      switchNodes(i, smallestChild);
			
			i = smallestChild;
			
			smallestChild = getSmallestChild(i,N);
			
		}
   	
//    Node atCursor = getNodeAt(i);
//
//    insert(smallestChild, atCursor, true);
    	
		
	}
    
    
    
    
    
    
    
    
    private int getSmallestChild(int i,int N)
	{
		if((2*i+2) > (N-1) || nodes[2*i+1].getPriorityValue() <= nodes[2*i+2].getPriorityValue())
		{
			return (2*i)+1;
		}
		else
		{
			return (2*i)+2;
		}

	}

	public void remove(int index) {
        nodes[index] = null;
    }
	
}
