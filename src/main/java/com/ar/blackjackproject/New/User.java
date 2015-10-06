package com.ar.blackjackproject.New;

import java.io.Console;

/**
 * This is a helper class for printing menues and getting user input.
 *
 * @author Anders
 */
public class User {

    private static final Console c = System.console();

    /**
     * Asks the user to choose whether to play the game or not.<p>
     */
    public static boolean wishToPlay() {
        boolean choiceAccepted = false;
        boolean answer = false;
        int choice;
        String question = "Would you like to play a game of Black Jack?"
                + "\n1. Yes\t2. No\n";
        String errorMessage = "Please, enter  1  for Yes or  2  for No";

        while (!choiceAccepted) {
            choice = getNumericInput(question, errorMessage);

            switch (choice) {
                case 1:
                    choiceAccepted = true;
                    answer = true;
                    break;
                case 2:
                    System.out.println("Maybe some other time, eh!?");
                    choiceAccepted = true;
                    answer = false;
                    break;
                default:
                    choiceAccepted = false;
                    System.out.println(errorMessage);
                    break;
            }
        }
        return answer;
    }

    /**
     * Asks the user to choose whether to play another round or not.<p>
     */
    public static boolean continueToPlay() {
        boolean choiceAccepted = false;
        boolean answer = false;
        String question = "Would you like to play another round?"
                + "\n1. Yes\t2. No\n";
        String errorMessage = "Please, enter  1  for Yes or  2  for No";
        int choice;

        while (!choiceAccepted) {
            choice = getNumericInput(question, errorMessage);

            switch (choice) {
                case 1:
                    choiceAccepted = true;
                    answer = true;
                    break;
                case 2:
                    choiceAccepted = true;
                    answer = false;
                    break;
                default:
                    System.out.println(errorMessage);
                    choiceAccepted = false;
                    break;
            }
        }
        return answer;
    }

    /**
     * Gives the menu for the user just before a new round is started.<p>
     * @param player is an instance of class {@code Player}
     */
    public static void getMenuChoice(Player player) {
        boolean startRound = false;
        String question = "You need at least " + BlackJack.MINIMUM_BET + "$ to start round."
                + "\nWould you like to: \n1.Buy more chips!\t2. Count your chips\t3.Start round\t4. Quit\n";
        String errorMessage = "Please, enter 1, 2 or 3";

        while (!startRound) {
            int input = getNumericInput(question, errorMessage);
            switch (input) {
                case 1:
                    player.buyChips(BlackJack.MINIMUM_BET, BlackJack.MAXIMUM_BET);
                    break;
                case 2:
                    System.out.println("You have " + player.getSumOfChips());
                    break;
                case 3:
                    startRound = true;
                    break;
                case 4:
                    User.printFinalResults(player);
                    System.exit(0);
            }
        }
    }

    /**
     * Will print the overall results of the game that was played.<p>
     *
     * @param player is an instance of class {@code Player}
     *
     */
    public static void printFinalResults(Player player) {

        System.out.println("You bought chips for " + player.getTotalBuyIn());
        System.out.println("You left the table with a total amount of "
                + player.getSumOfChips());
        if (player.getTotalBuyIn() > player.getSumOfChips()) {
            System.out.println("You lost a total amount of "
                    + (player.getTotalBuyIn() - player.getSumOfChips()) + "!");
        } else if (player.getTotalBuyIn() < player.getSumOfChips()) {
            System.out.println("You won a total amount of "
                    + (player.getSumOfChips() - player.getTotalBuyIn() + "!"));
        } else {
            System.out.println("You broke even!");
        }
    }

    /**
     * Asks user for numeric input.<p>
     * @param question prompts the user for a value
     * @param errorMessage will be printed if no, or incorrect value is
     * provided.
     *
     * @return what the user typed
     */
    public static int getNumericInput(String question, String errorMessage) {
        int input = 0;
        for (;;) {
            try {
                input = Integer.parseInt(c.readLine(question));
                return input;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    /**
     * Asks user for string input.<p>
     * @param question prompts the user for a value
     * @param errorMessage will be printed if no, or incorrect value is
     * provided.
     * @param pattern will be matched with user input to control it's value
     * @param minLength specifies the minimum length of input.
     * @param maxLength specifies the maximum length of input.
     *
     * @return what the user typed
     */
    public static String getStringInput(String question, String errorMessage,
            String pattern, int minLength, int maxLength) {
        boolean correctInput = false;
        String input = "";

        while (!correctInput) {
            input = c.readLine(question).trim();
            if (input.matches(pattern) && input.length() >= minLength && input.length() <= maxLength) {
                correctInput = true;
            } else {
                System.out.println(errorMessage);
                correctInput = false;
            }
        }
        return input;
    }
}
