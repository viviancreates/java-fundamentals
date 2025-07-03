package com.example.commands;

public interface Command {
    String getCommand();
    int execute(String arg);
}
