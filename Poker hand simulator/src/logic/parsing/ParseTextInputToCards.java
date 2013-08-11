package logic.parsing;

import card.Card;
import card.Rank;
import card.Suit;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Parses text input of cards to a list of card-objects.
 * 
 * @todo Implement text parsing.
 * @author Sebastian Björkqvist
 */
public class ParseTextInputToCards {

    /**
     * Parses string to cards.
     * 
     * One card must be represented by a string of length two, where
     * the first capital letter gives the rank, and the second letter
     * gives the suit. Cards must be separated by a comma.
     * 
     * Examples of valid strings for a card:
     * Ad - Ace of diamonds
     * Ts - Ten of spades
     * Kh - King of hearts
     * Qc - Queen of clubs
     * 5d - Five of diamonds
     * 
     * Inputting the string "Ad,Ts,Kh,Qc,5d" would return a list
     * with the five cards specified above. The string
     * "Ad, Ts, Kh, Qc, 5d" also works.
     * 
     * @param input String with cards
     * @return List of cards
     * @throws ParseException if the string can't be parsed.
     */
    public List<Card> parseTextToCards(String input) throws ParseException {
        List<Card> cards = new ArrayList<Card>();
        Map<String, Rank> stringToRankMap = Rank.getStringToRankMap();
        Map<String, Suit> stringToSuitMap = Suit.getStringToSuitMap();
        
        String[] cardStrings = input.split(",");
        
        for (int i = 0; i < cardStrings.length; i++) {
            String cardString = cardStrings[i].trim();
            if (cardString.length() != 2) {
                throw new ParseException("Cardstring " + i + " has a length other than two.", i);
            }
            Rank rank = stringToRankMap.get(Character.toString(cardString.charAt(0)));
            Suit suit = stringToSuitMap.get(Character.toString(cardString.charAt(1))); 
            if (rank == null) {
                throw new ParseException("Invalid rank in cardstring " + i, i);
            }
            if (suit == null) {
                throw new ParseException("Invalid suit in cardstring " + i, i);                
            }
            Card card = new Card(suit, rank);
            
            cards.add(card);
        }
        
        return cards;
    }
    
}
