import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Decisions App!");
        Scanner console = new Scanner(System.in);

        //snooze the alarm when you are tired (if-else)
        //if statements can help execute code only when evaluating a condition is true
        System.out.println("Wake Up");
        boolean tooTired = true;
        if(tooTired){
            System.out.println("Too tired... snooze");
        }else{
            System.out.println("Let's get up and get at it!");
        }

        //decide to stop at the stop light (else-if)
        System.out.println("Approaching stop light!");
            //prompt user
            System.out.print("Stoplight color? ");
            //capture user input into a variable named color
            String color = console.nextLine();
            if("red".equalsIgnoreCase(color)){
                System.out.println("Stop!");
            }else if("yellow".equalsIgnoreCase(color)){
                System.out.println("Slow down, then stop.");
            }else if("green".equalsIgnoreCase(color)){
                System.out.println("Go!!");
            }else{
                System.out.println("Invalid color");
            }

        //decide to speed through a yellow stop light (nested-if)
        System.out.println("Approaching stop light changing color!");

        System.out.print("Stoplight color? ");
        String color2 = console.nextLine();

        if("red".equalsIgnoreCase(color2)){
            System.out.println("Stop!");
        }else if("yellow".equalsIgnoreCase(color)){
            System.out.println("How far from the light?");
            String input = console.nextLine();
            int distance = Integer.parseInt(input);
            if(distance >= 20) {
                System.out.println("Slow down and stop");
            }else{
                System.out.println("Slow down, then stop");
            }

        }else if("green".equalsIgnoreCase(color)){
            System.out.println("Go!!");
        }else{
            System.out.println("Invalid color");
        }

        //decide which direction your adventure will go in? (switch)
        System.out.println("Which way do you want to go?");
            //print out menu for user to see which direction they can go
            System.out.println("N - North");
            System.out.println("S - South");
            System.out.println("E - East");
            System.out.println("W - West");
            System.out.println("=====");
            //prompt user
            System.out.println("Which direction?");
            //capture console input into a variable named direction
            String direction = console.nextLine();

            //case statements only evaluate one case at a time
            //case sensitive
            switch(direction) {
                case "N": {
                    System.out.println("Go North");
                    break;
                }
                case "S": {
                    System.out.println("Go South");
                    break;
                }
                case "E": {
                    System.out.println("Go East");
                    break;
                }
                case "W": {
                    System.out.println("Go West");
                    break;
                }
                //default keyword is used as a catch-all, if we do not match any case, print out default "invalid"
                default:
                {
                    System.out.println("Invalid Input");
                    break;
                }
            }









    }
}