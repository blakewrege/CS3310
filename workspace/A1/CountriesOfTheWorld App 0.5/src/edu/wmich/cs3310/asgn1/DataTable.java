package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.util.ArrayList;

/********************************
 * Countries Of The World App 0.5
 * @author Caleb Viola
 */
public class DataTable {
	private ArrayList<DataTableRecord> dataTable;

	/******************************************
	 * Initialize array and set index 0 as null
	 */
	public DataTable(){
		dataTable = new ArrayList<DataTableRecord>();
		dataTable.add(null);
	}	
	

	/*********************************************************************
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
	public void insert(String code, int id, String name, String continent,
			int area, int population, float lifeExpectancy, boolean userApp,
			Log log) throws IOException {
		boolean insert = true;
		try{
			if(Character.isLetter(dataTable.get(id).code.charAt(0)))
				insert = false;
		}catch (Exception e){
			
		}
		
		if (insert){
			DataTableRecord record = new DataTableRecord();
			record.code = code;
			record.id = id;
			record.name = name;
			record.continent = continent;
			record.area = area;
			record.population = population;
			record.lifeExpectancy = lifeExpectancy;
			
			int diff = id - dataTable.size();
			
			if (diff >= 1){
				for (int i = 0; i < diff; i++)
					dataTable.add(null);
				dataTable.add(record);
			}
			else if (diff == 0)
				dataTable.add(record);
			else
				dataTable.set(id, record);	
			
			if (userApp)
				log.displayThis("   OK, country inserted (in data storage & name index)");
		}else{
			log.displayThis("   ERROR, existent country with that id");
		}
	}
	
	/******************************************************************
	 * Loads directly to table
	 * @param code
	 * @param id
	 * @param name
	 * @param continent
	 * @param area
	 * @param population
	 * @param lifeExpectancy
	 * @throws IOException
	 */
	public void load(String code, int id, String name, String continent,
			int area, int population, float lifeExpectancy) throws IOException {
		DataTableRecord record = new DataTableRecord();
		record.code = code;
		record.id = id;
		record.name = name;
		record.continent = continent;
		record.area = area;
		record.population = population;
		record.lifeExpectancy = lifeExpectancy;
		dataTable.add(record);	
	}
	
	/**********************
	 * Places null un table
	 */
	public void loadNull(){
		dataTable.add(null);
	}
	
	/**********************************************************
	 * DI command
	 * @param id
	 * @param log
	 * @throws IOException
	 */
	public void deleteById(int id, Log log) throws IOException{
		log.displayThis("   [SORRY, Delete By Id module not yet working]");
	}

	/**********************************************************
	 * SI command
	 * @param id
	 * @throws IOException
	 */
	public void selectById(int id, Log log) throws IOException{
		if ((id > 0) && (id < dataTable.size()) && (dataTable.get(id) != null)){
			log.displayThis("  "+log.country(dataTable.get(id)));
		}else{
			log.displayThis("   ERROR, invalid country id");
		}
	}
	
	/*********************************************
	 * AI command which displays table in id order
	 * @param log
	 * @throws IOException
	 */
	public void allById(Log log) throws IOException{
		log.displayThis("  "+log.header());
		for (short x = 0; x < dataTable.size(); x++)
			if (dataTable.get(x) != null)
				log.displayThis("  "+log.country(dataTable.get(x)));
			else if (x != 0)
				log.displayThis("  "+log.empty(x));
		log.displayThis("  "+log.footer());
	}
	
	/*********************************
	 * Dumps dataTable into Backup.txt
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		Backup backup = new Backup(false);
		backup.displayThis((dataTable.size()-1));
		for (short x = 0; x < dataTable.size(); x++)
			if (dataTable.get(x) != null)
				backup.displayThis(backup.dataFormat(dataTable.get(x)));
			else if (x != 0)
				backup.displayThis(backup.empty());
		backup.finishUp();			
	}
}
