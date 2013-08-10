package card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a standard 52-card deck.
 *
 * The deck contains one of each card in a standard 52-card deck.
 *
 * @author Sebastian Björkqvist
 */
public final class CardDeckStandard implements ICardDeck {

    private LinkedList<Card> cards;

    /**
     * Creates a new standard card deck.
     *
     * The cards are automatically shuffled.
     */
    public CardDeckStandard() {
        this(true);
    }

    /**
     * Creates a new standard card deck
     *
     * @param shuffleDeck If true, deck is shuffled
     */
    public CardDeckStandard(boolean shuffleDeck) {
        cards = new LinkedList<Card>();
        addAllCards();
        if (shuffleDeck) {
            shuffle();
        }
    }

    /**
     * Creates a new standard card deck with some cards removed.
     *
     * @param removedCards List of removed cards.
     * @param shuffleDeck Shuffle the deck
     * @throws IllegalArgumentException if removedCards is null
     */
    public CardDeckStandard(List<Card> removedCards, boolean shuffleDeck) {
        this(false);
        if (removedCards == null) {
            throw new IllegalArgumentException("removedCards is null");
        }
        cards.removeAll(removedCards);
        if (shuffleDeck) {
            shuffle();
        }
    }
    /**
     * Creates a new standard card deck with some cards removed.
     *
     * @param removedCards List of removed cards.
     * @throws IllegalArgumentException if removedCards is null
     */
    public CardDeckStandard(List<Card> removedCards) {
        this(removedCards, true);
    }

    /**
     * Adds all 52 cards to deck.
     *
     */
    private void addAllCards() {
        List<Rank> allRanks = Rank.getAllRanks();
        List<Suit> allSuits = Suit.getAllSuits();

        for (Rank r : allRanks) {
            for (Suit s : allSuits) {
                cards.add(new Card(s, r));
            }
        }
    }

    @Override
    public Card getCard() {
        return cards.poll();
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public int getNumberOfRemainingCards() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
