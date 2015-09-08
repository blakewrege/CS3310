package lab7;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class reads the files for accounts, withdraw, and deposit.
 * @author sam, blake
 *
 */

public class FileInput {

	/**
	 * This method reads the file with the account information.
	 * @param fileName The file name with the account information.
	 * @return Returns Account object array.
	 * @throws IOException
	 */
	public Account[] loadAccounts(String fileName) throws IOException {
		int count = 0; // counter for first loop
		int countArray = 0; // counter for second loop
		String line; // string for nextLine
		Account account; // account object
	    Account[] accountArray; // account object array
		FileReader fileReader = new FileReader(fileName); // initialize FileReader for fileName
	    Scanner scan = new Scanner(fileReader); 
		    
	    // this loop finds out how many lines are in file and sets them to count
	    while (scan.hasNextLine()) {
	    	scan.nextLine();
	    	count++;
	    }
	    accountArray = new Account[count]; // initialize account object array with lines in file
	    
	    fileReader.close(); // reset scanner and filereader
	    fileReader = new FileReader(fileName);
	    scan.close();
	    scan = new Scanner(fileReader);
	    
	    // this loop scans nextLine for each line and sends it to account class and puts account object in account array
	    while (scan.hasNextLine()) {
	    	line = scan.nextLine();
	    	account = new Account(line); // account object accepts string
	    	accountArray[countArray] = account; // puts account object in account array
	    	countArray++;
	    }
	    scan.close(); // close scanners
	    return accountArray;
	}
	
	/**
	 * This method reads the withdraw file information.
	 * @param fileName File name for withdraw file.
	 * @return TransactionList of data type withdraw.
	 * @throws IOException
	 */
	public TransactionList<Withdraw> loadWithdrawTransactions(String fileName) throws IOException {
		String line; // string for each line in file
		String[] stringArray; // array to accept strings when splitting
		int accountNumber;
		double amount;
		TransactionList<Withdraw> list = new TransactionList<Withdraw>(); // initialize transactionlist of type withdraw
		
		FileReader fileReader = new FileReader(fileName); // initialize filereader
	    Scanner scan = new Scanner(fileReader); 
	    
	    // this loop reads the nextLine while there is one, splits it where there is whitespace, and parses the splits to accountNumber and amount
	    while (scan.hasNextLine()) {
	    	line = scan.nextLine();
	    	stringArray = line.split(" "); // split line by whitespace
	    	accountNumber = Integer.parseInt(stringArray[0]);
	    	amount = Double.parseDouble(stringArray[1]);
	    	Withdraw withdraw = new Withdraw(accountNumber, amount); // initialize withdraw object
	    	list.add(withdraw);
	    }
	    scan.close(); // close scanner
		return list;
	}
	
	/**
	 * This method reads the deposit file information.
	 * @param fileName File name for deposit file.
	 * @return TransactionList of data type deposit.
	 * @throws IOException
	 */
	public TransactionList<Deposit> loadDepositTransaction(String fileName) throws IOException {
		String line; // string for each line in file
		String[] stringArray; // array to accept strings when splitting
		int accountNumber;
		double amount;
		TransactionList<Deposit> list = new TransactionList<Deposit>(); // initialize transactionlist of type withdraw
		
		FileReader fileReader = new FileReader(fileName); // initialize filereader
	    Scanner scan = new Scanner(fileReader);
	    
	    // this loop reads the nextLine while there is one, splits it where there is whitespace, and parses the splits to accountNumber and amount
	    while (scan.hasNextLine()) {
	    	line = scan.nextLine();
	    	stringArray = line.split(" "); // split line by whitespace
	    	accountNumber = Integer.parseInt(stringArray[0]);
	    	amount = Double.parseDouble(stringArray[1]);
	    	Deposit deposit = new Deposit(accountNumber, amount); // initialize deposit object
	    	list.add(deposit);
	    }
	    scan.close(); // close scanner
		return list;
	}
}
	
