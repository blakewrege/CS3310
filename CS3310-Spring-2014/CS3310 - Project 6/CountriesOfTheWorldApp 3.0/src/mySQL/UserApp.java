package mySQL;


public class UserApp {
	
	/****************************************************
	 * Build appropiate sqlString for SELECT command.
	 * @param sqlString
	 * @return
	 */
	public static String selectHandler(String sqlString){
		return sqlString;
	}
	
	/****************************************************
	 * Build appropiate sqlString for INSERT command.
	 * @param sqlString
	 * @return
	 */
	public static String insertHandler(String sqlString){
		String[] temp;
		sqlString = sqlString.substring(2,sqlString.length());
		temp = sqlString.split("\\|");
		
		if (temp.length > 2)
			return String.format("INSERT INTO %s(%s) VALUES (%s);", temp[0], temp[1], temp[2]);
		
		return String.format("INSERT INTO %s VALUES (%s);", temp[0], temp[1]);
	}
	
	/****************************************************
	 * Build appropiate sqlString for DELETE command.
	 * @param sqlString
	 * @return
	 */
	public static String deleteHandler(String sqlString){
		String[] temp;
		sqlString = sqlString.substring(2,sqlString.length());
		temp = sqlString.split("\\|");
		
		return String.format("DELETE FROM %s WHERE %s = %s;", temp[0], temp[1], temp[2]);
	}
	
	/****************************************************
	 * Build appropiate sqlString for UPDATE command.
	 * @param sqlString
	 * @return
	 */
	public static String updateHandler(String sqlString){
		return sqlString;
	}
}
