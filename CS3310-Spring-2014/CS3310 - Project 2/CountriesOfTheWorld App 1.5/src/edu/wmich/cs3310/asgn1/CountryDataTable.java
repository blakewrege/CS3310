package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

/*******************************************
 * Manages countries by implementing a table 
 * stored in a binary file.
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class CountryDataTable {
	private short N;
	private boolean[] status;
	private RandomAccessFile file;
	private CountryDataRec cdr;

	/*******************************************************
	 * Creates binaryfile and creates CountryDataRec object.
	 * @param tl
	 * @param fileName
	 * @throws IOException
	 */
	public CountryDataTable(TheLog tl, String fileName) throws IOException{
		file = new RandomAccessFile(fileName, "rw");
		file.seek(0);
		try {
			N = file.readShort();
		} catch (IOException e) {
			N = 0;
		}
		tl.displayThis("FILE STATUS > CountryData FILE opened.");
		cdr = new CountryDataRec();
	}	
	
	/*******************************************
	 * Execution for SI command which locates an
	 * element in a binary file using id.
	 * @param Element id to locate
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void SelectByID(int id, TheLog tl) throws IOException{
		status = cdr.byteOffsetAndStatus(id, file);
		if(id == 0)
			tl.displayThis(tl.Sorry());
		else if (status[0] && !status[1]){
			cdr.Read1Country(file);
			tl.displayThis("   "
					+ tl.Country(cdr.getCode(), cdr.getName(),
							cdr.getContinent(), cdr.getArea(),
							cdr.getPopulation(), cdr.getLifeExpectancy()));
		}
		else if (!status[0] && !status[1])
			tl.displayThis(tl.Sorry3());
		else
			tl.displayThis(tl.Sorry());
	}
	
	/************************************************
	 * Execution for SA command which displays table.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void SelectAll(TheLog tl) throws IOException{
		tl.displayThis("   "+tl.Header1());
		short i = 1;
		status = cdr.byteOffsetAndStatus(i, file);
		while (!(status[0] && status[1])){
			cdr.Read1Country(file);
			if(cdr.getID() != 0)
				tl.displayThis("   "+tl.Country(cdr.getCode(),cdr.getName(), 
						cdr.getContinent(), cdr.getArea(), cdr.getPopulation(),
						cdr.getLifeExpectancy()));
			status = cdr.byteOffsetAndStatus(i++, file);
		}
		tl.displayThis("   "+tl.Footer());
	}

	/**********************************************************
	 * Execution for IN command which inserts country in table.
	 * @param id
	 * @param code
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @param tl TheLog object
	 * @param userApp boolean to determine if confirmation message should be logged
	 * @throws IOException
	 */
	public void Insert1Country(short id, String code, String name,
			String continent, int area, long population, float lifeExpectancy,
			TheLog tl, boolean userApp) throws IOException {
		status = cdr.byteOffsetAndStatus(id, file);
		if ((status[0] && status[1]) || (!status[0] && status[1])){
			cdr.Write1Country(file, id, code, name, continent, area,
					population, lifeExpectancy);
			N++;
			if (userApp)
				tl.displayThis(tl.Msg("inserted"));
		}
		else if (status[0] && !status[1])
			tl.displayThis(tl.Sorry2());
		else
			tl.displayThis(tl.Sorry3());
	}

	/****************************************************
	 * Deletes country in table by changing it's id to 0.
	 * @param id
	 * @param tl
	 * @throws IOException
	 */
	public void DeleteByID(int id, TheLog tl) throws IOException{
		status = cdr.byteOffsetAndStatus(id, file);
		if(id == 0)
			tl.displayThis(tl.Sorry());
		else if (status[0] && !status[1]){
			cdr.Delete1Country(file);
			
			tl.displayThis(tl.Msg("deleted"));
			N--;
		}
		else if (!status[0] && !status[1])
			tl.displayThis(tl.Sorry3());
		else
			tl.displayThis(tl.Sorry());
	}
	
	/******************************************
	 * Prints all contries and empty locations.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	private void Snapshot(TheLog tl) throws IOException{
		tl.displayThis("CODE STATUS > Snapshot started.");
		DecimalFormat noFormat = new DecimalFormat("#000");
		
		tl.displayThis("N: "+N);
		tl.displayThis(tl.Header2());
		short i = 1;
		status = cdr.byteOffsetAndStatus(i, file);
		while (!(status[0] && status[1])){
			cdr.Read1Country(file);
			if(cdr.getID() != 0)
				tl.displayThis(tl.Country2(i,cdr.getCode(),cdr.getName(), 
						cdr.getContinent(), cdr.getArea(), cdr.getPopulation(),
						cdr.getLifeExpectancy()));
			else
				tl.displayThis("["+noFormat.format(i)+"] "+"    EMPTY");
			i++;
			status = cdr.byteOffsetAndStatus(i, file);
		}
		tl.displayThis(tl.Footer()+tl.Footer2());
		
		tl.displayThis("CODE STATUS > Snapshot finished - "+N+" countries displayed.");
	}
	
	/*********************************************************
	 * Records N in table, calls Snapshot, closes binary file.
	 * @param tl TheLog object
	 * @param printTable
	 * @throws IOException
	 */
	public void FinishUp(TheLog tl, boolean printTable) throws IOException{
		file.seek(0);
		file.writeShort(N);
		if (printTable)
			Snapshot(tl);
		file.close();
		tl.displayThis("FILE STATUS > CountryData FILE closed.");
	}
}
