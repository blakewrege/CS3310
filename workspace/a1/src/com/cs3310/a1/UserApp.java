package com.cs3310.a1;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * Tell other classes to do things.
 * 1. Open the log file so we can write stuff to it (append mode)
 * 2. Create a DataStorage object + read in the BST
 * 3. Create a UIinput object + read in the transactions
 * 4. Create a Transaction object to hold the incoming transactions
 */
public class UserApp {
    public static void main(String[] args) {
        // open the log in append mode
        UIoutput log = new UIoutput(true);
        log.display_this("-->> USERAPP started");

        // read in the BST
        DataStorage ds = new DataStorage("Backup.csv", log);
        UIinput in = new UIinput("TransData.txt", log);

        Transaction tr;

        // keep looping until we reach the end of the TransData.txt file
        tr = in.getNextTransaction();
        while (tr != null) {
            switch (tr.getTransCode()) {
                case 'I':
                    Country c = tr.createCountry();
                    ds.insert(c, true);
                    UIinput.numberOfTransactions++;
                    break;
                case 'D':
                    ds.delete(tr.getTransData());
                    UIinput.numberOfTransactions++;
                    break;
                case 'S':
                    ds.select(tr.getTransData(), true);
                    UIinput.numberOfTransactions++;
                    break;
                case 'A':
                    ds.showAll();
                    UIinput.numberOfTransactions++;
                    break;
                case '%':
                    break;
                default:
                    break;
            }
            tr = in.getNextTransaction();
        }

        // clean up after ourselves
        in.finish_up();
        ds.finish_up(ds);
        log.display_this("-->> USERAPP finished - processed " + UIinput.numberOfTransactions + " transactions");
        log.finish_up();
    }
}
