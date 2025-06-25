package com.mycoolcompany.zoomanager.business;

/*
This class doesn't do much on its own.
All it does is give us a place to store and retrieve data.
This type of class is a POJO, or "plain old Java object".
 */

public class Animal {
    private String name;
    private int populationCount;
    private String sound;

    // Optional
    public Animal() {
    // Defining a blank constructor, so Java won't automatically make a default.
    }

    public Animal(String name, int populationCount, String sound) {
        this.name = name;
        this.populationCount = populationCount;
        this.sound = sound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(int populationCount) {
        this.populationCount = populationCount;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }




}

