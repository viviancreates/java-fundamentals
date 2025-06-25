package com.mycoolcompany.zoomanager.business;
//Do not need an import statement when we utilize a class from same package
//Zoo and animal live in the same package

public class Zoo {
    private String name;
    private Animal[] population;

    // Optional
    public Zoo() {
        // Defining a blank constructor, so Java won't automatically make a default.
    }

    public Zoo(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal[] getPopulation() {
        return population;
    }

    public void setPopulation(Animal[] population) {
        this.population = population;
    }

    public void printPopulationReport() {
        System.out.println("Population Report: " + name);
        System.out.println("=============================================");
        System.out.println();

        //loop through the array of Animal objects stored in the zoo
        for (int i = 0; i < population.length; i++) {
            //access each animal one by one
            Animal a = population[i];
            //print each animal name
            //print how many in the zoo
            //requires getters from animal - get name an dget population count using dot method
            System.out.println(a.getName() + ": " + a.getPopulationCount());
        }

        System.out.println("=============================================");
    }

    public void printAnimalSounds() {
        System.out.println("The Sounds of: " + name);
        System.out.println("=============================================");
        System.out.println();

        for (int i = 0; i < population.length; i++) {
            Animal a = population[i];
            System.out.println(a.getSound());
        }

        System.out.println("=============================================");
    }


}
