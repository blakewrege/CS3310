package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Countries Of The World App 1.0
 * Creates table for managing contries information
 * @author Caleb Viola
 */
public class Main {
	
	/**
	 * (Main) Instantiates TheLog and CountryDataTable objects
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		CountryDataTable cdt = new CountryDataTable();
		TheLog tl = new TheLog();
		
		Setup.Table("Sample",cdt,tl);
		for (int i = 1; i <= 3; i++)
			UserApp.Table(i, cdt, tl);
		cdt.FinishUp(tl,true);
		
		Setup.Table("All",cdt,tl);
		for (int i = 1; i <= 4; i++)
			UserApp.Table(i, cdt, tl);
		cdt.FinishUp(tl,false);

		tl.FinishUp();
	}
}
