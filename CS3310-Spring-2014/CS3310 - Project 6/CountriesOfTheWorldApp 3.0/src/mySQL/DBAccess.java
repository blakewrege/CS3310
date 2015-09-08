package mySQL;

import java.io.IOException;
import java.sql.*;

public class DBAccess {
	private static String command;
	
	/********************************************************************************************
	 * 
	 * @param conn
	 * @param ui
	 * @param sqlString
	 * @throws IOException
	 */
	public static void retrieveData(Connection conn, UI ui, String sqlString) throws IOException{
		getCommand(sqlString);
		if (command.equals("INSERT") || command.equals("DELETE"))
			ui.println(String.format("SQL: %s", sqlString));
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlString);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			
			for (short x = 1; x <= rsMetaData.getColumnCount(); x++) {
                System.out.print(rsMetaData.getColumnLabel(x)+"-");
            }
			System.out.println();
				
			while(rs.next()){
				for (short j = 1; j <= rsMetaData.getColumnCount(); j++){
					System.out.print(rs.getString(j)+"-");
				}
				System.out.println();
			}
			System.out.println();
			stmt.close();
		} catch (SQLException e) {
			ui.println("ERROR on "+ command + ", not done.");
		}
	}
	
	/******************************************************************************************
	 * 
	 * @param conn
	 * @param ui
	 * @param sqlString
	 * @throws IOException
	 */
	public static void changeData(Connection conn, UI ui, String sqlString) throws IOException{
		getCommand(sqlString);
		if (!command.equals("UPDATE"))
			ui.println(String.format("SQL: %s", sqlString));
		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sqlString) != 0)
				ui.println("OK, " + command + " done.");
			else
				ui.println("No rows affected.");
			stmt.close();
		} catch (SQLException e) {
			ui.println("ERROR on  " + command + ", not done.");
		}
	}
	
	/************************************************
	 * 
	 * @param sqlString
	 */
	private static void getCommand(String sqlString){
		command = sqlString.substring(0,6);
	}
}
