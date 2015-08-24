

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Borked2 {

    public static void main(String[] args) throws Exception {
        String server = "dot";
        String nick = "GLaDOS";
        String login = "GLaDOS";
        String channel = "#asdf";
        String line = null;
        String lists = "";
        int i = 0;
        List<String> L1 = new ArrayList<String>();
        List<String> L2 = new ArrayList<String>();
        Socket socket = new Socket(server, 6667);
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream( )));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream( )));
        writer.write("NICK " + nick + "\r\n");
        writer.write("USER " + login + " 8 * : laughingman\r\n");
        writer.flush( );
        while ((line = reader.readLine( )) != null) {
            if (line.indexOf("004") >= 0) {
                break;}
            else if (line.indexOf("433") >= 0) {
                System.out.println("Nickname is already in use.");
                return;}}
        writer.write("JOIN " + channel + "\r\n");
        writer.flush( );
        
        
        
        
        
        while ((line = reader.readLine( )) != null) {
        	if (line.contains("PING")==true) {
                // We must respond to PINGs to avoid being disconnected.
                writer.write("PONG " + line.substring(5) + "\r\n");
                writer.flush( );
        	}
            if (line.contains("~test")==true||line.contains(",test")==true) {
            	writer.write("PRIVMSG " + channel + " :Cake, and grief counseling, will be available at the conclusion of the test.\r\n");
                writer.flush( );
            }
            if (line.contains(",help")==true) {
            	writer.write("PRIVMSG " + channel + " :create portals.\r\n");
                writer.flush( );
            }
            if (line.contains(",read")==true) {
            	L1 = readtxt("read.txt");
            	writer.write("PRIVMSG " + channel + " :"+tostring(L1)+"\r\n");		
            	writer.flush( );
			} 

            if (line.contains(",geekouts")) {
            	L1 = readdat("geekouts.txt".trim());
            	if (line.contains(",geekouts add")) {
            	writer.write("PRIVMSG " + channel + " : ok\r\n");
            	writer.flush( );
            	String[] spl = line.split(",geekouts add");
            	i = L1.size()+1;	
            	L1.add(spl[1]+" ");
            	writedat("geekouts.txt", L1);
            	}else if (line.contains("geekouts remove")){	
            	writer.write("PRIVMSG " + channel + " : ok\r\n");
            	writer.flush( );      	
            	String[] spl = line.split(",geekouts remove",2);
            	String str = spl[1].trim();
            	try{
            	try{
            	i = Integer.valueOf(str);
            	if (i-1<=L1.size()&& i>0) {
            	L1.remove(i-1);
            	writedat("geekouts.txt", L1);
            	} 
            	}catch (NumberFormatException e) {
                str = "1";
                }
                System.out.println(str);
               	}catch (IndexOutOfBoundsException e){
               	str ="1";	
               	}}
            	else{
            	writer.write("PRIVMSG " + channel + " :"+tostring(L1)+"\r\n");		
            	writer.flush( );
            	}
            }
            
            }
        }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
         
            
    // reads a file
    public static List<String> readtxt(String meme) throws FileNotFoundException{
    	File read = new File(meme);
    	FileReader re = new FileReader(read);
    	Scanner sc = new Scanner(re);
    	String item="";
    	List<String> lines = new ArrayList<String>();
    	while (sc.hasNextLine()==true) {
    		item =  sc.nextLine();
    		lines.add(item);
    	}
    	sc.close();
    	return lines;
    }
    public static String tostring(List<String> list1){
    	String lines = "";
    	String str = " ";
    	int k;
    	for (int j = 0; j < list1.size(); j++) {
    	k = j+1;	
    	str =	k+": "+ list1.get(j);
    	lines = lines +" "+ str;	
    	}
    	return lines;	
    }
    public static void writetxt(String File, String writey) throws IOException{
    FileWriter wr = new FileWriter(File, true);	
    BufferedWriter out = new BufferedWriter(wr);
    out.write(writey);
    out.newLine();
    out.close();
    }
    public static void writedat(String File, List<String> L1) throws IOException{	
    try{
        FileOutputStream fos= new FileOutputStream(File);
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(L1);
        oos.close();
        fos.close();
      }catch(IOException ioe){
           ioe.printStackTrace();
       }
    }
    public static List<String> readdat(String meme) throws FileNotFoundException{
    ArrayList<String> A1;
	try
    {
        FileInputStream fis = new FileInputStream(meme);
        ObjectInputStream ois = new ObjectInputStream(fis);
        A1 = (ArrayList) ois.readObject();
        ois.close();
        fis.close();
     }catch(IOException ioe){
         ioe.printStackTrace();
         return null;
      }catch(ClassNotFoundException c){
         System.out.println("Class not found");
         c.printStackTrace();
         return null;
      }
	return A1;
    }
    }
    
    
    
    
    
        

