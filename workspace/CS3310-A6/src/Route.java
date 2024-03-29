
//CLASS: Route
//AUTHOR: Blake Wrege 
//DESCRIPTION: This was useful: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
//******************************  Assignment 6  ****************************

import java.io.IOException;
import java.util.*;

public class Route {
	private List<Short> currentTrace;
	private List<Short> nodesList;
	private short[] distanceArr;
	private short[] prevNodes;

	// Sets up arrays to be used
	@SuppressWarnings({"unchecked", "rawtypes"})
	private void setupArrays(short N) {
		this.currentTrace = new ArrayList();
		this.nodesList = new ArrayList();
		this.distanceArr = new short[N];
		this.prevNodes = new short[N];
		// Sets default distance to infinity
		for (short i = 0; i < N; i++) {
			this.nodesList.add(i);
			this.prevNodes[i] = -1;
			this.distanceArr[i] = Short.MAX_VALUE;
		}

	}

	// Called from DrivingApp to find distance
	public void findShortestPath(short n, short startNum, short endNum, Map map,
			Log output) throws IOException {
		this.setupArrays(n);
		this.searchPath(startNum, endNum, map);
		this.getResults(startNum, endNum, map, output);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private void getResults(short startNum, short endNum, Map map, Log output)
			throws IOException {
		// Creates a stack to store path data
		Deque<Short> drivePath = new ArrayDeque();
		short currentNode = endNum;

		// loops while still on the path
		boolean revisited = false;
		while (this.prevNodes[currentNode] >= 0 && currentNode != startNum
				&& revisited == false) {
			// Clears stack and exits if cities are revisited
			if (drivePath.contains(Short.valueOf(currentNode))) {
				drivePath.clear();
				revisited = true;
			} else {
				drivePath.push(currentNode);
				currentNode = prevNodes[currentNode];
			}

		}
		drivePath.push(startNum);
		// Checks to make sure a valid distance
		if ((this.distanceArr[endNum] >= 0) == false
				|| (this.distanceArr[endNum] == Short.MAX_VALUE) == true) {
			output.displayThis("DISTANCE:  ? \n");
		} else {
			output.displayThis("DISTANCE:  " + this.distanceArr[endNum] + "\n");
		}
		// Prints each path element
		output.displayThis("PATH:  ");
		if (drivePath.size() > 0 && this.distanceArr[endNum] >= 0
				&& (this.distanceArr[endNum] == Short.MAX_VALUE) == false) {
			boolean first = true;
			while (drivePath.size() > 0) {
				if (first == false) {
					output.displayThis(" > ");
				}
				// Pops path element off the stack
				output.displayThis(map.getCityName(drivePath.pop()).trim());
				first = false;
			}
		} else {
			output.displayThis(
					"SORRY - can NOT get to destination city from start city");
		}
		// Prints trace info to Log.txt
		output.displayThis("\nTRACE OF TARGETS: ");
		int i;
		for (i = 1; i < currentTrace.size(); i++) {
			output.displayThis(String.format(" %s ",
					map.getCityCode(currentTrace.get(i))));
		}
		output.displayThis(String.format(" %s\r\n", map.getCityCode(endNum)));
		// Prints targets info to Log.txt
		output.displayThis(String.format("# TARGETS: %d \n\n", i));
	}

	private void searchPath(short startNum, short destinationNum, Map map)
			throws IOException {
		this.distanceArr[startNum] = 0;

		// Loops until all nodes are visited
		boolean destination = false;
		while (destination == false && this.nodesList.size() > 0) {
			// Set the current node to the shortest distance
			short currentNode = this.nodesList.get(0);
			for (int nodeI = 0; nodeI < this.nodesList.size(); nodeI++) {
				if (this.distanceArr[this.nodesList
						.get(nodeI)] < this.distanceArr[currentNode]) {
					currentNode = this.nodesList.get(nodeI);
				}
			}

			// checks if destination has been reached and ends if true
			if (currentNode == destinationNum) {
				destination = true;
			} else {

				// Sets the node as visited.
				if (nodesList.contains(Short.valueOf(currentNode))) {
					nodesList.remove(Short.valueOf(currentNode));
					this.currentTrace.add(Short.valueOf(currentNode));
				}
				for (short k = 0; k < map.getN(); k++) {
					short distance = map.getRoadDistance(currentNode, k);
					// Compares distance to max short and current node
					if (distance < Short.MAX_VALUE && k != currentNode) {
						short alt = (short) (this.distanceArr[currentNode]
								+ distance);

						if (alt < this.distanceArr[k]) {
							this.distanceArr[k] = alt;
							this.prevNodes[k] = currentNode;
						}
					}
				}
			}
		}
	}

}
