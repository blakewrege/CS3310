package edu.wmich.cs3310.a3;

import java.io.IOException;

/*************************************************************
 * Class containing method to setup CDT from the RawData files
 * Countries Of The World App 1.5
 * @author Caleb Viola
 */
public class Setup {
	
	/**************************************************
	 * Setup DataTable and NameIndex from RawData files
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void main (String fileNameSufix) throws IOException {
		Log log = new Log(true);
		log.displayThis("CODE STATUS > Setup started");
		RawData rd = new RawData(fileNameSufix, log);
		DataTable dt = new DataTable("CountryData.bin", log);
		int N = 0;
				
		rd.grabCountry();
		while (!rd.doneWithInput){
			if(rd.countryGrabbed){
				dt.insertCountry(rd.getCode(), rd.getId(), rd.getName(),
						rd.getContinent(), rd.getArea(), rd.getPopulation(),
						rd.getLifeExpectancy(), log);
			}
			rd.grabCountry();
		}
		
		N = rd.getTransactions();
		rd.finishUp(log);
		dt.finishUp();
		log.displayThis("CODE STATUS > Setup finished - "+N+" countries processed");
		log.finishUp();
	}
}
