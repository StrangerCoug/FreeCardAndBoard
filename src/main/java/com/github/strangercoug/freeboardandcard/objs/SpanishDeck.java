/*
 * Copyright (c) 2018-2021, Jeffrey Hope
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
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
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.strangercoug.freeboardandcard.objs;

import com.github.strangercoug.freeboardandcard.enums.CardRank;
import com.github.strangercoug.freeboardandcard.enums.CardSuit;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
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
			for (int j = 0; j < ranks.length * suits.length; j++)
				deck.add(new Card(ranks[i/4], suits[i%4]));
	}
}
