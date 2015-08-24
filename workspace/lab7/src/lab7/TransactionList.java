package lab7;

import java.util.ArrayList;

public class TransactionList<T extends Transaction> {
	ArrayList<T> list = new ArrayList<T>();
	
	void process(Account[] accounts) {
		int i=0;
		for(i=0;i<list.size();i++){
		list.get(i).process(accounts);
		}
	}
	
	void add(T transaction) {
		list.add(transaction);
	} 
	
	T get(int index) {
		return list.get(index);
	} 
	
	int size() {
		return list.size();
	}
}
