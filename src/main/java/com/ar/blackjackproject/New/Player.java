package com.ar.blackjackproject.New;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class {@code Player} is used to set, hold and retrieve the values relevant to
 * the player. It implements the {@code Hand} interface.
 *
 * @author Anders
 */
public class Player implements Hand {

    private static final Console c = System.console();
    private final List<Card> hand = new ArrayList<>();
    private String name;
    private int sumOfChips;
    private int totalBuyIn;
    private int valueOfHand;
    private int roundBet;

    /**
     * {@inheritDoc}
     */
    @Override
    public List getViewOfHand() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueOfHand(int valueOfHand) {
        this.valueOfHand = valueOfHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getValueOfHand() {
        return valueOfHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOneCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * This method clears the hand of all the cards.
     */
    public void clearHand() {
        hand.clear();
    }

    /**
     * Returns the value of the roundBet.
     *
     * @return the value of the roundBet
     */
    public int getRoundBet() {
        return roundBet;
    }

    /**
     * Sets the value of roundBet.
     *
     * @param roundBet is the value that will be set.
     */
    public void setRoundBet(int roundBet) {
        this.roundBet = roundBet;
    }

    /**
     * Sets the value of sumOfChips.
     *
     * @param sumOfChips is the value that will be set.
     */
    public void addChips(int sumOfChips) {
        this.sumOfChips += sumOfChips;
    }

    /**
     * Returns the value of sumOfChips.
     *
     * @return the value of sumOfChips
     */
    public int getSumOfChips() {
        return sumOfChips;
    }

    /**
     * Adds the value of the chips to the value of totalBuyIn.
     *
     * @param newChips is added to the value of totalBuyIn
     */
    public void setTotalBuyIn(int newChips) {
        this.totalBuyIn += newChips;
    }

    /**
     * Returns the value of totalBuyIn.
     *
     * @return the value of totalBuyIn
     */
    public int getTotalBuyIn() {
        return totalBuyIn;
    }

    /**
     * Is used to ask the user to enter a name.<p>
     *
     * The name must contain only letters and the length must be within a
     * specified range.
     *
     */
    public void setName() {
        String question = "Please enter your name:\n";
        String pattern = "[a-öA-Ö]*";
        String errorMessage = "Please provide proper input,"
                + " using 3 - 16 letters!";
        int maxLength = 16;
        int minLength = 3;

        name = User.getStringInput(question, errorMessage, pattern, minLength, maxLength);
    }

    /**
     * Returns the players name.
     *
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * Asks the buyer to buy chips to be able to bet and play.
     *
     * @param minBet specifies the minimum bet that is required in each round.
     * @param maxBet specifies the maximum bet that is required in each round.
     */
    public void buyChips(int minBet, int maxBet) {
        boolean buyInAccepted = false;
        String question = "How much money would you "
                + "like to spend on chips?\n";
        String errorMessage = "Please, enter a numeric value "
                + "between 0 and 100000";
        int buyIn;

        while (!buyInAccepted) {

            buyIn = User.getNumericInput(question, errorMessage);
            if ((buyIn + getSumOfChips()) < minBet) {
                System.out.println("Too low! Please give a higher figure!");
            } else if (buyIn > 100000) {
                System.out.println("You don't need that much money. "
                        + "Maximum bet is $" + maxBet);
            } else {
                System.out.println("");
                totalBuyIn = sumOfChips += buyIn;
                buyInAccepted = true;
            }
        }
    }

    /**
     * Asks the player for a new bet within the specified range.
     *
     * @param min specifies the minimum bet that is required in each round.
     * @param max specifies the maximum bet that is required in each round.
     */
    public void bet(int min, int max) {
        int bet = 0;
        boolean betAccepted = false;

        String question = "How much would you like to bet?\n";
        String errorMessage = "Please, enter a whole number from 0 - 500\n";

        while (!betAccepted) {

            bet = User.getNumericInput(question, errorMessage);

            if (bet <= 0) {
                System.out.println("Haha, very funny! Place a REAL bet!\n");
            } else if (bet > sumOfChips) {
                System.out.println("You're out of chips! "
                        + "\nPlease, place new bet!\n");
            } else if (bet < min) {
                System.out.printf("Your bet is too low! Minimum bet is %d "
                        + "and maximum bet is %d!\n", min, max);
            } else if (bet > max) {
                System.out.printf("Your bet is too high! Minimum bet is %d"
                        + "and maximum bet is %d!\n", min, max);
            } else {
                System.out.println("You have bet $" + bet + "\n");
                sumOfChips -= bet;
                betAccepted = true;
            }
        }
        roundBet = bet;
    }

    /**
     * Asks player if he wishes to hit or stay. Returns the value of players
     * choice as long as it's 1 or 2.
     *
     * @return the value of players choice as long as it's 1 or 2.
     */
    public int hitOrStay() {

        boolean choiceAccepted = false;

        String question = "Would you like to? \n1. Hit!\t2. Stay!\n";
        String errorMessage = "Please, enter  1  for Hit! or  2  for Stay!";
        int choice = 0;
        while (!choiceAccepted) {
            choice = User.getNumericInput(question, errorMessage);

            if (choice == 1 || choice == 2) {
                choiceAccepted = true;
            }
        }
        return choice;
    }
}
