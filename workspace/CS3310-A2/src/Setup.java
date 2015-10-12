//PROGRAM: Setup
//AUTHOR: Jia Guo
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage based on data in the RawData file. 
//		Since DataStorage is built as an INTERNAL storage structure, it needs to be saved to an EXTERNAL storage 
//		structure (the Backup file) after it’s completely built (as the last step in Setup) in order for UserApp 
//		to be able to use it – it calls finishUp method in DataStorage class which handles this. Status messages 
//		are sent to displayThis method in UIoutput class, as needed. This controller program uses the “sequential file processing” 
//		design pattern.
//Regarding any other issues, please feel free to contact me via email: jia.guo@wmich.edu
//*****************************************************************************************************



import java.io.*;

public class Setup {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		
		
		
		RawData input = new RawData();
		Log output  = new Log(true); 
		DataStorage stor = new DataStorage("DataStorage.bin", output);
		
//		UIoutput output = new UIoutput(); //Open in truncate mode
//		
//		output.displayThis("-->> SETUP started");
//		output.displayThis("-->> OPENED RawData file");
//		output.displayThis("-->> OPENED Log file\n");
		
		
		while(input.input1Country()) //loop through the end of the file
		{
			
//			stor.insert1Country(input.getCode(), input.getId(), input.getName(),
//			input.getContinent(), input.getArea(), input.getPopulation(),
//			input.getLifeExpectancy(), output);
//		

		}
		
		RandomAccessFile file = new RandomAccessFile("DataStorage.bin", "rw");
		file.seek(4);
		file.writeShort((short)12);	
		file.write("MEX".getBytes());
		file.write("Mexico            ".getBytes());
		file.write("North America".getBytes());
		file.writeInt(1958201);
		file.writeLong(98881000);
		file.writeFloat((float)71.5);
		
		file.writeShort((short)3);	
		file.write("CHN".getBytes());
		file.write("China             ".getBytes());
		file.write("Eastern Asia ".getBytes());
		file.writeInt(9572900);
		file.writeLong(1277558000);
		file.writeFloat((float)71.4);
		file.close();
		
		
		
	}
}
		
		
		
		
//		
//public void addCountry(Country country) throws Exception {
//    file.seek(0);
//    short n = file.readShort();
//    short maxId = file.readShort();
//    if (country.getId() > maxId || country.getId() <= 0) {
//        throw new Exception("Invalid country ID.");
//    }
//
//    // Find the beginning of the record.
//    file.seek(4 + country.getId() * COUNTRY_SIZE);
//
//    // Read the ID.
//    short id = file.readShort();
//    if (id == country.getId()) {
//        throw new Exception("Invalid (duplicate) country ID.");
//    } else {
//        file.writeChars(country.getCode());
//        file.writeChars(String.format("%1$-" + 18 + "s", country.getName()));
//        file.writeChars(String.format("%1$-" + 13 + "s", country.getContinent()));
//        file.writeInt(country.getArea());
//        file.writeLong(country.getPopulation());
//        file.writeFloat(country.getLifeExpectancy());
//    }
//}
//		
		
		
		
		
		
		
//		
//		{
//			
//			try {
//
//				String content = "12MEX00000000111111110000000000000111111111111111         MEX Mexico           North America";
//
//				File file = new File("DataStorage.bin");
//
//				// if file doesnt exists, then create it
//				if (!file.exists()) {
//					file.createNewFile();
//				}
//
//				
//				
//				FileWriter fw = new FileWriter(file.getAbsoluteFile());
//				BufferedWriter bw = new BufferedWriter(fw);
//				bw.write(content);
//				
//				
//				
//
//				int poo = 1958201;
////				InttoAscii(poo, bw);
////				bw.write("000000000000000000000000000");
////				poo = 0000; 
////				InttoAscii(poo, bw);
////				
//				poo = 0;
//				int x =0;
//				while(x<500){
//				bw.write((char)poo);
//				x = x+1;
//				}
//				x=0;
//				poo=49;
//				while(x<500){
//				bw.write((char)poo);
//				x = x+1;
//				}
//
//				bw.close();
//
//				System.out.println("Done");
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		
//		
//		
//		
//		
//		
//	
//	}
//	
//	
//	
//	public static void InttoAscii(int poo,BufferedWriter bw) {
//		String hexstr = Integer.toHexString(poo); 
//		System.out.println("Converts Int to Hex to Ascii");
//		System.out.print("Int= "+poo+" \nHex= "+ hexstr+"\n");
//
//		while(hexstr.length()<8){
//			hexstr = "0"+hexstr.substring(0, hexstr.length());
//		}
//
//
//
//		String Hexarry[] = new String[4];
//		int Decarry[] = new int[4];
//
//		for (int c=0; c<hexstr.length(); c=c+2) {
//		Hexarry[c/2] = hexstr.substring(c, c+2);	
//		Decarry[c/2] = hex2decimal(hexstr.substring(c, c+2));	
//		System.out.println("Hex= "+ Hexarry[c/2] +" Dec= "+Decarry[c/2] +" ASCII= "+(char)Decarry[c/2] );
//		try {
//			bw.write((char)Decarry[c/2]);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		} 
//	}	
//	
//	
//	public static int hex2decimal(String s) {
//	    String digits = "0123456789ABCDEF";
//	    s = s.toUpperCase();
//	    int val = 0;
//	    for (int i = 0; i < s.length(); i++) {
//	        char c = s.charAt(i);
//	        int d = digits.indexOf(c);
//	        val = 16*val + d;
//	    }
//	    return val;
//	}	
//	
//	
//	
//	
//	
//}
//













//		RawData input = new RawData();
//		DataStorage dataStorage = new DataStorage();
//		UIoutput output = new UIoutput(); //Open in truncate mode
//		
//		output.displayThis("-->> SETUP started");
//		output.displayThis("-->> OPENED RawData file");
//		output.displayThis("-->> OPENED Backup file");
//		output.displayThis("-->> OPENED Log file\n");
//		
//		while(input.input1Country()) //loop through the end of the file
//		{
//			dataStorage.insert(input.getCountryCode(), input.getRestOfData());
//			//store to interior storage, which is an array
//		}
//		
//		output.displayThis("-->> CLOSED Log file");
//		output.displayThis("-->> CLOSED Backup file");
//		output.displayThis("-->> CLOSED RawData file");
//		output.displayThis("-->> SETUP finished" + " - inserted " + input.getN() + " countries into DataStorage\n");
//		
//		output.finishUp();
//		dataStorage.finishUp();
//		input.finishUp();
//		System.out.printf("done");
//	} 

