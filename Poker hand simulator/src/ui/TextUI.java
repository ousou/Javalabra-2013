package ui;

import card.Card;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import logic.parsing.TextParser;
import logic.simulator.AbstractPokerHandSimulator;
import logic.simulator.PokerHandSimulatorVersion2;
import logic.simulator.SimulationResult;
import poker.FiveCardBoard;
import poker.enums.PokerGameType;
import poker.startinghands.AbstractStartingHand;
import poker.startinghands.FiveCardDrawStartingHand;
import poker.startinghands.OmahaHoldemStartingHand;
import poker.startinghands.SevenCardStudStartingHand;
import poker.startinghands.TexasHoldemStartingHand;

/**
 * Text user interface.
 *
 * @author Sebastian Björkqvist
 */
public class TextUI implements UI {

    private Scanner scanner;
    private Set<Card> deadCards;
    private final static int resultAccuracy = 3;

    public TextUI() {
        this.scanner = new Scanner(System.in);
        deadCards = new HashSet<Card>();
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

    /**
     * Handles the printing and control flow of in the main menu.
     */
    private void goToMainMenu() {
        boolean printMainMenu = true;
        while (true) {
            deadCards.clear();
            System.out.println("");
            if (printMainMenu) {
                printMainMenu();
            }
            System.out.println("");
            System.out.print("> ");
            String input = scanner.nextLine().trim();
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
                System.out.println("Unknown command");
                printMainMenu = false;
            }
        }
    }

    private void startSimulation() {
        PokerGameType gameType = getGameSelection();
        int numberOfHands = getNumberOfHands(gameType);
        List<AbstractStartingHand> startingHands = getStartingHands(numberOfHands, gameType);
        FiveCardBoard board = null;
        if (gameType.isCommunityCardGame()) {
            board = getBoard();
            deadCards.addAll(board.getCards());
        }
        int numberOfSimulations = getNumberOfSimulations();
        

//        printSelections(gameType, numberOfHands, startingHands, board, numberOfSimulations);

        AbstractPokerHandSimulator simulator;
        if (gameType.isCommunityCardGame()) {
            simulator = new PokerHandSimulatorVersion2(startingHands, board.getCards(), numberOfSimulations);
        } else {
            simulator = new PokerHandSimulatorVersion2(startingHands, numberOfSimulations);
        }
        SimulationResult result;
        
        System.out.println("Simulating, please wait...");
        long start = System.currentTimeMillis();

        try {
            result = simulator.performSimulation(2);
        } catch (InterruptedException ex) {
            System.out.println("One of the simulation threads were interrupted. Please try again.");
            return;
        }
        long end = System.currentTimeMillis(); 
        double timeElapsed = (1.0*end-start)/1000;     
        printResults(result, startingHands, board, gameType, timeElapsed);                
    }

    private void printHelp() {
        System.out.println("");
        System.out.println("Cards are inputted by giving the two-character representation of them.");
        System.out.println("The first letter represents the rank, and the second represents the suit.");
        System.out.println("Possible ranks: A, 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K");
        System.out.println("Possible suits: h, d, c, s");
        System.out.println("");
        System.out.println("Examples:");
        System.out.println("Ace of spades: As");
        System.out.println("Ten of diamonds: Td");
        System.out.println("Queen of hearts: Qh");
        System.out.println("Nine of clubs: 9c");
        System.out.println("");
        System.out.println("When inputting multiple cards at the same time, the cards have to be");
        System.out.println("separated by a comma. For instance, to enter a hand containing the");
        System.out.println("Jack of clubs and the deuce of diamonds, one would enter Jc, 2c");
        System.out.println("The space after the comma is not necessary.");
        System.out.println("");
        System.out.print("Press any key to return to the main menu.");
        scanner.nextLine();
    }

