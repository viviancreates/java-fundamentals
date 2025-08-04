package com.example;

import com.example.commands.player.*;
import com.example.player.Player;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player p1 = new Player(100, "Eric", "He's awesome");

        p1.addCommand(new Move());
        p1.addCommand(new Quit());

        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            p1.executeCommand(command);
        }
    }
}
