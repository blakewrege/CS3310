import java.util.Date;
import java.util.GregorianCalendar;

public class Motorcycle extends Vehicle{
	private String strMotorcycle;

	public Motorcycle(){
		this.strMotorcycle = "";
	}
	
	// passes element of Vehicle string to Motorcycle
	public Motorcycle(String Motorcycle){
		this.strMotorcycle = Motorcycle;
	}
	
@SuppressWarnings("deprecation")
public void Display(){
	String[] motorcycles;
	int rent;
	GregorianCalendar Pick = new GregorianCalendar();
	Date rDate;
	rDate = Pick.getTime();
	rent = rDate.getDate();
	motorcycles = strMotorcycle.split(",");
	
	//Displays Motorcycle info
	System.out.printf("\nRented: %s\nVIN Number: %s\nMotorcycle Make: %s\nMotorcycle Model: %s\nMotorcycle Year: %s\nCC: %s\n",motorcycles[1],motorcycles[2],motorcycles[3],motorcycles[4],motorcycles[5],motorcycles[6]);
	if(motorcycles[1].equalsIgnoreCase("Yes")){
		System.out.println("Pickup Date: " + rDate);
		rDate.setDate(rent+7);
		System.out.println("Return Date: " + rDate);

}
}
}

