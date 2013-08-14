package ui;

import java.util.Scanner;

/**
 * Text user interface.
 *
 * @author Sebastian Björkqvist
 */
public class TextUI implements UI {

    private Scanner scanner;

    public TextUI() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void start() {
        printStartText();
        goToMainMenu();
    }

    private void printStartText() {
        System.out.println("---- Poker hand simulator ----");
    }

    private void printMainMenu() {
        System.out.println("1) Start a new simulation");
        System.out.println("2) Help");
        System.out.println("3) Quit");
    }

    private void goToMainMenu() {
        boolean printMainMenu = true;
        while (true) {
            
            System.out.println("");
            if (printMainMenu) {
                printMainMenu();
            }            
            System.out.println("");
            System.out.print(">> ");
            String input = scanner.nextLine();
            input = input.trim();
            
            if (input.equals("1")) {
                startSimulation();
                printMainMenu = true;
            } else if (input.equals("2")) {
                printHelp();
                printMainMenu = true;
            } else if (input.equals("3")) {
                System.out.println("Shutting down...");
                break;
            } else {
                System.out.println("Unknown command!");
                printMainMenu = false;
            }
        }
    }

    private void startSimulation() {
        System.out.println("Not implemented yet!");
    }

    private void printHelp() {
        System.out.println("No help here yet!");
    }
}
