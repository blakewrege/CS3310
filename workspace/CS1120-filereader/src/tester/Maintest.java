package tester;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maintest {
public static void main(String[] args) throws IOException {
	
	
    FileReader fileReader = new FileReader("account.txt");
	
    BufferedReader reader =  new BufferedReader(fileReader);
   System.out.println(reader.readLine());
    reader.close();
	
}
}
