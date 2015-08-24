package viki;
//ssh -L 6667:dot:6667 gigglesbw4@yakko.cs.wmich.edu
//bot.connect("yakko.cs.wmich.edu",6667,"Starwars3");
// -N and -f
//bot.connect("irc.freenode.net");


    	public class MyBotMain {
    	    
    	    public static void main(String[] args) throws Exception {
    	        
    	        // Now start our bot up.
    	        MyBot bot = new MyBot();
    	        
    	        // Enable debugging output.
    	        bot.setVerbose(true);
    	        
    	        // Connect to the IRC server.
    	      bot.connect("dot",6667,"Starwars3");

    	        // Join the #pircbot channel.
    	        bot.joinChannel("#asdf");
    	        
    	    }
    	    
}