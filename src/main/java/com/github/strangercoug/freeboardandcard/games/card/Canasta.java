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
package com.github.strangercoug.freeboardandcard.games.card;

import java.util.ArrayList;
import java.util.List;

import com.github.strangercoug.freeboardandcard.Player;
import com.github.strangercoug.freeboardandcard.enums.CardRank;
import com.github.strangercoug.freeboardandcard.enums.CardSuit;
import com.github.strangercoug.freeboardandcard.objs.Card;
import com.github.strangercoug.freeboardandcard.objs.Deck;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Canasta extends CardGame {
	public Canasta() {
		minPlayers = 2;
		maxPlayers = 6;
	}

	@Override
	public void init(List<Player> players) {
		if (players.size() < minPlayers || players.size() > maxPlayers
				|| players.size() == 5) {
			throw new IllegalArgumentException("Wrong number of players.");
		}

		this.players = players;
		this.gameWon = false;

		if (players.size() == 6) {
			deck = new Deck(3, true, true);
		} else {
			deck = new Deck(2, true, true);
		}
		players.forEach(_item -> hands.add(new ArrayList<>()));
	}

	@Override
	public void play() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private boolean isWildCard (Card card) {
		return card.getRank() == CardRank.TWO
				|| card.getRank() == CardRank.JOKER;
	}

	private int getPointValue (Card card) {
		switch (card.getRank()) {
			case ACE, TWO -> {
				return 20;
			}
			case THREE -> {
				return card.getSuit().isRed() ? 100 : 5;
			}
			case FOUR, FIVE, SIX, SEVEN -> {
				return 5;
			}
			case EIGHT, NINE, TEN, JACK, QUEEN, KING -> {
				return 10;
			}
			case JOKER -> {
				return 50;
			}
		}
	}
}