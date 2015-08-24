package edu.wmich.cs1120;


public class Circle extends Point {

	private double radius;
	
	public Circle() {
		this.radius = 0.0;
		System.out.println("Default or no-arg constructor of Circle executed");
	}
	
	public Circle(double r) {
	     super();
		 radius = r;
		 System.out.println("One-param constructor of Circle executed");
  }
	   
  public Circle(Point center, double radius) {
      super(center.x(), center.y());
      this.radius = radius;
      System.out.println("Two-param constructor of Circle executed");
  }

  public boolean contains(Point p) {
  	return distanceTo(p) <= radius; 
  }

  public double diameter() { 
		System.out.println("in method diameter() of Circle");
	  	return radius * 2.0;   	
	  }
  
  public double area() { 
	System.out.println("in method area() of Circle");
  	return Math.PI * radius * radius;   	
  }
  
  public double perimeter() { 
	  System.out.println("in method perimeter() of Circle");
  	return 2 * Math.PI * radius;      
  }

  public boolean intersects(Circle c) {
  	Point center = new Point(c.x(), c.y());
      return super.distanceTo(center) <= radius + c.radius;
  }
  
  public String toString() {
      return super.toString() + " with Radius = " + radius;
  }

}


