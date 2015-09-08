package edu.wmich.cs3310.a2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

/********************************
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class NameIndex {
	private short n, nextEmpty, rootPtr, visited;
	private final short MAX_N_HOME_LOC = 40;
	private BSTNode[] bstNodes;
	
	public NameIndex(){
		n = 0;
		nextEmpty = 0;
		rootPtr = -1;
		bstNodes = new BSTNode[MAX_N_HOME_LOC];
	}
	
	/*********************
	 * insertIntoNameIndex
	 * @param keyValue
	 * @param drp
	 */
	public void insertIntoNameIndex (String keyValue, short drp){
		bstNodes[nextEmpty] = new BSTNode();
		bstNodes[nextEmpty].name = keyValue;
		bstNodes[nextEmpty].drp = drp;
		bstNodes[nextEmpty].leftChPtr = -1;
		bstNodes[nextEmpty].rightChPtr = -1;
		visited = 0;
		
		if (rootPtr == -1) 
			 rootPtr = 0; 
		else {
			int i = rootPtr;
			int parentI = rootPtr;
			String side = "na";
			while (i != -1) {
				parentI = i;
				if (bstNodes[nextEmpty].name.compareTo(bstNodes[i].name) < 0){
					i = bstNodes[i].leftChPtr;
					side = "left";
				}
				else if (bstNodes[nextEmpty].name.compareTo(bstNodes[i].name) > 0){
					i = bstNodes[i].rightChPtr;
					side = "right";
				}
				else{
					bstNodes[nextEmpty] = null;
					nextEmpty--;
					i = -1;
				}
				visited++;
			}
			if (side.equals("left"))
				bstNodes[parentI].leftChPtr = nextEmpty;
			else if (side.equals("right"))
				bstNodes[parentI].rightChPtr = nextEmpty;
		}
		
		n++;
		nextEmpty++;
	}
	
	/*************************
	 * Loads directly to index
	 * @param keyValue
	 * @param drp
	 */
	public void load (short Lch, String keyValue, short drp, short Rch){
		bstNodes[nextEmpty] = new BSTNode();
		bstNodes[nextEmpty].name = keyValue;
		bstNodes[nextEmpty].drp = drp;
		bstNodes[nextEmpty].leftChPtr = Lch;
		bstNodes[nextEmpty].rightChPtr = Rch;
		nextEmpty++;
	}
	
	/*************************
	 * Load record header data
	 * @param n
	 * @param nextEmpty
	 * @param rootPtr
	 */
	public void loadHeader(short n, short nextEmpty, short rootPtr){
		this.n = n;
		this.nextEmpty = nextEmpty;
		this.rootPtr = rootPtr;
	}
	
	/*******************************
	 * SN command with binary search
	 * @param dt
	 * @param name
	 * @param log
	 * @throws IOException
	 */
	public void selectByName (DataTable dt, String name, Log log) throws IOException{
		short[] i = searchForName(name);
		if (i[0] != -1)
			dt.selectById(bstNodes[i[0]].drp, i[1], log, true);
		else
			log.displayThis(String.format(
					"   ERROR, invalid country name\n      >> %d %s visited",
					visited, (visited == 1) ? "node" : "nodes"));
	}
	
	/********************************
	 * Searches for target in the BST
	 * @param target
	 * @return
	 */
	private short[] searchForName(String target){
		short i = rootPtr; 
		visited = (short)(rootPtr+1);
		while (i != -1 && !target.equals(bstNodes[i].name)) { 
			if (target.compareTo(bstNodes[i].name) < 0)
				i = bstNodes[i].leftChPtr;
			else 
				i = bstNodes[i].rightChPtr;
			visited++;
		} 
		short[] result = {i, visited};
		return result;
	}
	
	/********************************************************************************
	 * DN command
	 * @param dr
	 * @param name
	 * @param log
	 * @throws IOException
	 */
	public void deleteByName (DataTable dt, String name, Log log) throws IOException{
		log.displayThis("   [SORRY, Delete By Name module not yet working]");
	}

	/**********************************************************************
	 * AN command
	 * @param dt
	 * @param log
	 * @throws IOException
	 */
	public void selectAllByName(DataTable dt, Log log) throws IOException {
		log.displayThis("  "+log.header());
		LNR(rootPtr, dt, log);
		log.displayThis("  "+log.footer());	
	}

	/*********************************************
	 * Inorder traversal algorithm using recursion
	 * @param i Search index
	 * @param tl TheLog object
	 * @throws IOException
	 */
	private void LNR(short i, DataTable dt, Log log) throws IOException{
		if(bstNodes[i].leftChPtr != -1){
			LNR(bstNodes[i].leftChPtr, dt, log);
			dt.selectById(bstNodes[i].drp, (short)0, log, false);	
		}else 
			dt.selectById(bstNodes[i].drp, (short)0, log, false);
		
		if(bstNodes[i].rightChPtr != -1){
			LNR(bstNodes[i].rightChPtr, dt, log);
		}
			
	}

	/********************
	 * Getter for visited
	 * @return
	 */
	public short getVisited(){
		return visited;
	}
	
	/*******************************
	 * Dumps nameIndex to Backup.txt
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		Backup backup = new Backup();
		backup.displayThis(String.format("%d'%d'%d", n, nextEmpty, rootPtr));
		for (short x = 0; x < n; x++)
			backup.displayThis(backup.nameFormat(bstNodes[x].leftChPtr,
					bstNodes[x].name, bstNodes[x].drp, bstNodes[x].rightChPtr));
		backup.finishUp();
	}
	
}
