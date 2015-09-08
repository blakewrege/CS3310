package linklistpro;

public class LinkList {
    private Node first;

    //LinkList constructor
    public LinkList() {
	    first = null;
    }

    //Returns true if list is empty
    public boolean isEmpty() {
	    return (first == null);
    }

    //Inserts a new Link at the first of the list
    public void insert(char d1, double d2) {
	    Node node = new Node(d1, d2);
	    node.nextLink = first;
	    first = node;
    }

    //Deletes the link at the first of the list
    public Node delete() {
	    Node temp = first;
	    first = first.nextLink;
	    return temp;
    }

    //Prints list data
    public void printList() {
	    Node currentLink = first;
	    System.out.print("List: ");
	    while(currentLink != null) {
		    currentLink.printNode();
		    currentLink = currentLink.nextLink;
	    }
	    System.out.println("");
    }
    
    //find a node with the char data d1 and return its double data value
    public double findDoubleData(char d1){
    	
    	Node current = first;
    	while (current!=null){
    		if(current.getCharData()==d1){
    		return current.getDoubleData();
    		}
    	}
    	
    	return Double.MIN_VALUE; //change it to return appropriate value
    }
    
    //find a node with the double data d2 and return its char data value
    public char findCharData(double d2){
    	Node current = first;
    	System.out.println(first);
    	while (current!=null){
    		if(current.getDoubleData()==d2){
    		return current.getCharData();
    		}
    	}
    	return Character.MIN_VALUE; //change it to return appropriate value
    }
    
    //find a node with the data d1 and d2 and return it
    public Node findNode(char d1, double d2){
System.out.println(d1 + "  " + d2);
    	return null; //change it to return appropriate value
    }
    
    
  //deleteNode(n1) method deletes a node referenced by n1
    public void deleteNode(Node n1) {
    	//add your code here
    }
    
    //insertAtEnd() method inserts a node with data d1 and d2 at the end of the list
    public void insertAtEnd(char d1, double d2){
    	//add your code here
    }	
	//deleteFromtEnd() method deletes the last node of the list and returns a reference to it
    public Node deleteFromEnd(){
    	//add your code here
		return null; // change it appropriately
    }	    
} 
