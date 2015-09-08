package lab7;


public class Deposit extends Transaction {
	
	int accountNumber;
	double amount;
	double balanceChange;
	
	public Deposit(int accountNumber, double amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	void process(Account[] accounts) {
		for(int i = 0; i < accounts.length; i++) {
			if(accounts[i].getAccountNumber() == this.accountNumber) {
				double newBalance = accounts[i].getBalance() + this.amount;
				accounts[i].setBalance(newBalance);
			}
		}
	}
}
