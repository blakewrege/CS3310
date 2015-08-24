/**
 * Author: Blake Wrege 
 * Date: Feb 18, 2014 
 * Class: CS1120 
 * Lab3
 */

import java.io.File;
import java.util.Scanner;
import java.io.*;




public class Vehicle {
	public static void main(String[] args) throws IOException {
		Scanner kb = new Scanner(System.in);
		int options = 0,i;
		String strFile="",VIN="";
		String[] strVehicles,strSort;
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

// Displays all of the Vehicles
		if(options == 1){
			for(i=2;i<strVehicles.length;i++){
			strSort = strVehicles[i].split(",");
				if(strSort[0].equalsIgnoreCase("C")){
				c1 = new Car(strVehicles[i]);	
				c1.Display();
				}
				if(strSort[0].equalsIgnoreCase("M")){
				m1 = new Motorcycle(strVehicles[i]);	
				m1.Display();
				}
			}
			
// allows the user to rent a Vehicle then prints dates for rented Vehicle
	}else if(options == 2){
	System.out.printf("Enter the call number: ");
	kb.nextLine();
	VIN = kb.next();
	strFile = strFile.replace("No,"+VIN.trim(),"Yes,"+VIN.trim());
	strVehicles = strFile.split("@");	
		for(i=2;i<strVehicles.length;i++){
		strSort = strVehicles[i].split(",");
				if(strSort[2].equalsIgnoreCase(VIN)){
					if(strSort[0].equalsIgnoreCase("C")){
					c1 = new Car(strVehicles[i]);	
					c1.Display();
					}
					if(strSort[0].equalsIgnoreCase("M")){
					m1 = new Motorcycle(strVehicles[i]);	
					m1.Display();
					}
				}
		}
}			
}
}
}
	

			
		
		
		
		

		
	


