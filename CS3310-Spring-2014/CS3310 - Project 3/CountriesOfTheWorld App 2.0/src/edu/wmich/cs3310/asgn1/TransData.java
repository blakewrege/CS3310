package edu.wmich.cs3310.asgn1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intended to obtain information for UserApp in Main from the TransData files
 * in order to create table. 
 * @author Caleb Viola
 */
public class TransData {
	private Scanner input;
	private short id;
	private String code;
	private String name;
	private String continent;
	private String command;
	private int area;
	private long population;
	private float lifeExpectancy;
	boolean doneWithFile = false;

	/******************************************************************
	 * Constructor to open a TransData file and create log entry.
	 * @param fileNameSufix number of TransData file to read.
	 * @param tl TheLog object 
	 * @throws IOException
	 */
	public TransData(int fileNameSufix, TheLog tl) throws IOException {
		File file = new File("TransData" + fileNameSufix + ".txt");
		tl.displayThis("FILE STATUS > TransData FILE opened.");
		input = new Scanner(file);
	}

	/******************************************************
	 * Obtain command from a line in the TransData file.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void grabCommand(TheLog tl) throws IOException {
		if (input.hasNextLine()) {
			String temp = input.nextLine();
			command = temp.substring(0, 2);

			if (command.equals("IN")) {
				temp = temp.substring(33, temp.length() - 2).replace("'", "");
				String[] fields = temp.split(",");

				id = Short.parseShort(fields[0]);
				code = fields[1];
				name = fields[2];
				continent = fields[3];
				area = Integer.parseInt(fields[5]);
				population = Integer.parseInt(fields[7]);
				lifeExpectancy = Float.parseFloat(fields[8]);
			} else if (command.equals("SI") || command.equals("DI")) {
				if (temp.length() > 2) {
					try {
						id = Short.parseShort(temp.substring(3, temp.length())
								.trim());
					} catch (NumberFormatException e) {
						id = -1;
					}
				} else
					id = -1;
			}else if (command.equals("SC") || command.equals("DC")) {
				if (temp.length() > 2) 
					code = temp.substring(3, temp.length()).trim();
			}
		} else
			doneWithFile = true;
	}

	/**
	 * Getter for acquired command.
	 * @return
	 */
	public String transCode() {
		return command;
	}

	/**
	 * Getter for id.
	 * @return
	 */
	public short getID() {
		return id;
	}

	/**
	 * Getter for code.
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Getter for name.
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for continent.
	 * @return
	 */
	public String getContinent() {
		return continent;
	}

	/**
	 * Getter for area.
	 * @return
	 */
	public int getArea() {
		return area;
	}

	/**
	 * Getter for population.
	 * @return
	 */
	public long getPopulation() {
		return population;
	}

	/**
	 * Getter for lifeExpectancy.
	 * @return
	 */
	public float getLifeExpectancy() {
		return lifeExpectancy;
	}

	/**
	 * Close file and creat log entry.
	 * @param tl TheLog object
	 * @throws IOException
	 */
	public void finishUp(TheLog tl) throws IOException {
		input.close();
		tl.displayThis("FILE STATUS > TransData FILE closed.");
	}
}
