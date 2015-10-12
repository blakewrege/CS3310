package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/*************************************************************
 * Class containing method to setup CDT from the RawData files
 * Countries Of The World App 0.5
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
		DataTable dt = new DataTable();
		NameIndex ni = new NameIndex();
		
		int N = 0;
				
		rd.grabCountry();
		while (!rd.doneWithInput){
			if(rd.countryGrabbed){
				dt.insert(rd.getCode(), rd.getId(), rd.getName(),
						rd.getContinent(), rd.getArea(), rd.getPopulation(),
						rd.getLifeExpectancy(), false, log);
				ni.insert(rd.getName(), rd.getId());
			}
			rd.grabCountry();
		}
		
		N = rd.getTransactions();
		rd.finishUp(log);
		dt.finishUp();
		ni.finishUp();
		log.displayThis("CODE STATUS > Setup finished - "+N+" countries processed");
		log.finishUp();
	}
}
