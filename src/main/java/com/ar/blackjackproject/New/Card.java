package com.ar.blackjackproject.New;

/**
 * Class {@code Card} specifies the relevant variables of a card in a deck of
 * cards.
 *
 * @author Anders
 */
public class Card {

    private final String face;
    private final String suit;
    private final int value;

    /**
     * A constructor for Card
     *
     * @param face holds the face value of each card
     * @param suit holds the different suits
     * @param value holds the actual value of the card that is used in a game of
     * Black Jack.
     */
    public Card(String face, String suit, int value) {
        this.face = face;
        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns the face of the card.
     *
     * @return the face of the card
     */
    public String getFace() {
        return face;
    }

    /**
     * Returns the suit of the card.
     *
     * @return the suit of the card
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Returns the value of the card used in the game.
     *
     * @return the actual value of the card used in the game
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the card as "face" of "suit.
     *
     * @return the card as "face" of "suit"
     */
    @Override
    public String toString() {

        return new StringBuilder().append(face).append(" of ")
                .append(suit).toString();
    }
}
