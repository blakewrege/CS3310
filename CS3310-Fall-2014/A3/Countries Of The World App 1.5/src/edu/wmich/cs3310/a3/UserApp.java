package edu.wmich.cs3310.a3;

import java.io.*;

/**********************************************
 * Interact with DataTable from TransData files
 * Countries Of The World App 1.5
 * @author Caleb Viola
 */
public class UserApp {
	
	/***************************************************************
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void main(int fileNameSufix) throws IOException{
		Log log = new Log(true);
		log.displayThis("CODE STATUS > UserApp started");
		UI ui = new UI(fileNameSufix, log);
		DataTable dt = new DataTable("CountryData.bin", log);
		
		int N = 0;
		
		ui.grabCommand();
		while (!ui.doneWithTrans){
			switch (ui.getTransCode()) {
			case "IN":
				log.displayThis("IN " + ui.getCode() + "," + ui.getId() + ","
						+ ui.getName() + "," + ui.getArea() + ","
						+ ui.getPopulation() + "," + ui.getLifeExpectancy());
				dt.insertCountry(ui.getCode(), ui.getId(), ui.getName(),
						ui.getContinent(), ui.getArea(), ui.getPopulation(),
						ui.getLifeExpectancy(), log);
				log.displayThis("   OK, country inserted");
				break;
			case "DC":
				log.displayThis("DC " + ui.getCode());
				dt.deleteByCode(ui.getCode(), log);
				break;
			case "SC":
				log.displayThis("SC " + ui.getCode());
				dt.selectByCode(ui.getCode(), log);
				break;
			default:
				log.displayThis(ui.getTransCode() + "\n"
						+ "   ERROR, invalid command");
				break;
			}
			ui.grabCommand();
		}
		N = ui.getTransactions();
		ui.finishUp(log);
		dt.finishUp();
		log.displayThis("CODE STATUS > UserApp finished - " + N
				+ " transactions processed");
		log.finishUp();
	}
}
