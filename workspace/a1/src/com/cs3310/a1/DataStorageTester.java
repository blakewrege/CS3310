package com.cs3310.a1;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 *
 */
public class DataStorageTester {

    public static void main(String args[]) {
        UIoutput log = new UIoutput();
        RawData rd = new RawData("RawData.csv", log);
        DataStorage ds = new DataStorage(log);
        Country c = rd.inputOneCountry();

        while (c != null) {
            ds.insert(c, false);
            c = rd.inputOneCountry();
        }

        ds.finish_up(ds);

        //DataStorage ds_input = new DataStorage("Backup.csv", log);
    }
}
