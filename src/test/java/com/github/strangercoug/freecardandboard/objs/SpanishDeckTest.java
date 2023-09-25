package com.github.strangercoug.freecardandboard.objs;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

class SpanishDeckTest {
	SpanishDeck deck;
	final int deckSize = 48;
	final int numDecks = 2;

	@Test
	void testPopulateDeckSingle() {
		deck = new SpanishDeck();
		deck.populateDeck();
		Card[] testCard = new Card[2];
		testCard[0] = deck.dealCard();

		for (int i = 0; i < deckSize - 1; i++) {
			testCard[1] = testCard[0];
			testCard[0] = deck.dealCard();
			assertThat(testCard[0].compareTo(testCard[1]), greaterThan(0));
		}
		assert deck.isEmpty();
	}

	@Test
	void testPopulateDeckDouble() {
		deck = new SpanishDeck(numDecks);
		deck.populateDeck();
		Card[][] testCard = new Card[numDecks][deckSize];

		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < deckSize; j++) {
				testCard[i][j] = deck.dealCard();
			}
		}
		assert deck.isEmpty();

		for (int i = 0; i < deckSize; i++) {
			assertThat(testCard[0][i].equals(testCard[1][i]), equalTo(true));
		}
	}
}
