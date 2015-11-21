package edu.wmich.cs3310.asgn5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ShortestPath {
	private UI ui;
	private Map map;
	private boolean search;
	private boolean[] included;
	private short[] path, distance;
	private short N, startNumber, destNumber;
	private ArrayList<Short> trace; 	
	
	public ShortestPath(Map map, UI ui) {
		this.ui = ui;
		this.map = map;
		N = map.getN();
	}
	
	public void findPath(short startNumber, short destNumber) throws IOException{
		ui.writeThis(true,"#   #   #   #   #   #   #   #   #   #   #   #   #   #   #   #");
		ui.writeThis(true, String.format("%s (%d) TO %s %(d)",
				map.whatsCityName(startNumber), startNumber,
				map.whatsCityName(destNumber), destNumber));

		if (startNumber < 0 || destNumber < 0 || startNumber >= N|| destNumber >= N) {
			ui.writeThis(true, "ERROR – one of the cities is not on this map\n\n");
		}else{
			this.startNumber = startNumber;
			this.destNumber = destNumber;
			
			initialize();		
			search = search();
			reportAnswer();
			reportTraceOfTargets();
		}
	}
	
	private void initialize(){
		path = new short[N];
		distance = new short[N];
		included = new boolean[N];
		
		Arrays.fill(included, false);
		included[startNumber] = true;
		
		Arrays.fill(distance, Short.MAX_VALUE);
		distance[startNumber] = 0;
		
		Arrays.fill(path, (short)-1); 
		
		trace = new ArrayList<Short>();
	}
	
	private boolean search() throws IOException{
		while (!included[destNumber]) {
			/*1. out of nodes NOT yet included, choose target node (its node number) as
			 *the node with the minimum distance value [target is a subscript not a distance]*/
			short targetNode = -1;
			for (short x = 0; x < N; x++){
				if (!included[x] && (targetNode == -1 || distance[x] < distance[targetNode])) 
					targetNode = x;
			}
					
			/*2. target now becomes included 
			 *[as having been evaluated in step 3 below, to see it’s effect]*/
			included[targetNode] = true;
		
			/*3. check all distance values [they’re ceilings] to see 
			 *which ones can be lowered [i.e., loop: i = 0 to N-1]*/
			for (short i=0; i < N; i++) {	
				
				
				
				
			}
			
//			if included[i] is false [GUARD #1 against doing the BIG TEST unnecessarily]
//			if edgeWeight from target to i is a valid edgeWeight [GUARD#2 against doing …]
//			[as opposed to a non-edge of 0 or “infinity”]
//			[Finally comes the “BIG TEST” – i.e., should distance[i] ceiling be lowered?]
//			if distance[target] + edgeWeight from target to i < distance[i]
//			then: 1) distance[i] = distance[target] + edgeWeight from target to i
//			2) path[i] = target
			
		}
		return true;
	}
	
	private void reportAnswer(){
		
	}
	
	private void reportTraceOfTargets(){
		
	}
	
	public void finishUp() throws IOException {
		
	}
}
