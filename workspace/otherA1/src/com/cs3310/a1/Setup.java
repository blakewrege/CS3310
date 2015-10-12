package com.cs3310.a1;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * Tell other classes to do things.
 * 1. Open the log file so we can write stuff to it (truncate mode)
 * 2. Create a RawData object
 * 3. Create a DataStorage object
 * 4. Create a Country object to hold stuff from RawData
 */
public class Setup {
    public static void main(String[] args) {
        UIoutput log = new UIoutput();
        log.display_this("-->> SETUP started");
        RawData rd = new RawData("RawData.csv", log);
        DataStorage ds = new DataStorage(log);
        Country c = rd.inputOneCountry();

        // keep reading data and putting it in the BST
        // until we're at the EOF
        while (c != null) {
            ds.insert(c, false);
            c = rd.inputOneCountry();
        }

        // clean up after ourselves
        rd.finish_up();
        ds.finish_up(ds);
        log.display_this("-->> SETUP finished - inserted " + ds.getN() + " countries into DataStorage");
        log.finish_up();
    }
}
