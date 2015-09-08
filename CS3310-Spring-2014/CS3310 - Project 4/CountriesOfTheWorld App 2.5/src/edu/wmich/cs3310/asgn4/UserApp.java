package edu.wmich.cs3310.asgn4;

import java.io.IOException;

/*********************
 * Class containing method to manage table modifications from the TransData
 * files.
 * @author Caleb Viola
 */
public class UserApp {

	/*************************************************************************
	 * Method intended to manage table modifications from the TransData files.
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void userAppMain(int fileNameSufix)throws IOException {
		TheLog tL = new TheLog();
		tL.printThis("CODE STATUS > UserApp started.");
		
		TransData tD = new TransData(fileNameSufix, tL);
		CountryData cD = new CountryData(fileNameSufix, tL);
		CountryIndex cI= new CountryIndex(fileNameSufix, tL);
		int trans = 0;

		while (!tD.doneWithFile) {
			tD.grabCommand(tL);
			if (!tD.doneWithFile) {
				switch (tD.getCommand()) {
					case "SC":
						tL.printThis("SC " + tD.getCode());
						cD.selectByDRP(cI.selectByCode(tD.getCode(), tL), tL);
						tL.nodesRead(cI.getNodesRead());
						trans++;
						break;
					default:
						tL.printThis(tD.getCommand() + "\n"
								+ "   ERROR, invalid command.");
						trans++;
				}
			}
		}
		tD.finishUp(tL);
		cD.finishUp(tL, true);
		cI.finishUp(tL, true);
		tL.printThis("CODE STATUS > UserApp finished - " + trans
				+ " transactions processed.");
		tL.finishUp();
	}
}
