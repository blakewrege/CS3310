//CLASS: CustomerPQ
//AUTHOR: Blake Wrege 

//************************************  Assignment 4  **********************************

import java.io.IOException;

public class CustomerPQ {
	private int N;
	private final int maxN = 200;
	private Heap heap;
	private int initialPriorityValue = 100;
	private int priorityValue;
	// Heap newheap = new Heap();

	public CustomerPQ(UIoutput output) {
		// heap = new Node[maxN];
		heap = new Heap(maxN);
		N = 0;

	}

	public void addCustToPQ(String theLine, UIoutput output) throws IOException {
		// output.displayThis(theLine+" \n");
		String fields[] = theLine.split(",");
		for (int i = 0; i < fields.length; i++) {
			// fields[i] = info[i+1];

		}
		// System.out.print(fields[0]);
		// System.out.println(" Length: "+fields.length);
		determinePriorityValue(fields);
		// System.out.println(""+priorityValue);
		output.displayThis("ADDED:  " + fields[0] + " (" + priorityValue + ")");
		heapInsert(fields[0], priorityValue);

	}

	private void heapInsert(String value, int pv) {

		Node newNode = new Node(value, pv);
		heap.insert(newNode, N);
		N++;

	}

	public void dumpNodes(UIoutput output) throws IOException {

		output.displayThis("Dump of current heap (array) - " + N + " nodes:");
		output.displayThis(">> SUB  PV   NAME");

		for (int j = 0; j < N; j++) {

			dumpFormat(j, output);
//			System.out.println(heap.getNodeAt(j).getName());
		}
	}

	public void dumpFormat(int loc, UIoutput output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);
		formatter.format(">> %02d   %03d  %s ", loc, heap.getNodeAt(loc).getPriorityValue(),
				heap.getNodeAt(loc).getName());
		output.displayThis(buf.toString());
		formatter.close();
	}

	private void determinePriorityValue(String[] points) throws IOException {
		int age = new Integer(points[points.length - 1]);
		priorityValue = ++initialPriorityValue;

		for (int i = 1; i < points.length - 1; i++) {
			switch (points[i].toLowerCase()) {
			case "employee":
				priorityValue -= 10;
				break;
			case "owner":
				priorityValue -= (50 + 10);
				break;
			case "vip":
				priorityValue -= 5;
				break;
			case "supervip":
				priorityValue -= (8 + 5);
				break;
			case "loyalty":
				priorityValue -= 4;
				break;
			case "child":
				priorityValue -= 2;
				break;

			}
		}
		if (age >= 65) {
			priorityValue -= 5;

		}

		if (age >= 80) {
			priorityValue -= 5;
		}

	}

	public int getN() {

		return N;
	}

	public void serveNextCustInPQ(UIoutput output) throws IOException {
		
		output.displayThis("SERVED: " + heap.getNodeAt(0).getName() + " (" + heap.getNodeAt(0).getPriorityValue() + ")");
		heap.delete(N);
		N--;

//		heap[0] = heap[N-1];	
//		N--;
//		
//		walkDown(0);	
		
	}

}
