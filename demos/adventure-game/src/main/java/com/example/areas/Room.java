package com.example.areas;

import com.example.commands.Command;

import java.util.HashMap;

public class Room {
    private String description;
    private HashMap<String, Command> commands;
    private HashMap<String, Room> exits;
    private String id;

    public Room() {

    }

    public void addCommand(Command command) {
        if (!commands.containsKey(command.getCommand())) {
            commands.put(command.getCommand(), command);
        }
    }

    public void removeCommand(String key) {
        commands.remove(key);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }


}


