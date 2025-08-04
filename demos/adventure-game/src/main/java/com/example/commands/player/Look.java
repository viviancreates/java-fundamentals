package com.example.commands.player;

import com.example.commands.Command;

public class Look implements Command {
    @Override
    public String getCommand() {
        return "logout";
    }

    @Override
    public int execute(String arg) {
        if (arg == null) {

        }
        return 1;
    }
}
