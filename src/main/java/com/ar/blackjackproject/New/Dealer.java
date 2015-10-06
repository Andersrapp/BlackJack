package com.ar.blackjackproject.New;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class {@code Dealer} is used to set, hold and retrieve the values relevant to
 * the player. It implements the {@code Hand} interface.
 *
 * @author Anders
 */
public class Dealer implements Hand {

    private List<Card> hand = new ArrayList<>();
    private int valueOfHand;

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
    public void setValueOfHand(int valueOfHand) {
        this.valueOfHand = valueOfHand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List getViewOfHand() {
        return Collections.unmodifiableList(hand);
    }

    /**
     * This method will empty the current hand.
     */
    public void clearHand() {
        hand.clear();
    }

    public void addOneCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * This will print the cards in the dealers hand.
     */
    public void showHand() {
        System.out.println("The dealer shows his hand: ");
        for (int i = 0; i < hand.size(); ++i) {
            System.out.println(hand.get(i));
        }
    }
}
