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
package com.github.strangercoug.freeboardandcard.games.card;

import java.util.ArrayList;

import com.github.strangercoug.freeboardandcard.Player;
import com.github.strangercoug.freeboardandcard.enums.CardSuit;
import com.github.strangercoug.freeboardandcard.objs.Card;
import com.github.strangercoug.freeboardandcard.objs.Deck;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class NinetyNine extends CardGame {
	int[] scores, tricksBid, tricksWon;
	private CardSuit currentTrump; // for no-trump, this is null
	private enum PremiumBid{DECLARE, REVEAL};
	private PremiumBid premiumBid;
	private Player premiumBidder;

	public NinetyNine() {
		minPlayers = 3;
		maxPlayers = 5;
	}

	@Override
	public void init(ArrayList<Player> players) {
		assert players.size() >= minPlayers && players.size() <= maxPlayers
				: "Wrong number of players.";

		this.players = players;
		this.gameWon = false;

		/* TODO: This works correctly only for four players. The game should
		 * strip the deuces through fives for a three-player game and add the
		 * elevens and twelves for a five-player game.
		 */
		deck = new Deck(1, false, false);

		players.forEach((_item) -> {
			hands.add(new ArrayList<Card>());
		});

		scores = new int[players.size()];
		tricksBid = new int[players.size()];
		tricksWon = new int[players.size()];
	}

	@Override
	public void play() {
		deck.populateDeck();
		deck.shuffleDeck();
	}

	/**
	 * Deals out the entire deck of cards to each player.
	 * 
	 * @param dealerPos the position of the current dealer
	 */

	public void dealCards(int dealerPos) {
		deck.shuffleDeck();
		 
		for (int i = dealerPos + 1; !deck.isEmpty(); i++) {
			hands.get(i).add(deck.dealCard());
		}
	}

	private int getBidValue(CardSuit suit) {
		switch(suit) {
			case DIAMONDS: return 0;
			case SPADES: return 1;
			case HEARTS: return 2;
			case CLUBS: return 3;
			default: throw new IllegalArgumentException("Suit cannot be null.");
		}
	}

	/**
	 * Determines which card won the trick.
	 * 
	 * @param cards  the cards played in order
	 * @param trump  the trump suit
	 * @return the highest trump if a card was played, the highest card of the
	 * suit led otherwise
	 */

	public Card findWinningCard(Card[] cards, CardSuit trump) {
		boolean trumpFound = cards[0].getSuit().equals(trump);
		int winningCardIndex = 0;

		for (int i = 1; i < cards.length; i++) {
			if (trump != null) {
				if (!trumpFound && cards[i].getSuit().equals(trump)) {
					trumpFound = true;
					winningCardIndex = i;
					continue;
				}
			}   

			if (!cards[i].getSuit().equals(cards[winningCardIndex].getSuit()))
				continue;

			if (cards[i].outranks(cards[winningCardIndex]))
				winningCardIndex = i;
		}

		return cards[winningCardIndex];
	}

	/**
	 * Tabulates the results of the hand. Currently does not support
	 * declarations or revelations.
	 */
	public void scoreGame() {
		boolean[] madeBid = new boolean[players.size()];
		byte numWinners = 0;
		byte winBonus;

		for (int i = 0; i < players.size(); i++) {
			scores[i] += tricksWon[i];
			madeBid[i] = tricksWon[i] == tricksBid[i];

			if (madeBid[i]) {
				numWinners++;
			}
		}

		switch (numWinners) {
			case 0: currentTrump = CardSuit.DIAMONDS;
				break;
			case 1: currentTrump = CardSuit.SPADES;
				break;
			case 2: currentTrump = CardSuit.HEARTS;
				break;
			case 3: currentTrump = CardSuit.CLUBS;
				break;
			default: currentTrump = null;
		}

		if (numWinners == 0) {
			return; // since there's no point in continuing
		}

		if (players.size() == 5) {
			winBonus = (byte) ((6 - numWinners) * 10);
		}

		else {
			if (numWinners == 4) {
				winBonus = 0;
			}

			else winBonus = (byte) ((4 - numWinners) * 10);
		}

		for (int i = 0; i < players.size(); i++) {
			if (madeBid[i]) {
				scores[i] += winBonus;
			}
		}
	}

	public boolean isThereAWinner() {
		for (int i = 0; i < scores.length; i++) {
			if (scores[i] >= 100) {
				return true;
			}
		}

		return false;
	}

	public Player getFirstPlacePlayer() {
		Player leader = players.get(0);

		for (int i = 1; i < players.size(); i++) {
			if (scores[i] > scores[players.indexOf(leader)])
				leader = players.get(i);
		}

		return leader;
	}
}
