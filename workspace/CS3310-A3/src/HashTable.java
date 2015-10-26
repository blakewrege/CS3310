import java.awt.event.FocusAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Formatter;

public class HashTable{
	private short n;
	private short maxId = 0;
	int MAX_SIZE = 499;
	int IdArr[] = new int[MAX_SIZE];
	String CodeArr[] = new String[MAX_SIZE];

	// Insert used by the Setup Program
	public void insert1Country(String code, short id, int count) throws IOException {
		CodeArr[count] = code;
		IdArr[count] = id;

	}

	public void Hash(int count,int HashFunc, int maxNHomeLoc, int ColRes, UIoutput output) {
		int hashnum = 0;
		int hashaddr = 0;
		for (int i = 0; i < count; i++) {

			hashnum = Hashformula(HashFunc, i);
			hashaddr = hashnum % maxNHomeLoc;

	//		System.out.println(CodeArr[i] + "  " + IdArr[i] + " " + hashnum + " " + hashaddr);
			System.out.println( hashnum);
			
			
			
			
			
			
		}
	}


	public int Hashformula(int select, int i) {
		String hashstr = "";
		switch (select) {
		case 1:
			return CodeArr[i].charAt(0) * CodeArr[i].charAt(1) * CodeArr[i].charAt(2);
			
		case 2:
			return CodeArr[i].charAt(0) + CodeArr[i].charAt(1) + CodeArr[i].charAt(2);
			
		case 3:
			hashstr = Integer.toString((int) CodeArr[i].charAt(0)) + Integer.toString((int) CodeArr[i].charAt(1))
					+ Integer.toString((int) CodeArr[i].charAt(2));
			return Integer.parseInt(hashstr);
		case 4:
			hashstr = Integer.toString((int) CodeArr[i].charAt(2)) + Integer.toString((int) CodeArr[i].charAt(1))
			+ Integer.toString((int) CodeArr[i].charAt(0));
			return Integer.parseInt(hashstr);

		}
		return i;

	}

	public void ReadAll(int count) {
		for (int i = 0; i < count; i++) {
			System.out.println(CodeArr[i] + "  " + IdArr[i]);

		}

	}

}
