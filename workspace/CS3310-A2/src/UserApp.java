//PROGRAM: UserApp
//AUTHOR: Blake Wrege (based off Jia Guo)
//DESCRIPTION: Setup (and the 3 classes it uses) creates DataStorage file
//*****************************************************************************************************


import java.io.*;

public class UserApp {

	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		String transCode;
		UIinput input = new UIinput();
		UIoutput output = new UIoutput("open in append mode");//The String is just for calling a specified constructor for UserApp
		DataStorage stor = new DataStorage("DataStorage.bin",output);
		output.displayThis("-->> USERAPP started");
		output.displayThis("-->> OPENED TransData file");
		output.displayThis("-->> OPENED DataStorage file");
		output.displayThis("-->> OPENED Log file\n");
		stor.setupApp();
		

		//TransCode is the 1st char in the record: I, D, S, A (for Insert, Delete, Select, showAll)
		//TransCode % means do nothing.
		while(input.input1User()) 		//Loop till EOF
		{
			transCode = input.getTransCode();
			switch (transCode)
			{
			case "I": stor.insert1Country(input.getCode(), input.getId(), input.getName(),
			input.getContinent(), input.getArea(), input.getPopulation(),
			input.getLifeExpectancy(),output);
				break;
			case "D": stor.delete(input.getId(),output);
				break;
			case "S": stor.select(input.getId(),output);
				break;
			case "A": stor.all(output);
				break;
			case "%": 
				break;
			default: System.out.println("Invalid transcode!");
				break;
			}
		}	
		output.displayThis("-->> CLOSED Log file");
		output.displayThis("-->> CLOSED DataStorage file");
		output.displayThis("-->> CLOSED TransData file");
		output.displayThis("-->> USERAPP finished" + " - processed " + input.getId() + " transactions\n");
		stor.finishUp();
		output.finishUp();
	}
}
