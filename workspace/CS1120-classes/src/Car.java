import java.util.Date;
import java.util.GregorianCalendar;

public class Car extends Vehicle{

private String strCar;

public Car(){
	this.strCar = "";
}

// passes element of Vehicle string to Cars
public Car(String Cars){
	this.strCar = Cars;
}
	
public void Display(){
	String[] cars;
	int rent;
	GregorianCalendar Pick = new GregorianCalendar();
	Date rDate;
	rDate = Pick.getTime();
	rent = rDate.getDate();
	cars = strCar.split(",");
	
	// Displays Car info
	System.out.printf("\nRented: %s\nVIN Number: %s\nCar Make: %s\nCar Model: %s\nCar Year: %s\n",cars[1],cars[2],cars[3],cars[4],cars[5]);
	if(cars[1].equalsIgnoreCase("Yes")){
		System.out.println("Pickup Date: " + rDate);
		rDate.setDate(rent+7);
		System.out.println("Return Date: " + rDate);
}
	

}
}
