package test;

public class List2 {
	public String data;
	public List2 next;
	List2(){
		this.data = null;
		this.next = null;	
	}
	public List2(String data, List2 next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return data;
		
	}

}