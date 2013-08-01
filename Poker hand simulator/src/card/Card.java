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

    /**
     * hashCode-method for Card.
     * 
     * Generated by NetBeans.
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.suit != null ? this.suit.hashCode() : 0);
        hash = 71 * hash + (this.rank != null ? this.rank.hashCode() : 0);
        return hash;
    }

    
    /**
     * equals-method for Card.
     * 
     * Generated by NetBeans.
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }
}
