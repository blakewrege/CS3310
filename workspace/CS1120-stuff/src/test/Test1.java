package test;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class Test1 {
public static void main(String[] args) throws FileNotFoundException {
	System.out.println("hello word");
	test2 poop1 = new test2();
	test2 poop2 = new test2(15);
	test2 poop3 = new test2(55);
	System.out.println(poop1.pr()+" "+poop2.pr()+" "+poop3.pr(20));
	List2 read = new List2("blarg", null);
	read.data = "poop"; 
	System.out.println(read);
//	List2 read2 = new List2("wtf", read);
//	System.out.println(read2.toString()+" "+read2.next);
//	Node data;
	List<String> frmfile = readthis("Account.txt");
	System.out.println(frmfile.get(0));
	
}



public static List<String> readthis(String meme) throws FileNotFoundException{
	File read = new File(meme);
	FileReader re = new FileReader(read);
	Scanner sc = new Scanner(re);
	String item="";
	List<String> lines = new ArrayList<String>();
	while (sc.hasNextLine()==true) {
		item =  sc.nextLine();
		lines.add(item);
	}
	sc.close();
	return lines;
}
//public static String tostring(List list1){
//	String lines = "";
//	for (int j = 0; j < list1.getRows(); j++) {
//	lines = lines +" "+ list1.getItem(j);	
//	}
//	
//	
//	return lines;
//	
//	
//	
//}

}
