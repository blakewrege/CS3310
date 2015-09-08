package src.ece1120.wmich.edu;

public class Point {

	 	private double x;   // Cartesian
	    private double y;   // coordinates
	   
	    // create and initialize a random point in unit square
	    public Point() {
	        this.x = 0.0;
	        this.y = 0.0;
	        System.out.println("Default or no-arg constructor of Point executed");
	    }

	    // create and initialize a point with given (x, y)
	    public Point(double x, double y) {
	        this.x = x;
	        this.y = y;
	        System.out.println("Default or no-arg constructor of Point executed");
	    }


	    // accessor methods  
	    public double x() { return x; }
	    public double y() { return y; }

	    // return Euclidean distance between this point and that point
	    public double distanceTo(Point that) {
	        double dx = this.x - that.x;
	        double dy = this.y - that.y;
	        return Math.sqrt(dx*dx + dy*dy);
	    }

	    //shift the coordinates of the point by xs and ys units

	    public void shift (double xs, double ys) {
		//both assignments below are equivalent	
		this.x += xs;
		y = this.y + ys;
	    }
	    
	    // return string representation of this point
	    public String toString() {
	        return "(" + x + ", " + y + ")";
	    }
	}




