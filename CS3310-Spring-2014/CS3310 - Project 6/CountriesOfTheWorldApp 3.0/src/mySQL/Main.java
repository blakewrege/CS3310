package mySQL;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String url = "jdbc:mysql://localhost:3306/world";
        String user = "kaminski";
        String password = "cs3310";
        
        UI ui = new UI("WorldTrans.txt");
        
        ui.println("Connecting to MySQL...");
        
        try {
			Connection conn = DriverManager.getConnection(url, user, password);
			ui.println("OK, the DB Connection is OPENED\r\n");
			
			String line;
			while(ui.hasNextLine()){
				line = ui.nextLine();
				switch (line.charAt(0)) {
				case 'S': 
					ui.println("SQL: " + line);
					line = UserApp.selectHandler(line);
					DBAccess.retrieveData(conn, ui, line);
					break;
				case 'I': 
					ui.println("Raw: " + line);
					line = UserApp.insertHandler(line);
					DBAccess.changeData(conn, ui, line);
					break;
				case 'D':
					ui.println("Raw: " + line);
					line = UserApp.deleteHandler(line);
					DBAccess.changeData(conn, ui, line);
					break;
				case 'U':
					ui.println("SQL: " + line);
					line = UserApp.updateHandler(line);
					DBAccess.changeData(conn, ui, line);
					break;
				}
				ui.println("");
			}
			
			conn.close();
		} catch (SQLException e) {
			ui.println("ERROR, DB Connection didn't work - no trans done");
		}
        
        ui.println("EXITING PROGRAM");
        ui.finishUp();
	}

}
