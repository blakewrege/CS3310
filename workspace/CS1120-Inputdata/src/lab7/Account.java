package lab7;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {
	
	private int accountNumber;
	private String holderFirstName;
	private String holderLastName;
	private String holderPhoneNumber;
	private double balance;
	
	/**
	 * This constructor splits the line into account number, first name, last name,
	 * phone number, and balance using a scanner.
	 * @param line A line of the file account.txt.
	 */
	public Account(String line) {
		Scanner scan = new Scanner(line);
		accountNumber = scan.nextInt();
		holderFirstName = scan.next();
		holderLastName = scan.next();
		holderPhoneNumber = scan.next();
		balance = scan.nextDouble();
	}
	
	/**
	 * Get method which returns the account number.
	 * @return Account Number
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	public String getHolderFirstName() {
		return holderFirstName;
	}
	public String getHolderLastName() {
		return holderLastName;
	}

	public String getHolderPhoneNumber() {
		return holderPhoneNumber;
	}


	
	public void setBalance(double balance) {
		this.balance = balance;
	}	
	public double getBalance() {
		return balance;
	}
}
