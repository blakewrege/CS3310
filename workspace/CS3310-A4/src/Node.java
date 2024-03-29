//CLASS: Node
//AUTHOR: Blake Wrege 

//************************************  Assignment 4  **********************************
public class Node {
	private String name;
	private int value;
	
	
	public Node(String name, int value)
	{
		this.name = name;
		this.value = value;
		
	}


	public int getPriorityValue() {
		return value;
	}
	
	int compareTo(Node node) {
        return value - node.getPriorityValue();
    }


	public void setPriorityValue(int value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
