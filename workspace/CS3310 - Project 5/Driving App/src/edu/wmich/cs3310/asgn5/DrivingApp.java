package edu.wmich.cs3310.asgn5;

import java.io.IOException;

public class DrivingApp {
	private static UI ui;
	private static Map map;
	private static ShortestPath shortestPath;
	
	/*****************************************************************
	 * 
	 * @param fileNameSufix
	 * @throws IOException
	 */
	public static void Setup(String fileNameSufix) throws IOException{
		map = new Map(fileNameSufix);
		ui = new UI(fileNameSufix);
		shortestPath = new ShortestPath(map,ui);
	}
	
	/************************************************
	 * 
	 * @throws IOException
	 */
	public static void finishUp() throws IOException{
		map.finishUp();
		ui.finishUp();
		shortestPath.finishUp();
	}
}
