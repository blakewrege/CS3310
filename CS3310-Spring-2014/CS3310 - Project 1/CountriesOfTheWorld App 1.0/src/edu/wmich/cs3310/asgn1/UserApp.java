/**
 * 
 */
package edu.wmich.cs3310.asgn1;

import java.io.IOException;

/**
 * Class containing method to setup CDT from the TransData files
 * @author Caleb Viola
 */
public class UserApp {
	
	/**
	 * Method intented to setup CDT from the TransData files
	 * @throws IOException 
	 */
	public static void Table(int fileNameSufix,CountryDataTable cdt,TheLog tl) 
			throws IOException{
		tl.displayThis("CODE STATUS > UserApp started");
		TransData td = new TransData(fileNameSufix,tl);
		int temp = 0;
		
		while (!td.doneWithFile){
			td.GrabCommand(tl);
			if (!td.doneWithFile){
				if (td.TransCode().equals("SN")){
					tl.displayThis("SN "+td.GetName());
					cdt.SelectByName(td.GetName().toLowerCase(), tl);
				}
				else if (td.TransCode().equals("SA")){
					tl.displayThis("SA");
					cdt.SelectAll(tl);
				}
				else if (td.TransCode().equals("IN")){
					tl.displayThis("IN "+td.GetCode()+","+td.GetName()+","+td.GetArea()+","+
									td.GetPopulation()+","+td.GetLifeExpectancy());
					cdt.Insert(td.GetName(),td.GetContinent(),td.GetCode(),td.GetArea(),
							td.GetPopulation(),td.GetLifeExpectancy(),tl, true);
				}
				else if (td.TransCode().equals("DN")){
					tl.displayThis("DN "+td.GetName());
					cdt.Delete(td.GetName().toLowerCase(), tl);
				}
				else{
					tl.displayThis(td.TransCode()+"\n" +
							"   ERROR, invalid command");
				}
			}
		}
		temp = td.GetTransactions();
		td.FinishUp(tl);
		tl.displayThis("CODE STATUS > UserApp finished - "+temp+" transactions processed");
	}
}
