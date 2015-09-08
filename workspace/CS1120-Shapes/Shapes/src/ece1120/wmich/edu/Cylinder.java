package src.ece1120.wmich.edu;

public class Cylinder extends Circle {

	private double height;
	
	public Cylinder() {
		this.height = 0.0;
		System.out.println("Default or no-arg constructor of Cylinder executed");
	}
	
	public Cylinder(double h) {
	     super();
		 this.height = h;
		 System.out.println("One-param constructor of Cylinder executed");
 }
	  public Cylinder(double r, double h) {
	      super(r);
	      this.height = h;
	      System.out.println("Two-param constructor of Circle executed");
	  }
	  public Cylinder(Point center,double r, double h){
super(center,r);
this.height = h;		  
	  }
	public double area() { 
		double a;
		a = 2*super.area()+(super.perimeter()*height);
		System.out.println("in method area() of Cylinder");
	  	return a;  	
	  }
	
	public double volume() { 
		double v;
		v= super.area()*height;
		return v;
	  }
	
	  public String toString(){
		  return super.toString() + " with Height = " + height;
	}
}
