import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class lab3main {
	public static void main(String[] args) throws IOException {
		Scanner kb = new Scanner(System.in);
		int options = 0,i;
		String strFile="",VIN="";
		String[] strVehicles,strSort;
		Vehicle v1;
        Car c1;
		Motorcycle m1;
		
		// Reads the File into a String
		File inFile = new File("vehicles.txt");
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        while (inputFile.hasNextLine()) {
			strFile = strFile +"@"+ inputFile.nextLine(); 
        }
        inputFile.close();
		strFile = strFile.replaceAll("@C", "@C,No");
		strFile = strFile.replaceAll("@M", "@M,No");
		 
		 //Loops the program until user inputs 3
		 while(options != 3){
	strVehicles = strFile.split("@");	
	System.out.printf("\n---Menu---\n1) Display Vehicles\n2) Rent a Vehicle\n3) Quit\n");
	options = kb.nextInt();
v1 = new Vehicle(strVehicles);
}
}
}
