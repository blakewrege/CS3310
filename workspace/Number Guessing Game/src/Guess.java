// Author: Blake Wrege
// Lab 1
// CS 1120





import java.util.Random;
import java.util.Scanner;

public class Guess {
	private static String generateRandomNumber(){
		Random rand = new Random();
		String str;
		int n;
		do {
			do{
		    n = rand.nextInt(9999)+1;
			}while(n<1000);
		} while(containsRepeatingDigits(n));
		str = Integer.toString(n);
		return str;
	
	}
	private static boolean containsRepeatingDigits(final int n) {
	    final boolean digits[] = new boolean[10];
	    for(char c : String.valueOf(n).toCharArray()) {
	        final int i = c-'0';
	        if(digits[i])
	            return true;
	        digits[i] = true;
	    }
	    return false;
	}
	private static String getHint(String guess,String str){
		String hint;
		int i,n,k;
		n=0;k=0;	
		for(i=0;i<4;i++){
		if(guess.charAt(i) == str.charAt(i)){
		n=n+1;
		}else{
			if(guess.charAt(i)==str.charAt(0)){
				k=k+1;
			}
			if(guess.charAt(i)==str.charAt(1)){
				k=k+1;
			}
			if(guess.charAt(i)==str.charAt(2)){
				k=k+1;
			}
			if(guess.charAt(i)==str.charAt(3)){
				k=k+1;
			}
		}	
		}
		
		hint = n+"A"+k+"B";
		return hint;
	}
	

	public static void main(String[] args) {   
		 Scanner input = new Scanner(System.in);
		 int chances = 5;
		 String str = generateRandomNumber(); 
		 str = str.trim();
	//	  System.out.printf("%s  ",str);
		  String guess;

do{
    System.out.printf("You have %d chances. Input your guess:",chances);
    guess = input.nextLine();
    if(false == guess.equalsIgnoreCase(str)){
    	System.out.printf("\n%s\n",getHint(guess,str));
    }
    chances--;
    }while(chances>0 && false == guess.equalsIgnoreCase(str));
		
	if(true == guess.equalsIgnoreCase(str)){
		System.out.printf("\nWinner! %s\n",str);
	}else{
		System.out.printf("\nLoser! %s\n",str);	
	}
	
	}
	}
	
	