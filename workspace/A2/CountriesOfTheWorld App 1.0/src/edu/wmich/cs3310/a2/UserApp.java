package edu.wmich.cs3310.a2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**********************************************
 * Interact with DataTable from TransData files
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class UserApp {
	
	/***************************************************************
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void main(short fileNameSufix) throws IOException{
		Log log = new Log(true);
		log.displayThis("CODE STATUS > UserApp started");
		UI ui = new UI(fileNameSufix, log);
		DataTable dt = new DataTable("CountryData.txt", log);
		NameIndex ni = new NameIndex();
		load(ni);
		
		int N = 0;
		
		ui.grabCommand();
		while (!ui.doneWithTrans){
			switch (ui.getTransCode()) {
			case "IN":
				log.displayThis("IN " + ui.getCode() + "," + ui.getId() + ","
						+ ui.getName() + "," + ui.getArea() + ","
						+ ui.getPopulation() + "," + ui.getLifeExpectancy());
				dt.insert1Country(ui.getCode(), ui.getId(), ui.getName(),
						ui.getContinent(), ui.getArea(), ui.getPopulation(),
						ui.getLifeExpectancy(), log);
				ni.insertIntoNameIndex(ui.getName(), ui.getId());
				log.displayThis("   OK, country inserted (in data storage & name index)");
				log.displayThis(String.format("      >> %d %s visited in the name index", 
						ni.getVisited(), (ni.getVisited() == 1) ? "node": "nodes"));
				break;
			case "DN":
				log.displayThis("DN " + ui.getName());
				ni.deleteByName(dt, ui.getName(), log);
				break;
			case "DI":
				log.displayThis("DI " + ui.getId());
				dt.deleteById(ui.getId(), log);
				break;
			case "SN":
				log.displayThis("SN " + ui.getName());
				ni.selectByName(dt, ui.getName(), log);
				break;
			case "SI":
				log.displayThis("SI " + ui.getOrigId());
				dt.selectById(ui.getId(), (short) 0, log, false);
				break;
			case "AN":
				log.displayThis("AN");
				ni.selectAllByName(dt, log);
				break;
			case "AI":
				log.displayThis("AI");
				dt.selectAllById(log);
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
		ni.finishUp();
		log.displayThis("CODE STATUS > UserApp finished - " + N
				+ " transactions processed");
		log.finishUp();
	}

	/*******************************
	 * Load NameIdex from Backup.txt
	 * @param ni
	 * @throws IOException
	 */
	private static void load(NameIndex ni) throws IOException {
		Scanner input = new Scanner(new File("Backup.txt"));
		String[] line = input.nextLine().split("'");
		short n = Short.parseShort(line[0]);
		short nextEmpty = Short.parseShort(line[1]);
		short rootPtr = Short.parseShort(line[2]);
		while (input.hasNextLine()){
			line = input.nextLine().split("'");
			ni.load(Short.parseShort(line[0]), line[1],
					Short.parseShort(line[2]), Short.parseShort(line[3]));
		}
		ni.loadHeader(n, nextEmpty, rootPtr);
		input.close();
	}
}
