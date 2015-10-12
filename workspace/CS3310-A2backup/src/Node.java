//CLASS: Node - used by DataStorage Class
//AUTHOR: JIA GUO
//DESCRIPTION: Node for BST. Two constructors are included, which are used both for empty and non-empty tree.
//		cleanUp method is used to clean up restOfData (id, name, continent, area, population and lifeExpentency)
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************



public class Node 
{
	int leftChildPtr;
	int rightChildPtr;
	String countryCode;
	String id;
	String restOfData;//contains name, continent, area, population and life (separated by ",")
	String name;
	String continent;
	String area;
	String population;
	String life;

	//Constructor for empty tree
	public Node(String countryCode, String restOfData)
	{
		this.countryCode = countryCode;
		this.restOfData = restOfData;
		cleanUp(restOfData);
		leftChildPtr = -1;
		rightChildPtr = -1;
	}
	
	//Constructor for non-empty tree.
	public Node(String countryCode, String restOfData, int leftChildPtr, int rightChildPtr)
	{
		this.countryCode = countryCode;
		this.restOfData = restOfData;
		cleanUp(restOfData);
		this.leftChildPtr = leftChildPtr;
		this.rightChildPtr = rightChildPtr;
	}
	
	//Clean up restOfData
	public void cleanUp(String restOfData)
	{
		String [] field = restOfData.split(",");
		id = field[0];
		name = field[1];
		continent = field[2];
		area = field[3];
		population = field[4];
		life = field[5];
	}
}
