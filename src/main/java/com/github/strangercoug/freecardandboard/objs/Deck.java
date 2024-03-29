/*
 * Copyright (c) 2018-2023, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted (subject to the limitations in the disclaimer
 * below) provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY
 * THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.strangercoug.freecardandboard.objs;

import java.security.SecureRandom;
import java.util.LinkedList;

import com.github.strangercoug.freecardandboard.enums.CardRank;
import com.github.strangercoug.freecardandboard.enums.CardSuit;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Deck {
	protected LinkedList<Card> cards;
	protected final int numDecks;
	private final boolean usesBlackJoker;
	private final boolean usesRedJoker;
	private final SecureRandom rng = new SecureRandom();

	public Deck(int numDecks, boolean usesBlackJoker, boolean usesRedJoker) {
		cards = new LinkedList<>();
		this.numDecks = numDecks;
		this.usesBlackJoker = usesBlackJoker;
		this.usesRedJoker = usesRedJoker;
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

		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < ranks.length * suits.length; j++)
				cards.add(new Card(ranks[j%ranks.length], suits[j/ranks.length]));

			if (usesBlackJoker)
				cards.add(new Card(CardRank.JOKER, CardSuit.BLACK));

			if (usesRedJoker)
				cards.add(new Card(CardRank.JOKER, CardSuit.RED));
		}
	}

	public void shuffleDeck() {
		/* TODO: This is fine for alpha and beta testing, but at a later point I would
		 * like to be able to detect whether there is an Internet connection and use
		 * the random.org API to shuffle if possible. If something goes wrong, we fall
		 * back to this.
		 */
		for (int i = cards.size() - 1; i > 0; i--) {
			Card temp = cards.get(i);
			int j = rng.nextInt(i + 1); /* Without the +1 this becomes a Sattolo
			                             * shuffle, which we don't want */
			cards.set(i, cards.get(j));
			cards.set(j, temp);
		}
	}

	public Card dealCard() {
		return cards.pop();
	}

	public boolean isEmpty() {
		return cards.isEmpty();
	}
}
