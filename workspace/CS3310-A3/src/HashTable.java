import java.awt.event.FocusAdapter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;

public class HashTable {
	int nCase = 1;
	private short maxId = 0;
	int MAX_SIZE = 499;
	int IdArr[] = new int[MAX_SIZE];
	String CodeArr[] = new String[MAX_SIZE];
	int HomeArr[] = new int[MAX_SIZE];
	int LinkArr[] = new int[MAX_SIZE];
	int ColArr[] = new int[MAX_SIZE];
	int ColArrNew[] = new int[MAX_SIZE];
	String HomeLoc[] = new String[MAX_SIZE];

	int nColl = 0;
	int nHome = 0;

	// Insert used by the Setup Program
	public void insert1Country(String code, short id, int count) throws IOException {
		CodeArr[count] = code;
		IdArr[count] = id;

	}

	public void Hash(int count, int HashFunc, int maxNHomeLoc, int ColRes, UIoutput output) throws IOException {

		int hashnum = 0;
		int hashaddr = 0;
		for (int i = 0; i < count; i++) {

			hashnum = Hashformula(HashFunc, i);
			hashaddr = hashnum % maxNHomeLoc;

			// System.out.println(CodeArr[i] + " " + IdArr[i] + " " + hashnum +
			// " " + hashaddr);

			if (ColRes == 2) {
				ColArr[i] = hashaddr;
				HomeArr[i] = Myhome(hashaddr, i, maxNHomeLoc);
			}
			// System.out.println(CodeArr[i] + " " + HomeArr[i] + "
			// "+ColArr[i]);

		}

		if (ColRes == 2) {
			setLinks(maxNHomeLoc);
		}

		output.displayThis("N_HOME: " + nHome + ", N_COLL: " + nColl + " --> " + (nColl + nHome));
		output.displayThis("AVE SEARCH PATH (for successful):	(11+52)/(11+15) --> 2.4");
		if (nCase < 7) {

			output.displayThis("LOC  CODE DRP LINK");
			for (int i = 0; (i < maxNHomeLoc + nColl); i++) {
				format(i, output);
			}
		}

		nCase++;

	}

	public void setLinks(int maxNHomeLoc) {

		Arrays.fill(HomeLoc, "");

		Arrays.fill(ColArrNew, -1);
		for (int i = 0; i < (nHome + nColl); i++) {
			ColArrNew[HomeArr[i]] = ColArr[i];
		}
		for (int i = 0; i < (maxNHomeLoc + nColl); i++) {
			// System.out.println(i+" "+ ColArrNew[i]);
		}

		for (int i = 0; i < (maxNHomeLoc); i++) {
			if (ColArrNew[i] != -1) {
				System.out.print("Compare:" + ColArrNew[i] + " to ");

				for (int j = i + 1; j < (maxNHomeLoc + nColl); j++) {
					if (ColArrNew[i] == ColArrNew[j]) {
						LinkArr[i] = j;
						System.out.print(j + " ");
					}
				}
				System.out.println();
				System.out.println(LinkArr[i]);
			}
		}

		for (int i = maxNHomeLoc + nColl; i > (maxNHomeLoc); i--) {
			if (ColArrNew[i] != -1) {
				System.out.print("Compare:" + ColArrNew[i] + " to ");
				int j = i - 1;
				while (j > (maxNHomeLoc)) {
					// System.out.print(ColArr[j]+" ");
					if (ColArrNew[i] == ColArrNew[j]) {
						LinkArr[i] = j;
						System.out.print(j + " ");
						j = maxNHomeLoc;
					}
					j--;
				}
				System.out.println();
				System.out.println(LinkArr[i]);
			}
		}

		for (int i = 0; i < (nHome + nColl); i++) {
			HomeLoc[HomeArr[i]] = CodeArr[i] + " " + IdArr[i];
			if (LinkArr[i] == 0) {
				LinkArr[i] = -1;
			}

		}

		for (int i = 0; (i < maxNHomeLoc + nColl); i++) {
			// System.out.println(HomeLoc[i]);
			// System.out.println(CodeArr[i] + " " + HomeArr[i] + "
			// "+ColArr[i]);
		}

	}

	public void format(int loc, UIoutput output) throws IOException {
		StringBuffer buf = new StringBuffer();
		java.util.Formatter formatter = new java.util.Formatter(buf);
		if (HomeLoc[loc] != null && !HomeLoc[loc].isEmpty()) {
			String fields[] = HomeLoc[loc].split(" ");
			int id = Integer.parseInt(fields[1]);
			formatter.format("%03d> %s  %03d %03d", loc, new String(fields[0]), id, LinkArr[loc]);
			output.displayThis(buf.toString());
			formatter.close();
		} else {
			formatter.format("%03d>", loc);
			output.displayThis(buf.toString());
			formatter.close();
		}
	}

	private int Myhome(int hashaddr, int i, int maxNHomeLoc) {

		for (int j = 0; j < i; j++) {
			if (HomeArr[j] == hashaddr) {
				hashaddr = (maxNHomeLoc) + nColl;
				nColl++;
			}

		}
		if (hashaddr < maxNHomeLoc)
			nHome++;

		return hashaddr;
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
