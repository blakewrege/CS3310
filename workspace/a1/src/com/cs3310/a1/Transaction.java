package com.cs3310.a1;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * Lines read from the TransData.txt file are turned into Transaction objects
 * by UIinput.
 *
 */
public class Transaction {
    private char transCode;
    private String transData;

    public Transaction(String transaction) {
        this.transCode = transaction.charAt(0);

        try {
            this.transData = transaction.substring(2);
        } catch(StringIndexOutOfBoundsException e) {
            this.transData = "";
        }
    }

    public char getTransCode() {
        return transCode;
    }

    public void setTransCode(char transCode) {
        this.transCode = transCode;
    }

    public String getTransData() {
        return transData;
    }

    public void setTransData(String transData) {
        this.transData = transData;
    }

    public String[] getTransDataAsArray() {
        return transData.split(",");
    }

    /**
     * An 'I' transaction can get turned into a Country object here.
     * And be ripe for sticking in the BST somewhere.
     * @return
     */
    public Country createCountry() {
        Country country = new Country();
        String[] parts = this.getTransDataAsArray();
        country.setId(Short.parseShort(parts[0]));
        country.setCode(parts[1]);
        country.setName(parts[2]);
        country.setContinent(parts[3]);
        country.setArea(Integer.parseInt(parts[4]));
        country.setPopulation(Long.parseLong(parts[5]));
        country.setLifeExpectancy(Float.parseFloat(parts[6]));

        return country;
    }
}
