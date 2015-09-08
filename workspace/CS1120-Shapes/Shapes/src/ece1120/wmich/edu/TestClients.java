package src.ece1120.wmich.edu;




public class TestClients {
	//test in main()

	 public static void main(String[] args) {
			
		Point p = new Point();
	      System.out.println("p  = " + p);
	      Point q = new Point(0.5, 0.5);
	      System.out.println("q  = " + q);
	      System.out.println("q  = " + q.toString());
	      System.out.println("dist(p, q) = " + p.distanceTo(q));
		p.shift(1.0, 2.0);
		System.out.println("shifted p  = " + p);
		
		Circle c1, c2;
		c1 = new Circle();
		System.out.println("c1: " + c1);
		c2 = new Circle(3.5);
		System.out.println("c2: " + c2 +" has diameter "+ c2.diameter()
				+ " and area " + c2.area());
		c2 = new Circle(p, 5);	
		System.out.println("c2 now: " + c2);
			
		Cylinder c3, c4;
		c3 = new Cylinder(2,5);
		System.out.println("c3: " + c3);
		c4 = new Cylinder(3,5);
		System.out.println("c4: " + c4 +" has area "+ c4.area()
				+ " and volume " + c4.volume());	
			
	  }
	}


	/* extend to include other 2-d and 3-d shapes 
	 * line, Rectangle, Triangle - derived from Point (note rectangle and triangle can derive from Line too)
	 * Cylinder, Sphere - derived from Circle
	 * Square - derived from Rectangle
	 * Cube - derived from Square
	 * RectangularBox - derived from Rectangle
	 * etc
	 * of course test by creating instantiating objects from these classes
	 * for polymorphism - create a collection of various shapes from the inheritance hierarchy
	 * and test that appropriate methods are called, etc.

	 * */