    private PokerGameType getGameSelection() {
        while (true) {
            printGameSelectionMenu();
            System.out.println("");
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return PokerGameType.TEXAS;
            } else if (input.equals("2")) {
                return PokerGameType.OMAHA;
            } else if (input.equals("3")) {
                return PokerGameType.SEVEN_STUD;
            } else if (input.equals("4")) {
                return PokerGameType.FIVE_DRAW;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    private void printGameSelectionMenu() {
        System.out.println("");
        System.out.println("Select poker game type:");
        System.out.println("1) Texas hold'em");
        System.out.println("2) Omaha hold'em");
        System.out.println("3) Seven card stud");
        System.out.println("4) Five card stud/draw");
    }

    private int getNumberOfHands(PokerGameType gameType) {
        int maxNumberOfHands = getMaximumNumberOfHands(gameType);
        while (true) {
            System.out.println("");
            System.out.print("Number of hands in simulation (2-" + maxNumberOfHands + "): ");

            String input = scanner.nextLine().trim();
            int selection;
            try {
                selection = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                continue;
            }
            if (selection < 2) {
                System.out.println("There must be atleast 2 hands");
                continue;                
            }
            if (selection > maxNumberOfHands) {
                System.out.println("There can be a maximum of " + maxNumberOfHands + " of hands");
                continue;
            }
            return selection;
        }
    }

    private int getMaximumNumberOfHands(PokerGameType gameType) {
        int maximumNumberOfHands;
        if (gameType == PokerGameType.SEVEN_STUD) {
            maximumNumberOfHands = 7;
        } else {
            maximumNumberOfHands = 10;
        }
        return maximumNumberOfHands;
    }

    private List<AbstractStartingHand> getStartingHands(int numberOfHands, PokerGameType gameType) {
        List<AbstractStartingHand> hands = new ArrayList<AbstractStartingHand>();
        
        for (int i = 0; i < numberOfHands; i++) {
            AbstractStartingHand hand = getHandInput(gameType, i+1);
            hands.add(hand);
            deadCards.addAll(hand.getCards());
        }
        
        return hands;
    }

    private AbstractStartingHand getHandInput(PokerGameType gameType, int i) {
        while (true) {
            System.out.println("");
            System.out.print("Enter cards in hand " + i + "  (example: As, Kd): ");
            String input = scanner.nextLine().trim();
            List<Card> parsedCards;
            try {
                parsedCards = TextParser.parseTextToCards(input);
            } catch (ParseException ex) {
                System.out.println("Unable to parse input");
                continue;
            }
            if (parsedCards.isEmpty()) {
                System.out.println("No cards detected in input");
                continue;
            }
            if (checkDuplicateCards(parsedCards)) {
                System.out.println("One of the entered cards belongs to another hand");
                continue;
            }
            switch (gameType) {
                case TEXAS:
                    if (parsedCards.size() > 2) {
                        System.out.println("A Texas hold'em hand can have at most two cards");
                    } else {
                        return new TexasHoldemStartingHand(parsedCards);
                    }
                    break;
                case OMAHA:
                    if (parsedCards.size() > 4) {
                        System.out.println("An Omaha hold'em hand can have at most four cards");
                    } else {
                        return new OmahaHoldemStartingHand(parsedCards);
                    }
                    break;
                case SEVEN_STUD:
                    if (parsedCards.size() > 7) {
                        System.out.println("A Seven card stud hand can have at most seven cards");
                    } else {
                        return new SevenCardStudStartingHand(parsedCards);
                    }
                    break;
                case FIVE_DRAW:
                    if (parsedCards.size() > 5) {
                        System.out.println("A Five card draw/stud hand can have at most five cards");
                    } else {
                        return new FiveCardDrawStartingHand(parsedCards);
                    }
                    break;   
                default:
                    throw new RuntimeException("Unsupported hand type!");
            }
        }
    }

    private boolean checkDuplicateCards(List<Card> parsedCards) {
        for (Card card: parsedCards) {
            if (deadCards.contains(card)) {
                return true;
            }
        }
        return false;
    }

    private FiveCardBoard getBoard() {
        FiveCardBoard board = new FiveCardBoard();
        while (true) {
            System.out.println("");
            System.out.print("Enter cards on board: (example: Ts, Th, 3c): ");
            String input = scanner.nextLine().trim();
            List<Card> parsedCards;
            try {
                parsedCards = TextParser.parseTextToCards(input);
            } catch (ParseException ex) {
                System.out.println("Unable to parse input");
                continue;
            }
            if (checkDuplicateCards(parsedCards)) {
                System.out.println("One of the entered cards belongs to a hand");
                continue;
            }            
            if (parsedCards.size() > board.getMaximumAmountOfCardsInCollection()) {
                System.out.println("The board can take a maximum of five cards");
                continue;
            }
            board.addCards(parsedCards);
            return board;
        }
    }

    private int getNumberOfSimulations() {
        while (true) {
            System.out.println("");
            System.out.println("Number of simulations (select default 10000 by pressing enter): ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) {
                return 10000;
            }            
            try {
                return Integer.parseInt(input);                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            }                
        }
    }

    private void printSelections(PokerGameType gameType, int numberOfHands, List<AbstractStartingHand> startingHands, FiveCardBoard board, int numberOfSimulations) {
        System.out.println("You selected " + gameType.getFullName() + " with " + numberOfHands + " hands");
        for (AbstractStartingHand hand : startingHands) {
            System.out.println("Hand: " + hand);
        }
        System.out.println("Board: " + board);
        System.out.println("Number of simulations: " + numberOfSimulations);
    }

    private void printResults(SimulationResult result, List<AbstractStartingHand> startingHands, 
            FiveCardBoard board, PokerGameType gameType, double timeElapsed) {
        System.out.println("");
        System.out.println("Results: ");
        System.out.println("Game: " + gameType.getFullName());
        if (board != null) {
            System.out.println("Board: " + board);
        }
        for (int i = 0; i < startingHands.size(); i++) {
            AbstractStartingHand hand = startingHands.get(i);
            System.out.println("Hand " + (i+1) + hand + " win/loss/tie % " + 
                    result.getWinPercentageForHand(hand, resultAccuracy) + "/" +
                    result.getLossPercentageForHand(hand, resultAccuracy) + "/" +
                    result.getTiePercentageForHand(hand, resultAccuracy) + "/" +                    
                    " equity: " + result.getEquityForHand(hand, resultAccuracy));
        }        
        System.out.println("");
        System.out.println("Time elapsed: " + timeElapsed + " seconds");
        System.out.println("");
        System.out.println("Press any key to return to main menu");
        scanner.nextLine();
    }
}
