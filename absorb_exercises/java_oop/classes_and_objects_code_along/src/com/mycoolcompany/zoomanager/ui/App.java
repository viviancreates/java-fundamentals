package com.mycoolcompany.zoomanager.ui;

import com.mycoolcompany.zoomanager.business.Animal;
import com.mycoolcompany.zoomanager.business.Zoo;

public class App {
    public static void main(String[] args) {
        //make a new zoo object
        // Zoo is the blueprint and zoo is the real zoo, the object
        // the constructor is giving the zoo this name
        Zoo zoo = new Zoo("Kalama Zoo");

        Animal lion = new Animal("Lion", 5, "Grrr");
        Animal tiger = new Animal("Tiger", 2, "ROAR");
        Animal bear = new Animal("Bear", 2, "growlsplash");

        //create an array that can hold 3 animals
        Animal[] population = new Animal[3];

        population[0] = lion;
        population[1] = tiger;
        population[2] = bear;

        zoo.setPopulation(population);

        // Zookeeper print a report
        zoo.printPopulationReport();

        // Visitor gets to hear the animals
        zoo.printAnimalSounds();
    }
}