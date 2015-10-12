package com.cs3310.a1;

import java.util.ArrayList;

/**
 * CS3310 - Assignment 1 - BST - World Data Storage / Lookup
 * Author: Benjamin Masters
 * Modified: 2015/09/29
 */

/**
 * The data read from the RawData file is stored as Country Objects.
 * The following is pretty standard java...
 * Lots of private fields and getters/setters for them.
 */
public class Country {
    private short id;
    private String code;
    private String name;
    private String continent;
    private String region;
    private int area;
    private short yearFounded;
    private long population;
    private float lifeExpectancy;
    private int field10;
    private String field11;
    private String governmentForm;
    private String leader;
    private short field14;
    private String field15;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        code = code.toUpperCase();

        if (code.length() == 3) {
            this.code = code;
        } else {
            this.code = "ERROR";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        ArrayList<String> continents = new ArrayList<>(7);
        continents.add("Africa");
        continents.add("Antarctica");
        continents.add("Asia");
        continents.add("Europe");
        continents.add("North America");
        continents.add("Oceania");
        continents.add("South America");

        if (continents.contains(continent)) {
            this.continent = continent;
        } else {
            this.continent = "INVALID";
        }
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        if (area > 0) {
            this.area = area;
        } else {
            // set to 0 per Kaminski
            this.area = 0;
        }
    }

    public short getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(short yearFounded) {
        this.yearFounded = yearFounded;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        if (population >= 0) {
            this.population = population;
        } else {
            // set to 0 per Kaminski
            this.population = 0;
        }
    }

    public float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(float lifeExpectancy) {
        if (lifeExpectancy > 0) {
            this.lifeExpectancy = lifeExpectancy;
        } else {
            // set to 0 per Kaminski
            this.lifeExpectancy = 0.0f;
        }
    }

    public int getField10() {
        return field10;
    }

    public void setField10(int field10) {
        this.field10 = field10;
    }

    public String getField11() {
        return field11;
    }

    public void setField11(String field11) {
        this.field11 = field11;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public short getField14() {
        return field14;
    }

    public void setField14(short field14) {
        this.field14 = field14;
    }

    public String getField15() {
        return field15;
    }

    public void setField15(String field15) {
        this.field15 = field15;
    }

    /**
     * I thought this could come in handy at some point.
     * (but not for this assignment, unfortunately).
     * It returns the country's data as a CSV string
     * @return
     */
    @Override
    public String toString() {

        return String.valueOf(this.getId()) + "," + this.getCode() + "," + this.getName() + "," +
                this.getContinent() + "," + this.getRegion() + "," + this.getArea() + "," +
                this.getYearFounded() + "," + this.getPopulation() + "," +
                this.getLifeExpectancy() + "," + this.getField10() + "," + this.getField11() + "," +
                this.getGovernmentForm() + "," + this.getLeader() + "," + this.getField14() + "," + this.getField15();
    }
}
