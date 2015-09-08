package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Manages countries in a table
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class CountryDataTable {
	private int RootPtr;
	private int N, nextEmpty;
	private Node[] BstNodes;

	/**
	 * Constructor
	 */
	public CountryDataTable(){
		RootPtr = -1;
		N = 0;
		nextEmpty = 0;
		BstNodes = new Node[299];
	}	
	
	/**
	 * @param target Node to compare
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void SelectByName(String target, TheLog tl) throws IOException{
		int[] i = SearchForName(target);
		if (i[0] != -1 && !BstNodes[i[0]].code.equals("XXX"))
			tl.displayThis("   "+tl.Country(BstNodes[i[0]].code,BstNodes[i[0]].name,BstNodes[i[0]].continent,
							BstNodes[i[0]].area,BstNodes[i[0]].population, BstNodes[i[0]].lifeExpectancy)
						    +"\n"+tl.Visited(i[1]));
		else
			tl.displayThis(tl.Sorry()+"\n"+tl.Visited(i[1]));
	}
	
	/**
	 * Searches for target in the BST
	 * @param target
	 * @return
	 */
	private int[] SearchForName(String target){
		int i = RootPtr; 
		int visited = RootPtr+1;
		while (i != -1 && !target.equals(BstNodes[i].name.toLowerCase())) { 
			if (target.compareTo(BstNodes[i].name.toLowerCase()) < 0)
				i = BstNodes[i].LChPtr;
			else 
				i = BstNodes[i].RChPtr;
			visited++;
		} 
		int[] result = {i,visited};
		return result;
	}
	
	/**
	 * Execution for SA command which displays table 
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void SelectAll(TheLog tl) throws IOException{
		tl.displayThis("   "+tl.Header1());
		LNR(RootPtr,tl);
		tl.displayThis("   "+tl.Footer());
	}
	
	/**
	 * Inorder traversal algorithm using recursion
	 * @param i Search index
	 * @param tl TheLog object
	 * @throws IOException
	 */
	private void LNR(int i, TheLog tl) throws IOException{
		if(BstNodes[i].LChPtr != -1){
			LNR(BstNodes[i].LChPtr,tl);
			if (!BstNodes[i].code.equals("XXX"))
				tl.displayThis(tl.Country("   "+BstNodes[i].code,BstNodes[i].name,
							   BstNodes[i].continent,BstNodes[i].area,BstNodes[i].population,
							   BstNodes[i].lifeExpectancy));
		}
		else 
			if (!BstNodes[i].code.equals("XXX"))
				tl.displayThis(tl.Country("   "+BstNodes[i].code, BstNodes[i].name, 
							   BstNodes[i].continent,BstNodes[i].area, BstNodes[i].population,
							   BstNodes[i].lifeExpectancy));
		
		if(BstNodes[i].RChPtr != -1){
			LNR(BstNodes[i].RChPtr,tl);
		}
			
	}

	/**
	 * Insert country in table 
	 * @param name
	 * @param continent
	 * @param code
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @param tl TheLog object
	 * @param userApp boolean to determine if confirmation message should be logged
	 * @throws IOException
	 */
	public void Insert(String name, String continent, String code, int area, 
					   int population, float lifeExpectancy, TheLog tl, 
					   boolean userApp) throws IOException{
		BstNodes[nextEmpty] = new Node();
		BstNodes[nextEmpty].name = name;
		BstNodes[nextEmpty].continent = continent;
		BstNodes[nextEmpty].code = code;
		BstNodes[nextEmpty].area = area;
		BstNodes[nextEmpty].population = population;
		BstNodes[nextEmpty].lifeExpectancy = lifeExpectancy;
		BstNodes[nextEmpty].LChPtr = -1;
		BstNodes[nextEmpty].RChPtr = -1;
		int visited = 0;
		
		if (RootPtr == -1) 
			 RootPtr = N; 
		else {
			int i = RootPtr;
			int parentI = RootPtr;
			String side = "na";
			while (i != -1) {
				parentI = i;
				if (BstNodes[nextEmpty].name.compareTo(BstNodes[i].name) < 0){
					i = BstNodes[i].LChPtr;
					side = "left";
				}
				else if (BstNodes[nextEmpty].name.compareTo(BstNodes[i].name) > 0){
					i = BstNodes[i].RChPtr;
					side = "right";
				}
				else{
					BstNodes[nextEmpty] = null;
					nextEmpty--;
					BstNodes[i].code = code;
					BstNodes[i].continent = continent;
					BstNodes[i].area = area;
					BstNodes[i].population = population;
					BstNodes[i].lifeExpectancy = lifeExpectancy;
					i = -1;
				}
					
				
				visited++;
			}
			if (side.equals("left"))
				BstNodes[parentI].LChPtr = nextEmpty;
			else if (side.equals("right"))
				BstNodes[parentI].RChPtr = nextEmpty;
		}
		
		if (userApp)
			tl.displayThis(tl.Msg("inserted")+"\n"+tl.Visited(visited));
		
		N++;
		nextEmpty++;
	}
	
	/**
	 * Static delete for country in table
	 * @throws IOException 
	 */
	public void Delete(String target, TheLog tl) throws IOException{
		int[] i = SearchForName(target);
		if (i[0] != -1 && !BstNodes[i[0]].code.equals("XXX")){
			BstNodes[i[0]].code = "XXX";
			BstNodes[i[0]].continent = "   ";
			BstNodes[i[0]].area = 0;
			BstNodes[i[0]].population = 0;
			BstNodes[i[0]].lifeExpectancy = 0;
			N--;
			tl.displayThis(tl.Msg("deleted"));
		}
		else{
			tl.displayThis(tl.Sorry());
		}
	}
	
	/**
	 * Prints all contries and Tobstones
	 * @param tl TheLog object
	 * @throws IOException
	 */
	private void Snapshot(TheLog tl) throws IOException{
		tl.displayThis("CODE STATUS > Snapshot started\n");
		DecimalFormat noFormat = new DecimalFormat("#000");
		tl.displayThis("N: "+N+", NextEmpty: "+nextEmpty+",  RootPtr: "+RootPtr+"\n");
		
		int i = 0;
		tl.displayThis(tl.Header2());
		while (BstNodes[i] != null){
			if(BstNodes[i].code != "XXX")
				tl.displayThis(tl.Country2(i,BstNodes[i].code,BstNodes[i].name, 
						BstNodes[i].continent,BstNodes[i].area,BstNodes[i].population,
						BstNodes[i].lifeExpectancy,BstNodes[i].LChPtr,BstNodes[i].RChPtr));
			else
				tl.displayThis("["+noFormat.format(i)+"] "+"TOMBSTONE");
			i++;
		}
		tl.displayThis(tl.Footer()+tl.Footer2());
		tl.displayThis("CODE STATUS > Snapshot finished - "+i+" nodes displayed");
	}
	
	/**
	 * Calls Snapshot utility and restarts information in CDT
	 * @param tl TheLog object
	 * @param printTable
	 * @throws IOException
	 */
	public void FinishUp(TheLog tl, boolean printTable) throws IOException{
		if (printTable)
			Snapshot(tl);
		RootPtr = -1;
		N = 0;
		nextEmpty = 0;
	}
}
