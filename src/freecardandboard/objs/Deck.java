/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecardandboard.objs;

import freecardandboard.enums.CardRank;
import freecardandboard.enums.CardSuit;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Deck {
    protected LinkedList<Card> deck;
    protected final int NUM_DECKS;
    private final boolean USES_BLACK_JOKER;
    private final boolean USES_RED_JOKER;
    
    public Deck(int numDecks, boolean usesBlackJoker, boolean usesRedJoker) {
        deck = new LinkedList<>();
        NUM_DECKS = numDecks;
        USES_BLACK_JOKER = usesBlackJoker;
        USES_RED_JOKER = usesRedJoker;
    }

    public Deck(int numDecks) {
        this(numDecks, false, false);
    }
    
    public Deck() {
        this(1, false, false);
    }
    
    public void populateDeck() {
        CardRank[] ranks = {CardRank.TWO, CardRank.THREE, CardRank.FOUR,
            CardRank.FIVE, CardRank.SIX, CardRank.SEVEN, CardRank.EIGHT,
            CardRank.NINE, CardRank.TEN, CardRank.JACK, CardRank.QUEEN,
            CardRank.KING, CardRank.ACE};
        CardSuit[] suits = {CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS,
            CardSuit.SPADES};
        
        for (int i = 0; i < NUM_DECKS; i++) {
            for (int j = 0; j < 52; i++)
                deck.add(new Card(ranks[i/4], suits[i%4]));
            
            if (USES_BLACK_JOKER)
                deck.add(new Card(CardRank.JOKER, CardSuit.BLACK));
            
            if (USES_RED_JOKER)
                deck.add(new Card(CardRank.JOKER, CardSuit.RED));
        }
    }
    
    /* TODO: This is fine for alpha and beta testing, but at a later point I
     * would like to be able to detect whether there is an Internet connection
     * and use the random.org API to shuffle if possible. If something goes
     * wrong, we fall back to this.
     */
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }
    
    public Card dealCard() {
        return deck.pop();
    }
}
