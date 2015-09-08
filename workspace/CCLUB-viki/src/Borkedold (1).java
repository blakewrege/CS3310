

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Borked3{

    public static void main(String[] args) throws Exception {
        String server = "dot";
        String nick = "GLaDOS";
        String login = "GLaDOS";
        String channel = "#asdf";
        String line = null;
        List<String> L1 = new ArrayList<String>();
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
            if (line.contains(",read")==true) {
            	L1 = readthis("read.txt");
            	writer.write("PRIVMSG " + channel + " :"+tostring(L1)+"\r\n");		
            	writer.flush( );
			} 
            if (line.contains(",geekouts")) {
            	L1 = readthis("geekouts.txt");
            	if (line.contains(",geekouts add")) {
            	String[] spl = line.split(",geekouts add");
            	int i = L1.size()+1;
            	writer.write("PRIVMSG " + channel + " : ok\r\n");		
            	writer.flush( );	
            	writeto("geekouts.txt",i+":"+spl[1]+" ");
            	}else if (line.contains(",geekouts remove")){
            	String[] spl = line.split(",geekouts add");	
            	}
            	else{
            	writer.write("PRIVMSG " + channel + " :"+tostring(L1)+"\r\n");		
            	writer.flush( );
            	}
            }
        }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
         
            }
    // reads a file
    public static List<String> readthis(String meme) throws FileNotFoundException{
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
    	for (int j = 0; j < list1.size(); j++) {
    	lines = lines +" "+ list1.get(j);	
    	}
    	return lines;	
    }
    public static void writeto(String File, String writey) throws IOException{
    FileWriter wr = new FileWriter(File, true);	
    BufferedWriter out = new BufferedWriter(wr);
    out.write(writey);
    out.newLine();
    out.close();
    }
    }
    
    
    
    
    
        

