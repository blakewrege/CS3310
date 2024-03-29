import java.io.IOException;
import java.util.*;

/**
 * Created by cpg on 12/6/15.
 */
public class Route {
    private short[] tentativeDistances;
    private short[] prevNodes;
    private List<Short> currentNodesTrace;
    private List<Short> unvisitedNodes;
    private List<Short> finalTraversal;

    public Route() {

    }

    public void finishUp() {

    }

    public void findMinCostPath(short startNum, short destinationNum, short n, Map map, Log log) throws IOException {
        this.initialize3ScratchArrays(n);
        this.searchForPath(startNum, destinationNum, map);
        this.reportAnswer(startNum, destinationNum, map, log);
    }

    private void initialize3ScratchArrays(short N) {
        // Set all tentative distances to "infinity."
        // Set all nodes to unvisited.
        this.tentativeDistances = new short[N];
        this.prevNodes = new short[N];
        this.unvisitedNodes = new ArrayList();
        this.currentNodesTrace = new ArrayList();

        for (short i = 0; i < N; i++) {
            this.tentativeDistances[i] = Short.MAX_VALUE;
            this.prevNodes[i] = -1; // "undefined" sentinel
            this.unvisitedNodes.add(i);
        }

        // Initialize an empty final traversal array.
        this.finalTraversal = new ArrayList();
    }

    private void searchForPath(short startNum, short destinationNum, Map map) throws IOException {
        // Set the current node's tentative distance to 0.
        this.tentativeDistances[startNum] = 0;

        // While we have unvisited nodes:
        while (this.unvisitedNodes.size() > 0) {
            // Set the current node to the currently smallest tentative distance.
            short currentNode = this.unvisitedNodes.get(0);
            for (int nodeI = 0; nodeI < this.unvisitedNodes.size(); nodeI++) {
                if (this.tentativeDistances[this.unvisitedNodes.get(nodeI)] < this.tentativeDistances[currentNode]) {
                    currentNode = this.unvisitedNodes.get(nodeI);
                }
            }

            // If that node happens to be our destination, we are done.
            if (currentNode == destinationNum) {
                break;
            }

            // The node has been visited.
            if (unvisitedNodes.contains(Short.valueOf(currentNode))) {
                unvisitedNodes.remove(Short.valueOf(currentNode));
                this.currentNodesTrace.add(Short.valueOf(currentNode));
            }

            // For each neighbor of currentNode:
            for (short n = 0; n < map.getN(); n++) { // n = (Possible) Neighbor
                short distance = map.getRoadDistance(currentNode, n);
                // We have a neighbor if there is a distance less than MAX_VALUE ("infinity") AND...
                // We have a neighbor if the node is not the same as the current:
                if (distance < Short.MAX_VALUE && n != currentNode) {
                    short alt = (short) (this.tentativeDistances[currentNode] + distance);

                    if (alt < this.tentativeDistances[n]) {
                        this.tentativeDistances[n] = alt;
                        this.prevNodes[n] = currentNode;
                    }
                }
            }
        }
    }

    private void reportAnswer(short startNum, short destinationNum, Map map, Log log) throws IOException {
        // Create a stack data structure to store the path.
        Deque<Short> path = new ArrayDeque();
        short currentNode = destinationNum;

        // While we are still on the path:
        while (this.prevNodes[currentNode] >= 0 && currentNode != startNum) {
            // Have we reached a city again? That means the destination is unreachable.
            if (path.contains(Short.valueOf(currentNode))) {
                path.clear();
                break;
            } else {
                path.push(currentNode);
            }
            currentNode = prevNodes[currentNode];
        }
        path.push(startNum);

        // Print the distance
        log.displayThisLine(String.format("DISTANCE:  %s", this.tentativeDistances[destinationNum] > 0 ?
                                                           Short.toString(this.tentativeDistances[destinationNum]) :
                                                           "?"));

        // Print the path
        log.displayThis("PATH:  ");
        if (path.size() > 0 && this.tentativeDistances[destinationNum] > 0) {
            boolean first = true;
            while (path.size() > 0) {
                if (!first) {
                    log.displayThis(" > ");
                }
                log.displayThis(map.getCityName(path.pop()).trim());
                first = false;
            }
        } else {
            log.displayThis("SORRY - can NOT get to destination city from start city");
        }
        log.displayThis("\r\n");

        // Print trace of targets
        log.displayThis("TRACE OF TARGETS: ");
        int i = 1;
        for (i = 1; i < currentNodesTrace.size(); i++) {
            log.displayThis(String.format(" %s", map.getCityCode(currentNodesTrace.get(i))));
        }
        log.displayThis(String.format(" %s\r\n", map.getCityCode(destinationNum)));

        log.displayThisLine(String.format("# TARGETS: %d", i));
    }
}
