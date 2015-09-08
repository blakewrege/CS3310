/**
 * 
 */
package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Class containing method to setup CDT from the RawData files
 * @author Caleb Viola
 */
public class Setup {
	
	/**
	 * Method intented to setup CDT from the RawData files
	 * @throws IOException 
	 */
	public static void Table (String fileNameSufix,CountryDataTable cdt,TheLog tl) throws IOException {
		tl.displayThis("CODE STATUS > Setup started");
		RawData rd = new RawData(fileNameSufix,tl);
		int temp = 0;
				
		while (!rd.doneWithInput){
			rd.GrabCountry();
			if (!rd.doneWithInput)
				cdt.Insert(rd.GetName(),rd.GetContinent(),rd.GetCode(),
						rd.GetArea(),rd.GetPopulation(),rd.GetLifeExpectancy(),tl, false);
		}
		temp = rd.GetTransactions();
		rd.FinishUp(tl);

		tl.displayThis("CODE STATUS > Setup finished - "+temp+" countries processed");
	}
}
