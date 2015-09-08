import java.util.ArrayList;
import java.io.*;
public class ArrayListSerialization
{
   public static void main(String [] args)
   {
       ArrayList<String> al=new ArrayList<String>();
       al.add("1. Onity key Hacker");
       al.add("2. Remote Door Opener");
       al.add("3. Scrolling LED Display");

       try{
    	   File fr  = new File("poo");
         FileOutputStream fos= new FileOutputStream(fr);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(al);
         oos.close();
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
   }
}