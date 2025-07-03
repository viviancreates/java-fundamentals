package com.example.base;

import com.example.areas.Room;
import com.example.commands.Command;

import java.util.HashMap;

public class Living {
    protected int maxHP;
    protected int currentHP;

    protected String name;
    protected String description;

    protected HashMap<String, Command> commands;
    protected Room currentRoom;

    public Living(int maxHP, String name, String description) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.name = name;
        this.description = description;

        this.commands = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHPBar() {
        return String.format("%d/%d", currentHP, maxHP);
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void addCommand(Command command) {
        if (!commands.containsKey(command.getCommand())) {
            commands.put(command.getCommand(), command);
        }
    }

    public void removeCommand(String key) {
        commands.remove(key);
    }

    public void doCommand(String key, String args) {
        if(commands.containsKey(key)) {
            commands.get(key).execute(args);
        }
    }
}
