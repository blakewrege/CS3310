package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Class containing method to manage table modifinations from the TransData files.
 * @author Caleb Viola
 */
public class UserApp {

	/**
	 * Method intented to manage table modifinations from the TransData files.
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void Table(int fileNameSufix)throws IOException {
		TheLog tl = new TheLog(true);
		tl.displayThis("CODE STATUS > UserApp started.");
		CountryDataTable cdt = new CountryDataTable(tl, "CountryData.bin");

		TransData td = new TransData(fileNameSufix, tl);
		int temp = 0;

		while (!td.doneWithFile) {
			td.GrabCommand(tl);
			if (!td.doneWithFile) {
				switch (td.TransCode()) {
				case "SI":
					if (td.GetID() == -1) {
						tl.displayThis("SI ERROR");
						tl.displayThis(tl.Sorry3());
						temp++;
						break;
					}
					tl.displayThis("SI " + td.GetID());
					cdt.SelectByID(td.GetID(), tl);
					temp++;
					break;
				case "SA":
					tl.displayThis("SA");
					cdt.SelectAll(tl);
					temp++;
					break;
				case "IN":
					tl.displayThis("IN " + td.GetID() + "," + td.GetCode()
							+ "," + td.GetName() + "," + td.GetArea() + ","
							+ td.GetPopulation() + "," + td.GetLifeExpectancy());
					cdt.Insert1Country(td.GetID(), td.GetCode(), td.GetName(),
							td.GetContinent(), td.GetArea(),
							td.GetPopulation(), td.GetLifeExpectancy(), tl,
							true);
					temp++;
					break;
				case "DI":
					if (td.GetID() == -1) {
						tl.displayThis("DI ERROR");
						tl.displayThis(tl.Sorry3());
						temp++;
						break;
					}
					tl.displayThis("DI " + td.GetID());
					cdt.DeleteByID(td.GetID(), tl);
					temp++;
					break;
				default:
					tl.displayThis(td.TransCode() + "\n"
							+ "   ERROR, invalid command.");
					temp++;
				}
			}
		}
		td.FinishUp(tl);
		cdt.FinishUp(tl, true);
		tl.displayThis("CODE STATUS > UserApp finished - " + temp
				+ " transactions processed.");
		tl.FinishUp();
	}
}
