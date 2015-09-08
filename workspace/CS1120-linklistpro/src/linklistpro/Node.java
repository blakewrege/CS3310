package linklistpro;
public class Node {
	private char data1;//each node contains two data values - a double and a char
    private double data2;
    public Node nextLink; // allows us to link to next node

    //Node constructors
    public Node () {
	    data1 = ' ';
	    data2 = 0.0;
    }
    public Node (char d1, double d2) {
	    data1 = d1;
	    data2 = d2;
    }

    //Print Link data
    public void printNode() {
	    System.out.print("{" + data1 + ", " + data2 + "} ");
    }
    
    //return string formatted data of the node by overriding toString()
    public String toString(){
    	return ("{" + data1 + ", " + data2 + "} ");
    }
    //get and set node data
    public char getCharData(){
    	return data1;
    }
    public double getDoubleData(){
    	return data2;
    }
    public void setCharData(char inCh){
    	data1 = inCh;
    }
    public void setDoubleData(double d){
    	data2 = d;
    }    
}

