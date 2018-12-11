/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecardandboard.objs;

import freecardandboard.enums.CardRank;
import freecardandboard.enums.CardSuit;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class SpanishDeck extends Deck {
    public SpanishDeck(int numDecks) {
        super(numDecks, false, false);
    }
    
    public SpanishDeck() {
        this(1);
    }
    
    @Override
    public void populateDeck() {
        CardRank[] ranks = {CardRank.TWO, CardRank.THREE, CardRank.FOUR,
            CardRank.FIVE, CardRank.SIX, CardRank.SEVEN, CardRank.EIGHT,
            CardRank.NINE, CardRank.JACK, CardRank.QUEEN, CardRank.KING,
            CardRank.ACE};
        CardSuit[] suits = {CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS,
            CardSuit.SPADES};
        
        for (int i = 0; i < super.NUM_DECKS; i++)
            for (int j = 0; j < 48; i++)
                deck.add(new Card(ranks[i/4], suits[i%4]));
    }
}
