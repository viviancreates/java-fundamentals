import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //add variables
        String enterCave = "";
        String direction = "";
        String actionTaken = "";
        String treasure = "";

        //Starting prompt where player enters the cave or leaves
        System.out.println("Welcome to Viv's Stardew Valley");
        Scanner console = new Scanner(System.in);

        System.out.println("Do you want to enter the dark cave? (yes/no) ");
        enterCave = console.nextLine();
        if ("yes".equalsIgnoreCase(enterCave)){
            System.out.println("Let's go explore!");
            System.out.println("Which way do you want to go? (left/right) ");
            direction = console.nextLine();
                if ("left".equalsIgnoreCase(direction)){
                    System.out.println("Omg, there's a zombie!");
                    System.out.println("Do you want to fight or flee?");
                    actionTaken = console.nextLine();
                        if ("fight".equalsIgnoreCase(actionTaken)){
                            System.out.println("You have defeated the zombies, congrats!!!");
                        }else if("flee".equalsIgnoreCase(actionTaken)){
                            System.out.println("Coward.. bye bye!");
                        }else{
                            System.out.println("Invalid choice.");
                        }
                }else if("right".equalsIgnoreCase(direction)){
                    System.out.println("Wow, you found a treasure room!");

                        //print out menut to see artifacts
                        System.out.println("G - Gem");
                        System.out.println("K - Key");
                        System.out.println("B - Book");
                        System.out.println("=====");

                        //prompt user
                        System.out.println("Which treasure do you choose?");

                        //capture console input into variable
                        treasure = console.nextLine().toLowerCase();

                        //case statements evaluate one case at a atime
                        switch(treasure) {
                            case "g": {
                                System.out.println("Wow, a prismatic shard, trade it in for a sword!");
                                break;
                            }
                            case "k": {
                                System.out.println("You found a key! Go find the door it opens!");
                                break;
                            }
                            case "b": {
                                System.out.println("You found a book to learn how to fish! Have fun :)");
                                break;
                            }
                            default:
                            {
                                System.out.println("Invalid option!");
                                break;
                            }
                        }



                }else{
                    System.out.println("Invalid option...");
                }
        }else if("no".equalsIgnoreCase(enterCave)){
            System.out.println("See you next time!");
        }else{
            System.out.println("Invalid choice.");
        }

        //final message
        System.out.println("Thank you for playing Viv's game!");
        //game summary
        if ("yes".equalsIgnoreCase(enterCave)) {
            if ("left".equalsIgnoreCase(direction)) {
                System.out.println("You chose to go " + direction + " and  decided to " + actionTaken + ".");
            } else if ("right".equalsIgnoreCase(direction)) {
                System.out.println("You chose to go " + direction + " and selected the " + treasure + " as your treasure.");
            } else {
                System.out.println("You entered the cave, but got lost??????");
            }
        } else {
            System.out.println("You chose to end the game early!");
        }

    }
}