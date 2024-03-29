
//CLASS: CodeIndex
//AUTHOR: Blake
//************************************  Assignment 5  **********************************
import java.io.IOException;
import java.io.RandomAccessFile;

public class CodeIndex {
	private short M = 0;
	private short rootPtr = 0;
	private short N = 0;
	RandomAccessFile file;
	private short[] TP; // array of TPs for one node
	private String[] KV; // array of KVs for one node
	private short[] DRP; // array of DRPs for one node
	private int nodesRead; // iterator for nodes read
	private int sizeOfDataRec; // stores size of data record: (M - 1) * (7) + 2
	private int byteOffset;
	int loc;

	public CodeIndex(String fileName) throws IOException {

		file = new RandomAccessFile(fileName, "r");

	}

	// Reads the Header Record
	public void setupApp() throws IOException {
		// sets values from header record
		file.seek(0);
		M = file.readShort();
		rootPtr = file.readShort();
		N = file.readShort();
		sizeOfDataRec = (M - 1) * (7) + 2; // sets size of data record
		TP = new short[M];
		KV = new String[M - 1];
		DRP = new short[M - 1];
	}

	// searches for country code in code index from transaction data
	public int selectByCode(String code) throws IOException {
		nodesRead = 0;
		int result = searchOneNode(rootPtr, code);
		return result;
	}

	// reads one node into arrays
	private void readOneNode() throws IOException {
		nodesRead++;

		for (int j = 0; j < M - 1; j++) {
			KV[j] = "";
			TP[j] = file.readShort();
			for (int i = 0; i < 3; i++) {
				KV[j] += (char) file.readByte();
			}
			DRP[j] = file.readShort();
		}
		TP[TP.length - 1] = file.readShort();

	}

	// searches current node for code and sets the next code in tree to search
	private int searchOneNode(int pointer, String code) throws IOException {
		byteOffset(pointer);
		readOneNode();

		for (int k = 0; k < M - 1; k++) {
			// compares each node stored in KV to code
			if (code.compareTo(KV[k]) < 0) {
				if (TP[k] != -1) {
					return searchOneNode(TP[k], code);
				} else {
					return -1;
				}
			} else if (code.compareTo(KV[k]) == 0) {
				return DRP[k];
			} else if ((code.compareTo(KV[k]) > 0 && k + 1 == M - 1)
					|| (code.compareTo(KV[k]) > 0 && KV[k + 1].equals("]]]"))) {
				if (TP[k + 1] != -1) {
					return searchOneNode(TP[k + 1], code);
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

	// sets the byte offset to seek to for each node
	private void byteOffset(int pointer) throws IOException {

		byteOffset = 6 + ((pointer - 1) * sizeOfDataRec);
		if (byteOffset < file.length()) {
			file.seek(byteOffset);
		}
	}

	// closes the file
	public void finishUp() throws IOException {

		file.close();

	}

	// gets nodes read for log file
	public int getNodesRead() {
		return nodesRead;
	}

}
