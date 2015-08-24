import java.util.Scanner;

public class GCD {
	private static Scanner input;
	public static void main(String[] args) {
	    System.out.println("Welcome to GCD Recursion program ");
    input = new Scanner(System.in);
    System.out.println("Please enter two integers: ");
    int n1 = input.nextInt();
    int n2 = input.nextInt();

    int d = 0;
    int temp = 0;
    if(n1 < n2) {
        temp = n1;
        n1 = n2;
        n2 = temp;
    }

    for(d = n1;((n1 % d !=0 || n2 % d != 0));d--)  {

    }

    System.out.println("The GCD of " + n1 + " and " + n2 + " is " + d);
		}
	}


	
