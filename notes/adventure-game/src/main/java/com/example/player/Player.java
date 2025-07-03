package com.example.player;

import com.example.base.Living;

public class Player extends Living {
    public Player(int maxHP, String name, String description) {
        super(maxHP, name, description);
    }

    public void executeCommand(String commandText) {
        String[] commandArgs = commandText.split(" ");

        if (commandArgs.length > 1) {
            doCommand(commandArgs[0], commandArgs[1]);
        } else {
            doCommand(commandArgs[0], null);
        }
    }
}
