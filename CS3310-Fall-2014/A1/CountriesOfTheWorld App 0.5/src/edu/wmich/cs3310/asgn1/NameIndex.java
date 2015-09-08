package edu.wmich.cs3310.asgn1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

/********************************
 * Countries Of The World App 0.5
 * @author Cuzox
 */
public class NameIndex {
	private ArrayList<String> keyValue;
	private ArrayList<Integer> drp;
	private int N, index;
	
	public NameIndex(){
		keyValue = new ArrayList<String>();
		drp = new ArrayList<Integer>();
		N = 0;
	}
	
	/*********************************************
	 * Insertion sort to index
	 * @param keyValue
	 * @param drp
	 */
	public void insert (String keyValue, int drp){
		if (N > 0){
			index = this.keyValue.size()-1;
			boolean allocated = false;
			boolean exist;
			while (!allocated){
				exist = (drp == this.drp.get(index)) ? true : false;
				if (keyValue.compareToIgnoreCase(this.keyValue.get(index)) > 0 && !exist){
					if (index == this.keyValue.size()-1){
						this.keyValue.add(keyValue);
						this.drp.add(drp);
						allocated = true;
						N++;
					}else{
						this.keyValue.add(index+1, keyValue);
						this.drp.add(index+1, drp);
						allocated = true;
						N++;
					}
				}else if (keyValue.compareToIgnoreCase(this.keyValue.get(index)) < 0 && !exist){
					if (index == 0){
						this.keyValue.add(index, keyValue);
						this.drp.add(index, drp);
						allocated = true;
						N++;
					}else
						index--;
				}else
					allocated = true;
			}
		}else{
			this.keyValue.add(keyValue);
			this.drp.add(drp);
			N++;
		}
	}
	
	/*******************************************
	 * Loads directly to index
	 * @param keyValue
	 * @param drp
	 */
	public void load (String keyValue, int drp){
		this.keyValue.add(keyValue);
		this.drp.add(drp);
		N++;
	}
	
	/********************************************************************************
	 * SN command with binary search
	 * @param dt
	 * @param name
	 * @param log
	 * @throws IOException
	 */
	public void selectByName (DataTable dt, String name, Log log) throws IOException{
		int lowerBound = 0;
		int upperBound = keyValue.size();
		int index = ((upperBound-lowerBound)/2)+lowerBound;
		boolean found = false;
		boolean exists = true;
		while (!found && exists){
			if (name.compareToIgnoreCase(keyValue.get(index)) < 0){
				if(upperBound-lowerBound == 1){
					exists = false;
				}else{
					upperBound = index;
					index = ((upperBound-lowerBound)/2)+lowerBound;
				}
			}else if (name.compareToIgnoreCase(keyValue.get(index)) > 0){
				if(upperBound-lowerBound == 1){
					exists = false;
				}else{
					lowerBound = index;
					index = ((upperBound-lowerBound)/2)+lowerBound;
				}
			}else{
				dt.selectById(drp.get(index), log);
				found = true;
			}
		}		
		
		if (!found)
			log.displayThis("   ERROR, invalid country name");
	}
	
	/********************************************************************************
	 * DN command
	 * @param dr
	 * @param name
	 * @param log
	 * @throws IOException
	 */
	public void deleteByName (DataTable dt, String name, Log log) throws IOException{
		log.displayThis("   [SORRY, Delete By Name module not yet working]");
	}

	/****************************************************************
	 * AN command
	 * @param dt
	 * @param log
	 * @throws IOException
	 */
	public void allByName(DataTable dt, Log log) throws IOException {
		log.displayThis("  "+log.header());
		for (Integer id : drp)
			dt.selectById(id, log);
		log.displayThis("  "+log.footer());	
	}
	
	/**********************
	 * Returns object count
	 * @return
	 */
	public int getN(){
		return N;
	}

	/*******************************
	 * Dumps nameIndex to Backup.txt
	 * @throws IOException
	 */
	public void finishUp() throws IOException{
		Backup backup = new Backup(true);
		for (short x = 0; x < keyValue.size(); x++)
			backup.displayThis(backup.nameFormat(keyValue.get(x), drp.get(x)));
		backup.finishUp();
	}
}
