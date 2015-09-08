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
		TheLog tL = new TheLog(false);
		tL.displayThis("CODE STATUS > Setup started.");
		
		RawData rD = new RawData(fileNameSufix, tL);
		CountryData cD = new CountryData(tL, "CountryData.bin");
		CountryIndex cI= new CountryIndex(tL);
		int rrn, temp = 0;
		
		rD.grabCountry();
		while (!rD.doneWithInput) {
			rrn = cD.insertCountry(rD.getID(), rD.getCode(), rD.getName(),
					rD.getContinent(), rD.getArea(), rD.getPopulation(),
					rD.getLifeExpectancy(), tL, false);
			cI.insertCodeInIndex(rD.getCode(), rrn, tL, false);
			temp++;
			rD.grabCountry();
		}
		rD.finishUp(tL);
		cD.finishUp(tL, true);
		cI.finishUp(tL, true);
		tL.displayThis("CODE STATUS > Setup finished - " + temp
				+ " countries processed.");
		tL.finishUp();
	}
}
