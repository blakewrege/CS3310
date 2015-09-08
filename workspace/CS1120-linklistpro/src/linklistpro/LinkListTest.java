package linklistpro;

public class LinkListTest {
	public static void main(String[] args) {
	    LinkList list = new LinkList();

	    list.insert('A', 1.01);
	    list.insert('B', 2.02);
	    list.insert('C', 3.03);
	    list.insert('D', 4.04);
	    list.insert('E', 5.05);

	    list.printList();

	    while(!list.isEmpty()) {
		    Node deletedLink = list.delete();
		    System.out.print("deleted: ");
		    deletedLink.printNode();
		    System.out.println("");
	    }
	    list.printList();
	    
	    
	    
	    
	    // uncomment this section to test (yet) unimplemented methods of LinkList
	    list.insert('F', 11.01);
	    list.insert('G', 12.02);
	    list.insert('H', 13.03);
	    list.insert('J', 14.04);
	    list.printList(); // make sure list got created correctly
	    char ch; double d;
	    ch = 'H'; d = 12.02;
	    System.out.println("testing findDoubleData("+ch+"): "+list.findDoubleData(ch));
	    System.out.println("testing findCharData("+d+"): "+list.findCharData(d));
	    System.out.print("testing findNode("+ch+","+d+"): ");
	    Node n2 = list.findNode(ch,d);
	    System.out.println("Node found: "+n2);
	    if (n2 != null) n2.printNode(); 
	    System.out.println("");
	    ch = 'F'; d = 11.01;
	    System.out.println("testing findNode("+ch+","+d+"): "+list.findNode(ch,d));
	    list.printList(); // make sure list did not inadvertently got changed by above find methods
	    list.deleteNode(n2);
	    System.out.println("test deletion of an arbitrary node");
	    list.printList(); // make sure node n2 got deleted from the list
	    list.insertAtEnd('E',55.05);
	    System.out.println("test insertion at the end of a list");
	    list.printList(); // make sure node got inserted correctly in the list
	    System.out.println("test reversal of a list");
	    LinkList rList = reverseList(list);
	    rList.printList(); // print the new reversed list
	    // could also print as reverseList(list).printList();
    }
	
	//a method to reverse a singly linked list, list1
	//nodes of the new linked list contain data of list1 in reverse order (last node to first)
	public static LinkList reverseList(LinkList list1){
		//add your code here
		return null; //change it to return appropriate value
	}
}


