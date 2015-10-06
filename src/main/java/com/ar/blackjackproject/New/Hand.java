package com.ar.blackjackproject.New;

import java.util.List;

/**
 *
 * @author Anders
 */
public interface Hand {

    /**
     * Returns an unmodifiable view of the cards in this hand.
     *
     * @return an unmodifiable view of the cards in this hand
     */
    List<Card> getViewOfHand();

    /**
     * Sets the value of the hand.
     *
     * @param valueOfHand holds the value that will be set to the value of the
     * hand.
     */
    void setValueOfHand(int valueOfHand);

    /**
     * Returns the value of the hand.
     *
     * @return the value of the hand
     */
    int getValueOfHand();

    /**
     * This will add one card to the current hand.
     *
     * @param card is recieved and added to the ArrayList that holds the cards
     * of the current hand.
     */
    void addOneCardToHand(Card card);
}
