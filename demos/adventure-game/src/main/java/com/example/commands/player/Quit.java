package com.example.commands.player;

import com.example.commands.Command;

public class Quit implements Command {
    @Override
    public String getCommand() {
        return "logout";
    }

    @Override
    public int execute(String arg) {
        System.exit(0);
        return 1;
    }
}
