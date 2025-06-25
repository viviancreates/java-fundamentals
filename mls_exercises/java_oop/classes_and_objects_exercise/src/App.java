
public class App {
    public static void main(String[] args) {
        //AquariumFish aquariumFish = new AquariumFish();
        //don't forget to call the constructor with an argument
        AquariumFish aquariumFish = new AquariumFish(
            "Clown",
            "Nemo",
            20.0,
            20.0,
            "Likes to eat plants!"
        );

        System.out.println("Species Name: " + aquariumFish.getSpecies());
        System.out.println("Common Name: " + aquariumFish.getCommonName());
        System.out.println("Max Temp: " + aquariumFish.getMaxTemp());
        System.out.println("Mi Temp: " + aquariumFish.getMinTemp());
        System.out.println("Diet: " + aquariumFish.getDiet());

        //System.out.println(aquariumFish.printAverageTemp());
        //Calling a void, do not need to wrap it in a println
        aquariumFish.printAverageTemp();
    }
}