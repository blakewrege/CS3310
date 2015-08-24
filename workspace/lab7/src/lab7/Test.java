package lab7;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {


	public static void main(String[] args) throws IOException {
		GregorianCalendar calendar = new GregorianCalendar();
		Date date = calendar.getTime();
		int sunset = date.getHours();
		FileInput fileReader = new FileInput();
		Account[] accounts = fileReader.loadAccounts("account.txt");

		
		TransactionList<Withdraw> withdraws = fileReader.loadWithdrawTransactions("withdraw.txt");
		withdraws.process(accounts);
		TransactionList<Deposit> deposits = fileReader.loadDepositTransaction("deposit.txt");
		deposits.process(accounts);
		
		if(sunset<20){
			System.out.println("Sunset is 8pm, it is before sunset: "+date);
		}
		if(sunset>19){
			System.out.println("Sunset is 8pm, it is after sunset: "+date);
		}
		for (int i = 0; i < accounts.length; i++) {
			System.out.print(accounts[i].getAccountNumber() + " ");
			System.out.print(accounts[i].getHolderFirstName() + " ");
			System.out.print(accounts[i].getHolderLastName() + " ");
			System.out.print(accounts[i].getHolderPhoneNumber() + " ");
			if (sunset<20){
				System.out.print("waiting");
			}else{
			System.out.print(accounts[i].getBalance());
			}
			System.out.println();
		}
	}
}
