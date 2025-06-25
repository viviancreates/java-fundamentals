public class AquariumFish {
    private String species;
    private String commonName;
    private double maxTemp;
    private double minTemp;
    private String diet;

    //constructor to make instance
    //when you create a constructor, this makes sure the object has initial values on creation
    public AquariumFish(String species, String commonName, double maxTemp, double minTemp, String diet) {
        this.species = species;
        this.commonName = commonName;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.diet = diet;
    }

    public String getSpecies() {
        return species;
    }

    public String getCommonName() {
        return commonName;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public String getDiet() {
        return diet;
    }

    private void setSpecies(String species) {
        this.species = species;
    }

    private void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    private void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    private void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    private void setDiet(String diet) {
        this.diet = diet;
    }

    //cannot do publci void averageTemp(){ return average... }
    //REMEMBER VOID only prints DOES NOT RETURN
    public void printAverageTemp() {
        double sum = maxTemp + minTemp;
        double average = sum / 2;
        System.out.println("Avg Temp: " + average);
    }
}
