package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Class containing method to manage table modifinations from the TransData
 * files.
 * @author Caleb Viola
 */
public class UserApp {

	/**************************************************************
	 * Method intented to manage table modifinations from the TransData files.
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void Table(int fileNameSufix)throws IOException {
		TheLog tL = new TheLog(true);
		tL.displayThis("CODE STATUS > UserApp started.");
		TransData tD = new TransData(fileNameSufix, tL);
		CountryData cD = new CountryData(tL, "CountryData.bin");
		CountryIndex cI= new CountryIndex(tL);
		cI.restoreIndex(tL);
		int rrn, temp = 0;

		while (!tD.doneWithFile) {
			tD.grabCommand(tL);
			if (!tD.doneWithFile) {
				switch (tD.transCode()) {
				case "SI":
					if (tD.getID() == -1) {
						tL.displayThis("SI ERROR");
						tL.displayThis(tL.sorry3());
						temp++;
						break;
					}
					tL.displayThis("SI " + tD.getID());
					cD.selectByID(tD.getID(), tL, false);
					temp++;
					break;
				case "SC":
					tL.displayThis("SC " + tD.getCode());
					cD.selectByID(cI.selectByCode(tD.getCode(), tL), tL, true);
					tL.displayThis(String.format("   %s index nodes visited",
							cI.getNodRead()));
					temp++;
					break;
				case "DI":
					if (tD.getID() == -1) {
						tL.displayThis("DI ERROR");
						tL.displayThis(tL.sorry3());
						temp++;
						break;
					}
					tL.displayThis("DI " + tD.getID());
					cD.deleteByID(tD.getID(), tL);
					temp++;
					break;
				case "DC":
					if (tD.getID() == -1) {
						tL.displayThis("DC ERROR");
						tL.displayThis(tL.sorry3());
						temp++;
						break;
					}
					tL.displayThis("DC " + tD.getCode());
				    cI.deleteByCode(tD.getCode(), tL);
					temp++;
					break;
				case "IN":
					tL.displayThis("IN " + tD.getID() + "," + tD.getCode()
							+ "," + tD.getName() + "," + tD.getArea() + ","
							+ tD.getPopulation() + "," + tD.getLifeExpectancy());
					rrn = cD.insertCountry(tD.getID(), tD.getCode(),
							tD.getName(), tD.getContinent(), tD.getArea(),
							tD.getPopulation(), tD.getLifeExpectancy(), tL,
							true);
					cI.insertCodeInIndex(tD.getCode(), rrn, tL, true);
					temp++;
					break;
				default:
					tL.displayThis(tD.transCode() + "\n"
							+ "   ERROR, invalid command.");
					temp++;
				}
			}
		}
		tD.finishUp(tL);
		cD.finishUp(tL, true);
		cI.finishUp(tL, true);
		tL.displayThis("CODE STATUS > UserApp finished - " + temp
				+ " transactions processed.");
		tL.finishUp();
	}
}
