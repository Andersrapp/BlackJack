package com.ar.blackjackproject.New;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The {@code BlackJack} class holds the logic required to play an actual round
 * of Black Jack.
 *
 * @author Anders
 */
public class BlackJack {

    private static final Console c = System.console();
    /**
     * Constants defining minimum and maximum bet.
     */
    public static final int MINIMUM_BET = 5;
    public static final int MAXIMUM_BET = 500;

    /**
     * This method is used to play one complete round in the game.
     *
     * Player must have sufficient chips to play and he must also place a bet
     * within a certain range. Then the first cards are dealt and the value of
     * players hand is presented. He chooses to hit or stay a number of times
     * and may end up going bust. Dealer then shows his hand and continues to
     * play while the value of the hand is below 17 or until he is bust. The
     * hands are compared and an outcome is determined. Finally, all values are
     * cleared for a new round.
     *
     * @param player is used to bet, play, evaluate hand and determine outcome
     * @param dealer is used to play, evaluate hand and determine outcome.
     * @param deck is used to get cards.
     */
    public void playGame(Player player, Dealer dealer, Deck deck) {
        boolean playerStays = false;
        boolean playerIsBust = false;
        boolean dealerIsBust = false;

        do {
            User.getMenuChoice(player);
        } while (player.getSumOfChips() < MINIMUM_BET);

        player.bet(MINIMUM_BET, MAXIMUM_BET);

        createInitialHand(player, dealer, deck);
        setValueOfCurrentHand(player);

        System.out.println("\nDealers first card is shown: "
                + dealer.getViewOfHand().get(0));

        while (!playerIsBust && !playerStays) {
            switch (player.hitOrStay()) {
                case 1:
                    System.out.println("You ask dealer for hit!");
                    hit(player, "You", deck);
                    setValueOfCurrentHand(player);
                    playerIsBust = player.getValueOfHand() > 21;
                    break;
                case 2:
                    playerStays = true;
                    break;
            }
        }

        if (playerIsBust) {
            System.out.println("\nYou're bust!");
        }

        dealer.showHand();
        setValueOfCurrentHand(dealer);

        while (!playerIsBust && dealer.getValueOfHand() < 17) {
            hit(dealer, "Dealer", deck);
            setValueOfCurrentHand(dealer);
            dealerIsBust = dealer.getValueOfHand() > 21;
        }

        if (dealerIsBust) {
            System.out.println("\nThe dealer is bust! "
                    + "\nYou win " + (player.getRoundBet() * 2) + "!");
            player.addChips(player.getRoundBet() * 2);
        } else if (player.getValueOfHand() == dealer.getValueOfHand()) {
            player.addChips(player.getRoundBet());
            System.out.println("\nThe hand is a push. No one wins!");
        } else if (!playerIsBust && !dealerIsBust && player.getValueOfHand() > dealer.getValueOfHand()) {
            System.out.println("\nYou win "
                    + (player.getRoundBet() * 2) + "!");
            player.addChips(player.getRoundBet() * 2);
        } else {
            System.out.println("\nYou lose!");
        }

        player.setRoundBet(0);

        if (deck.getShoe().size() < 52) {
            deck.shuffleDeck();
            deck.addDeckToShoe();
        }

        player.clearHand();
        dealer.clearHand();
        System.out.println("\n\nThe round is over!");
    }

    /**
     * Creates the initial hand of two cards for both player and dealer.
     *
     * @param player is used to add cards to players hand.
     * @param dealer is used to add cards to dealers hand.
     * @param deck is used to retrieve cards from the shoe.
     */
    private void createInitialHand(Player player, Dealer dealer, Deck deck) {

        while (dealer.getViewOfHand().size() < 2) {
            Card card = dealOneCard(deck.getShoe());
            player.addOneCardToHand(card);
            card = dealOneCard(deck.getShoe());
            dealer.addOneCardToHand(card);
            if (dealer.getViewOfHand().size() == 1) {
                System.out.println("Your first card is "
                        + player.getViewOfHand().get(0));
            }
            if (dealer.getViewOfHand().size() == 2) {
                System.out.println("Your second card is "
                        + player.getViewOfHand().get(1));
            }
        }
    }

    /**
     * Sets the value of the current hand.
     *
     * This method caclculates and sets the value of the current hand while
     * compensating for any aces if that value is over 21.
     *
     * @param h is used to retrieve a view of the current hand and also to set
     * the hands value.
     */
    public void setValueOfCurrentHand(Hand h) {
        List<Card> hand = h.getViewOfHand();
        int numberOfAces = numberOfAces(hand);
        int valueOfHand = 0;

        for (int i = 0; i < hand.size(); ++i) {
            valueOfHand += hand.get(i).getValue();
        }

        while (valueOfHand > 21 && numberOfAces != 0) {
            valueOfHand -= 10;
            numberOfAces--;
        }

        h.setValueOfHand(valueOfHand);
        System.out.println("Total value of hand is " + valueOfHand);
    }

    /**
     * This will count the number of aces in the current hand.
     *
     * @param hand is used to check the values of the face of the cards.
     * @return
     */
    public int numberOfAces(List<Card> hand) {

//        List<Card> aces = hand.stream().filter((card) -> (card.getFace().equals("Ace"))).collect(Collectors.toList());
//        return aces.size();
//        int numberOfAces = (int) hand.stream().filter((card) -> (card.getFace().equals("Ace"))).count();
        return (int) hand.stream().filter((card) -> (card.getFace().equals("Ace"))).count();
    }

    /**
     * This method will add a {@code Card} that is retrieved from the shoe to
     * the current hand and print the result.
     *
     * @param h refers to an instance of the Hand interface which is implemented
     * by class {@code Player} and class {@code Dealer}.
     * @param name is used to print the name of the current user.
     * @param deck is used to retrieve a card from the shoe.
     */
    public void hit(Hand h, String name, Deck deck) {

        Card card = dealOneCard(deck.getShoe());
        System.out.println(name + " get a " + card);
        h.addOneCardToHand(card);
    }

    /**
     * This method will get one card from the shoe.
     *
     * @param shoe is the holder of decks of cards from which the card is
     * retrieved.
     * @return a card from the shoe.
     */
    public Card dealOneCard(List<Card> shoe) {
        Card card = (shoe.get(0));
        shoe.remove(0);
        return card;
    }
}
