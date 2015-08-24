/**
 * 
 */
package edu.wmich.cs1120.advanceFileIO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * @author akg
 *
 */
public class DemoSerializeDeserialize {
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		serializeObjects();
		deserializeObjects();

	}
	
/**
 * This method serializes the objects in an array of      
 * InventoryItem objects.
 */	
	private static void serializeObjects() throws FileNotFoundException, IOException
	{
		final int NUM_ITEMS = 3;  // Number of items
	      String description;       // Item description
	      int units;                // Units on hand
	      
	      // Create a Scanner object for keyboard input.
	      Scanner keyboard = new Scanner(System.in);
	          
	      // Create an array to hold InventoryItem objects.
	      InventoryItem[] items =
	                   new InventoryItem[NUM_ITEMS];
	      
	      // Get data for the InventoryItem objects.
	      System.out.println("Enter data for " + NUM_ITEMS +
	                         " inventory items.");
	                         
	      for (int i = 0; i < items.length; i++)
	      {
	         // Get the item description.
	         System.out.print("Enter an item description: ");
	         description = keyboard.nextLine();
	         
	         // Get the number of units.
	         System.out.print("Enter the number of units: ");
	         units = keyboard.nextInt();

	         // Consume the remaining newline.
	         keyboard.nextLine();

	         // Create an InventoryItem object in the array.
	         items[i] = new InventoryItem(description, units);
	      }
	      
	      // Create the stream objects.
	      FileOutputStream outStream = 
	             new FileOutputStream("Objects.dat");
	      ObjectOutputStream objectOutputFile = 
	             new ObjectOutputStream(outStream);
	      
	      // Write the serialized objects to the file.
	      for (int i = 0; i < items.length; i++)
	      {
	         objectOutputFile.writeObject(items[i]);
	      }
	      
	      // Close the file.
	      objectOutputFile.close();
	      System.out.println("The serialized objects were written to the " +
	                         "Objects.dat file.");
	}
	
/**
 * This method deserializes the objects in the Objects.dat
 * file and stores them in an array.  
 */
	private static void deserializeObjects() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		final int NUM_ITEMS = 3;  // Number of items

	      // Create the stream objects.
	      FileInputStream inStream = 
	             new FileInputStream("Objects.dat");
	      ObjectInputStream objectInputFile = 
	             new ObjectInputStream(inStream);
	      
	      // Create an array to hold InventoryItem objects.
	      InventoryItem[] items = new InventoryItem[NUM_ITEMS];
	      
	      // Read the serialized objects from the file.
	      for (int i = 0; i < items.length; i++)
	      {
	         items[i] =
	             (InventoryItem) objectInputFile.readObject();
	      }
	      
	      // Close the file.
	      objectInputFile.close();
	      
	      // Display the objects.
	      for (int i = 0; i < items.length; i++)
	      {
	         System.out.println("Item " + (i + 1));
	         System.out.println("   Description: " + 
	                            items[i].getDescription());
	         System.out.println("   Units: " + 
	                            items[i].getUnits());
	      }
	}

}

/**
 * InventoryItem class
 * This class throws an IllegalArgumentException when an
 * empty string is passed as a description, or 0 is
 * passed as the number of units.
 */


/**
 * @author akg
 *
 */
public class InventoryItem	implements Serializable
	{
	   private String description;
	   private int units;

		/**
		 * No-arg constructor
		 */

	   public InventoryItem()
	   {
	      description = "";
	      units = 0;
	   }

		/**
		 * The following constructor accepts a
	     * String argument which is assigned to the
	     * description field.
		 */

	   public InventoryItem(String d)
	   {
	      if (d.equals(""))
	      {
	         throw new IllegalArgumentException("Description "
	                  + "is an empty string.");
	      }

	      description = d;
	      units = 0;
	   }

		/**
		 * The following constructor accepts a
	     * String argument which is assigned to the
	     * description field, and an int argument
	     * which is assigned to the units field. 
		 */

	   public InventoryItem(String d, int u)
	   {
	      if (d.equals(""))
	      {
	         throw new IllegalArgumentException("Description "
	                  + "is an empty string.");
	      }
	      if (u < 0)
	         throw new IllegalArgumentException("Units is 0.");

	      description = d;
	      units = u;
	   }

		/**
		 * The setDescription method assigns its
	     * argument to the description field. 
		 */
	   
	   public void setDescription(String d)
	   {
	      if (d.equals(""))
	      {
	         throw new IllegalArgumentException("Description "
	                  + " is an empty string.");
	      }

	      description = d;
	   }

		/**
		 * The setUnits method assigns its argument
		 * to the units field. 
		 */

	   public void setUnits(int u)
	   {
	      if (u < 0)
	         throw new IllegalArgumentException("Units is 0.");

	      units = u;
	   }

		/**
		 * The getDescription method returns the
	     * value in the description field.
		 */

	   public String getDescription()
	   {
	      return description;
	   }

		/**
		 * The getUnits method returns the value in
	     * the units field. 
		 */

	   public int getUnits()
	   {
	      return units;
	   }
}
