package com.cs3310.a1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * This class controls our log file.
 * A UIoutput object is made both in Setup and UserApp and the reference is passed
 * to the various other classes so they can log things.
 */
public class UIoutput {
    private BufferedWriter bw;
    private String logFile = "Log.txt";

    /**
     * Setup takes this route.
     * The log file is truncated.
     */
    public UIoutput() {
        try {
            this.bw = new BufferedWriter(new FileWriter(this.logFile, false));
            this.display_this("-->> OPENED Log file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * UserApp uses this option.
     * The log file is appended to.
     * @param append
     */
    public UIoutput(boolean append) {
        try {
            this.bw = new BufferedWriter(new FileWriter(this.logFile, append));
            this.display_this("-->> OPENED Log file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the message out to the log file and the console
     * @param message
     */
    public void display_this(String message) {
        try {
            message += "\r\n";
            this.bw.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(message);
    }

    /**
     * More complicated method that is used to display the different queries made of our BST
     * Thanks to: http://bit.ly/1Lidod6 (stack overflow) for the modulus trick below
     * @param c the country to pull data from
     * @param transCode the transaction type we're dealing with (I D S A)
     * @param searchCode the country code that was used for the query
     * @param searchCount the number of nodes that were involved
     * @param success was the query a success?
     */
    public void display_this(Country c, char transCode, String searchCode, int searchCount, boolean success) {

        short countryID;
        String message;
        String nodesVisited = "[visited " + searchCount + " nodes]";

        // if the query was a success
        if (success) {
            switch (transCode) {
                case 'I':
                    message = transCode + " " + searchCode + "," + c.getId() + "," + c.getName() + "," +
                            c.getContinent() + "," + c.getArea() + "," + c.getPopulation() + "," +
                            c.getLifeExpectancy() + " >>\r\n" + "OK, " + c.getName() + " inserted " +
                            nodesVisited + "\r\n";

                    this.display_this(message);
                    break;
                case 'D':
                    message = "D " + searchCode + " >> OK, " + c.getName() + " deleted " + nodesVisited +
                            "\r\n";

                    this.display_this(message);
                    break;
                case 'S':
                    countryID = c.getId();
                    if (countryID != 0) {
                        this.display_this("S " + searchCode + " >> " + nodesVisited);
                        this.print_S_A_Header();
                        message = String.format("%-3s ", c.getCode());
                        message += String.format("%03d ", countryID);
                        message += String.format("%-18.18s ", c.getName());
                        message += String.format("%-13s ", c.getContinent());
                        message += String.format("%,10d ", c.getArea() % 100000000);
                        message += String.format("%,13d ", c.getPopulation());
                        message += String.format("%04.1f\r\n", c.getLifeExpectancy());
                        this.display_this(message);
                    }
                    break;
                case 'A':
                    countryID = c.getId();
                    if (countryID != 0) {
                        message = String.format("%-3s ", c.getCode());
                        message += String.format("%03d ", countryID);
                        message += String.format("%-18.18s ", c.getName());
                        message += String.format("%-13s ", c.getContinent());
                        message += String.format("%,10d ", c.getArea() % 100000000);
                        message += String.format("%,13d ", c.getPopulation());
                        message += String.format("%04.1f", c.getLifeExpectancy());
                        this.display_this(message);
                    }
                    break;
                default:
                    break;
            }
        } else {
            // the query wasn't a success
            switch (transCode) {
                case 'I':
                    // shouldn't be needed for A1
                    // leaving here for the future
                    break;
                case 'D':
                    message = transCode + " " + searchCode + " >> invalid country code " + nodesVisited + "\r\n";
                    this.display_this(message);
                    break;
                case 'S':
                    message = transCode + " " + searchCode + " >> invalid country code " + nodesVisited + "\r\n";
                    this.display_this(message);
                    break;
                case 'A':
                    // shouldn't be needed for A1
                    // leaving here for the future
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * The header for 'S' and 'A' transactions
     */
    public void print_S_A_Header() {
        this.display_this("CDE ID- NAME-------------- CONTINENT---- ------AREA ---POPULATION LIFE");
    }

    /**
     * Close the log file
     */
    public void finish_up() {
        try {
            this.display_this("-->> CLOSED Log file");
            this.bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
