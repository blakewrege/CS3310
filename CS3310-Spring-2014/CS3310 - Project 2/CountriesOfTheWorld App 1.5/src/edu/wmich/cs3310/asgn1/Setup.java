/**
 * 
 */
package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Class containing method to setup CDT from the RawData files.
 * @author Caleb Viola
 */
public class Setup {
	
	/**
	 * Method intented to setup CDT from the RawData files.
	 * @throws IOException 
	 */
	public static void Table(String fileNameSufix) throws IOException {
		TheLog tl = new TheLog(false);
		tl.displayThis("CODE STATUS > Setup started.");
		CountryDataTable cdt = new CountryDataTable(tl, "CountryData.bin");

		RawData rd = new RawData(fileNameSufix, tl);
		int temp = 0;

		while (!rd.doneWithInput) {
			rd.GrabCountry();
			if (!rd.doneWithInput)
				cdt.Insert1Country(rd.GetID(), rd.GetCode(), rd.GetName(),
						rd.GetContinent(), rd.GetArea(), rd.GetPopulation(),
						rd.GetLifeExpectancy(), tl, false);
			temp++;
		}
		rd.FinishUp(tl);
		cdt.FinishUp(tl, true);
		tl.displayThis("CODE STATUS > Setup finished - " + temp
				+ " countries processed.");
		tl.FinishUp();
	}
}
