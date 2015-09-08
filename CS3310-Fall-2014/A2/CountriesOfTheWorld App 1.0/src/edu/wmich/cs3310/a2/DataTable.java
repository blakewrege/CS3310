package edu.wmich.cs3310.a2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/********************************
 * Countries Of The World App 1.0
 * @author Caleb Viola
 */
public class DataTable {
	private short n;
	private short maxId;
	private boolean[] status;
	private RandomAccessFile file;
	private DataTableRecord dtr;

	/*************************************************************
	 * Constructor
	 * @param fileName
	 * @param log
	 * @throws IOException
	 */
	public DataTable(String fileName, Log log) throws IOException{
		file = new RandomAccessFile(fileName, "rw");
		file.seek(0);
		try {
			String[] line = file.readLine().split("'");
			n = Short.parseShort(line[0]);
			maxId = Short.parseShort(line[1]);
		} catch (Exception e) {
			n = 0;
			maxId = 1;
			file.writeBytes(String.format("%03d'%03d\n", n, maxId));
		}
		log.displayThis("FILE STATUS > CountryData FILE opened");
		dtr = new DataTableRecord();
	}	
	

	/************************************************************
	 * IN command
	 * @param code
	 * @param id
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @param userApp
	 * @param log
	 * @throws IOException
	 */
	public void insert1Country(String code, short id, String name,
			String continent, int area, long population, float lifeExpectancy,
			Log log) throws IOException { 
		dtr.byteOffset(id);
		status = dtr.locateWithStatus(file);
		if ((status[0] && status[1]) || (!status[0] && status[1])){
			if (id > maxId){
				/* fill space in between with pre-formatted string
				 * for better management and readability
				 * */
				if (id-maxId != 1){
					dtr.byteOffset(maxId+1);
					status = dtr.locateWithStatus(file);
					for (int x = maxId+1; x < id; x++)
						dtr.fill1record(file);
				}
				maxId = id;
			}
			dtr.write1Country(file, code, id, name, continent, area,
					population, lifeExpectancy);
			n++;
			
		}else if (status[0] && !status[1])
			log.displayThis("   SORRY, another country has that id");
		else
			log.displayThis("   SORRY, invalid id");	
	}

	/************************************************************
	 * DI command
	 * @param id
	 * @param log
	 * @throws IOException
	 */
	public void deleteById(short id, Log log) throws IOException{
		log.displayThis("   [SORRY, Delete By Id module not yet working]");
	}

	/*******************************************
	 * Execution for SI command which locates an
	 * element in a binary file using id
	 * @param id
	 * @param visited
	 * @param log
	 * @param nameIndex
	 * @throws IOException
	 */
	public void selectById(short id, short visited, Log log, boolean nameIndex) throws IOException{
		dtr.byteOffset(id);
		status = dtr.locateWithStatus(file);
		if(id == 0)
			log.displayThis("   SORRY, no country with that id");
		else if (status[0] && !status[1]){
			dtr.read1Country(file);
			log.displayThis("  "
					+ log.country(dtr.getCode(), dtr.getId(), dtr.getName(),
							dtr.getContinent(), dtr.getArea(),
							dtr.getPopulation(), dtr.getLifeExpectancy()));
			if(nameIndex)
				log.displayThis(String.format("      >> %d %s visited",
						visited, (visited == 1) ? "node" : "nodes"));
		}else if (!status[0] && !status[1])
			log.displayThis("   SORRY, invalid id");
		else
			log.displayThis("   SORRY, no country with that id");
	}
	
	/*********************************************
	 * AI command which displays table in id order
	 * @param log
	 * @throws IOException
	 */
	public void selectAllById(Log log) throws IOException{
		log.displayThis("  "+log.header());
		short i = 1;
		dtr.byteOffset(i);
		status = dtr.locateWithStatus(file);
		while (!(status[0] && status[1])){
			dtr.read1Country(file);
			if(dtr.getId() != 0)
				log.displayThis("  "
						+ log.country(dtr.getCode(), dtr.getId(),
								dtr.getName(), dtr.getContinent(),
								dtr.getArea(), dtr.getPopulation(),
								dtr.getLifeExpectancy()));
			dtr.byteOffset(++i);
			status = dtr.locateWithStatus(file);
		}
		log.displayThis("  "+log.footer());
	}
	
	/*********************************
	 * Dumps dataTable into Backup.txt
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		file.seek(0);
		file.writeBytes(String.format("%03d'%03d\n", n, maxId));
		file.close();
	}
}
