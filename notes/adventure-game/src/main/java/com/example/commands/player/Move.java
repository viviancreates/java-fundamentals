package com.example.commands.player;

import com.example.commands.Command;

public class Move implements Command {

    @Override
    public String getCommand() {
        return "move";
    }

    @Override
    public int execute(String arg) {
        if (arg != null) {
            // process the argument
            switch(arg) {
                case "north":
                case "n":
                    System.out.println("You walk north.");
                    return 1;
                case "south":
                case "s":
                    System.out.println("You walk south.");
                    return 1;
                default:
                    System.out.println("That is not a valid direction!");
                    return 0;
            }
        } else {
            System.out.println("Move where?");
            return 0;
        }
    }
}
