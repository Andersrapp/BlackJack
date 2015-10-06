package com.ar.blackjackproject.New;

/**
 * The {@code Start} class is the starting point for a cool game of Black Jack.
 * The user can buy chips, bet on a round, and choose to hit or stay. Final
 * results will be printed at the end of the game.
 *
 * Note: There are many versions of Black Jack. This is a "shoegame" in which
 * the shoe normally holds 6-8 shuffled decks of cards. In this game it always
 * holds 1-2 decks of cards.
 *
 * @author Anders Rapp 2014
 */
public class Start {

    public static void main(String[] args) {

        Player player = new Player();
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJack blackJack = new BlackJack();

        if (User.wishToPlay()) {
            player.setName();
            player.buyChips(BlackJack.MINIMUM_BET, BlackJack.MAXIMUM_BET);
            deck.shuffleDeck();
            deck.addDeckToShoe();

            do {
                blackJack.playGame(player, dealer, deck);
            } while (User.continueToPlay());

            User.printFinalResults(player);
            System.out.println("Thank you for playing, " + player.getName() +"!\n"
                    + "Tell your friends and please come again!");
        }
    }
}
