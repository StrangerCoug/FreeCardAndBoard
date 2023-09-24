package com.github.strangercoug.freecardandboard.objs;

import com.github.strangercoug.freecardandboard.enums.CardRank;
import com.github.strangercoug.freecardandboard.enums.CardSuit;
import com.github.strangercoug.freecardandboard.objs.Card;
import com.github.strangercoug.freecardandboard.objs.Dice;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {
    private static LinkedList<Card> cards;

    @BeforeAll
    static void setCards() {
        cards = new LinkedList<>();
        CardRank[] ranks = new CardRank[]{CardRank.TWO, CardRank.THREE, CardRank.FOUR, CardRank.FIVE, CardRank.SIX,
                CardRank.SEVEN, CardRank.EIGHT, CardRank.NINE, CardRank.TEN, CardRank.ELEVEN, CardRank.TWELVE,
                CardRank.THIRTEEN, CardRank.JACK, CardRank.QUEEN, CardRank.KING, CardRank.ACE, CardRank.JOKER, CardRank.JOKER};
        CardSuit[] suits = new CardSuit[]{CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES, CardSuit.CLUBS,
                CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES, CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS,
                CardSuit.SPADES, CardSuit.CLUBS, CardSuit.DIAMONDS, CardSuit.HEARTS, CardSuit.SPADES, CardSuit.BLACK,
		        CardSuit.RED};
        for (int i = 0; i < ranks.length; i++) {
            cards.add(new Card(ranks[i], suits[i]));
        }
    }

    @Test
    void testOutranksDifferentRank() {
        Card card1 = new Card(CardRank.TWO, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.outranks(card1), equalTo(true));
    }

    @Test
    void testOutranksDifferentSuit() {
        Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);

        // even though card2.compareTo(card1) > 0
        assertThat(card2.outranks(card1), equalTo(false));
    }

    @Test
    void testOutranksNull() {
        Card card = new Card(CardRank.ACE, CardSuit.CLUBS);
        assertThrows(NullPointerException.class, () -> card.outranks(null));
    }

    @Test
    void testEqualsAndHashCode() {
        Card card1 = new Card(CardRank.ACE, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card1.equals(card2), equalTo(true));
        assertThat(card1.hashCode(), equalTo(card2.hashCode()));
    }

    @Test
    void testEqualsAndHashCodeDifferentRank() {
        Card card1 = new Card(CardRank.TWO, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card1.equals(card2), equalTo(false));
        assertThat(card1.hashCode(), not(equalTo(card2.hashCode())));
    }

    @Test
    void testEqualsAndHashCodeDifferentSuit() {
        Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card1.equals(card2), equalTo(false));
        assertThat(card1.hashCode(), not(equalTo(card2.hashCode())));
    }

    @Test
    void testEqualityWithNull() {
        Card card = new Card(CardRank.ACE, CardSuit.SPADES);
        Object nullObject = null;
        assertThat(card.equals(nullObject), equalTo(false));
    }

    @Test
    void testEqualityWithInconvertibleType() {
        Card card = new Card(CardRank.ACE, CardSuit.SPADES);
        Dice dice = new Dice();
        assertThat(card.equals(dice), equalTo(false));
    }

    @Test
    void testCompareToSameCard() {
        Card card1 = new Card(CardRank.ACE, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card1.compareTo(card2), equalTo(0));
    }

    @Test
    void testCompareToDifferentRank() {
        Card card1 = new Card(CardRank.TWO, CardSuit.SPADES);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.compareTo(card1), greaterThan(0));
    }


    @Test
    void testCompareToDifferentSuit() {
        Card card1 = new Card(CardRank.ACE, CardSuit.CLUBS);
        Card card2 = new Card(CardRank.ACE, CardSuit.SPADES);
        assertThat(card2.compareTo(card1), greaterThan(0));
    }

    @Test
    void testCompareToNull() {
        Card card = new Card(CardRank.ACE, CardSuit.CLUBS);
        assertThrows(NullPointerException.class, () -> card.compareTo(null));
    }

    @Test
    void testToString() {
        String[] expectedValues = new String[]{"Two of Clubs", "Three of Diamonds", "Four of Hearts", "Five of Spades",
                "Six of Clubs", "Seven of Diamonds", "Eight of Hearts", "Nine of Spades", "Ten of Clubs", "Eleven of Diamonds",
                "Twelve of Hearts", "Thirteen of Spades", "Jack of Clubs", "Queen of Diamonds", "King of Hearts",
		        "Ace of Spades", "Black Joker", "Red Joker"};
        for (int i = 0; i < expectedValues.length; i++) {
            assertThat(cards.get(i).toString(), equalTo(expectedValues[i]));
        }
    }
}