
import java.util.Scanner;
/**
 * Name: Blake Wrege 
 * Date: 01/28/2014
 * Course: CS112
 * Description: This program tests if the value is a Palindrome
 */
public class WorldData {
	public static void main(String[] args) {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	Scanner input = new Scanner(System.in);
	String wordList,inverseWord;
	String[] splitStr;
	int i, palindromes;
	palindromes = 0;
    System.out.println("Enter your text:");
    wordList = input.nextLine();
    splitStr = wordList.split("\\s+");
    	for(i=0; i < splitStr.length;i++){
    		inverseWord = printStringBackward(splitStr[i].toCharArray());
    		palindromes = palindromes + isPalindrome(splitStr[i],inverseWord);			
    		}
        System.out.printf("Number of Palindromes = %d",palindromes);
    	}
    	

	
	
	
	
private static String printStringBackward(char[] x){      
     String inverseWord = ""; 
	int ab = x.length;
       while(ab>0){
       inverseWord =inverseWord + Character.toString(x[ab-1]);
       ab--;
       }
	return inverseWord;
}
private static int isPalindrome(String splitStr ,String inverseWord ){
	if (splitStr.equalsIgnoreCase(inverseWord)){
	return 1;	
	}
	return 0;	
    }
}
