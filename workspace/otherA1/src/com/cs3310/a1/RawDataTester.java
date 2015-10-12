package com.cs3310.a1;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 *
 */
public class RawDataTester {
    public static void main(String args[]) {
        RawData rd = new RawData("RawData.csv", null);
        Country tempCountry;

        for (int i = 0; i < 30; i++) {
            tempCountry = rd.inputOneCountry();
            if (tempCountry != null) {
                System.out.print(tempCountry.getId() + ":");
                System.out.print(tempCountry.getCode() + ":");
                System.out.print(tempCountry.getName() + ":");
                System.out.print(tempCountry.getContinent() + ":");
                System.out.print(tempCountry.getArea() + ":");
                System.out.print(tempCountry.getPopulation() + ":");
                System.out.print(tempCountry.getLifeExpectancy() + "\n");
                //System.out.println(tempCountry.toString());
            }
        }

        System.out.println("Number of Countries: " + RawData.getNumCountries());
        rd.finish_up();
    }
}
