package card;

/**
 * Represents a card in a standard 52-card deck.
 * 
 * @author Sebastian Björkqvist
 */
public class Card implements Comparable<Card> {

    private final Suit suit;   
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    @Override
    public String toString() {
        return suit.getShortName() + " " + rank.getShortName();
        
    }

    /**
     * Compares two cards by their rank.
     * 
     * Cards will be sorted in ascending order by rank.
     * 
     * @param o Card
     * @return this.getRank().getValue - o.getRank().getValue()
     */    
    @Override
    public int compareTo(Card o) {
        return rank.getValue() - o.getRank().getValue();
    }

    
}
