package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**********************************************
 * Interact with DataTable from TransData files
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class UserApp {
	
	/*************************************************************
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void main(int fileNameSufix) throws IOException{
		Log log = new Log(true);
		log.displayThis("CODE STATUS > UserApp started");
		UI ui = new UI(fileNameSufix, log);
		DataTable dt = new DataTable();
		NameIndex ni = new NameIndex();
		load(dt, ni);
		
		int N = 0;
		
		ui.grabCommand();
		while (!ui.doneWithTrans){
			switch (ui.getTransCode()) {
			case "IN":
				log.displayThis("IN " + ui.getCode() + "," + ui.getName() + ","
						+ ui.getArea() + "," + ui.getPopulation() + ","
						+ ui.getLifeExpectancy());
				dt.insert(ui.getCode(), ui.getId(), ui.getName(),
						ui.getContinent(), ui.getArea(), ui.getPopulation(),
						ui.getLifeExpectancy(), true, log);
				ni.insert(ui.getName(), ui.getId());
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
				dt.selectById(ui.getId(), log);
				break;
			case "AN":
				log.displayThis("AN");
				ni.allByName(dt, log);
				break;
			case "AI":
				log.displayThis("AI");
				dt.allById(log);
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

	/************************************************************************
	 * Load DataTable and NameIdex from Backup.txt
	 * @param dt
	 * @param ni
	 * @throws IOException
	 */
	private static void load(DataTable dt, NameIndex ni) throws IOException {
		File backup = new File("Backup.txt");
		Scanner input = new Scanner(backup);
		String temp;
		String line[];
		int N = Integer.parseInt(input.nextLine());
		for (short x = 1; x <= N; x++){
			temp = input.nextLine();
			if (!temp.equals("empty")){
				line = temp.replace(",", "").split("_");
				dt.load(line[0], Integer.parseInt(line[1]), line[2], line[3],
						Integer.parseInt(line[4]), Integer.parseInt(line[5]),
						Float.parseFloat(line[6]));
			}else
				dt.loadNull();
		}
		while (input.hasNextLine()){
			line = input.nextLine().split("_");
			ni.load(line[0], Integer.parseInt(line[1]));
		}
		input.close();
	}
}
