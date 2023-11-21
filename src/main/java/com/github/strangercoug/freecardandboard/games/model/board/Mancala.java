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
package com.github.strangercoug.freecardandboard.games.model.board;

import java.util.List;

import com.github.strangercoug.freecardandboard.Player;
import lombok.Getter;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Mancala extends BoardGame {
	@Getter int[] board;
	boolean allowEmptyCaptures;

	public Mancala() {
		minPlayers = maxPlayers = (byte) 2;
	}

	@Override
	public void init(List<Player> players) {
		init(players, true);
	}

	public void init(List<Player> players, boolean allowEmptyCaptures) {
		if (players.size() < minPlayers || players.size() > maxPlayers) {
			throw new IllegalArgumentException("Wrong number of players.");
		}

		this.players = players;
		this.allowEmptyCaptures = allowEmptyCaptures;
		currentPlayerIndex = 0;
		gameWon = false;

		board = new int[] {0, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4};
	}

	public boolean isGameOver() {
		if (isPlayersSideEmpty(0)) {
			emptySideIntoStore(1);
			return true;
		}
		if (isPlayersSideEmpty(1)) {
			emptySideIntoStore(0);
			return true;
		}
		return false;
	}

	public void playMove(int move) {
		int startBin = getOwnStore() + move;
		int seedsToSow = board[startBin];

		// Pick up the stones to sow them
		board[startBin] = 0;

		/* The reason for Math.floorMod(binIndex-1, board.length) instead of
		 * (binIndex-1) % boardLength is that the former has the same sign as the
		 * divisor while the latter has the same sign as the dividend--the bin
		 * number must always be non-negative or we'll get an
		 * IndexOutOfBounds exception.
		 */
		int binIndex = (startBin-1) % board.length;

		while (seedsToSow > 0) {
			// Skip opponent's scoring bin
			if (binIndex == getOpponentStore()) {
				continue;
			}

			board[binIndex]++;
			seedsToSow--;

			if (seedsToSow == 0 && binIndex != getOwnStore()) {
				// Capture if applicable
				if (binIndex/(board.length/2) == currentPlayerIndex && board[binIndex] == 1
						&& (allowEmptyCaptures || board[getAdjacentHouse(binIndex)] != 0)) {
					board[getOwnStore()] += board[binIndex] + board[getAdjacentHouse(binIndex)];
						board[binIndex] = board[getAdjacentHouse(binIndex)] = 0;
				}

				advanceToNextPlayer();
			}
			binIndex = Math.floorMod(binIndex-1, board.length);
		}
	}

	private int getStoreOfPlayer(int player) {
		return player * (board.length/2);
	}

	private int getOwnStore() {
		return currentPlayerIndex * (board.length/2);
	}

	private int getOpponentStore() {
		return (1-currentPlayerIndex) * (board.length/2);
	}

	/**
	 * Returns the number of the bin that would be across from the input house.
	 *  
	 * @param house the bin on the player's side
	 * @return the adjacent bin on the opponent's side
	 * @throws AssertionError when assertions are enabled and the input bin is a
	 *						scoring bin
	 */
	private int getAdjacentHouse(int house) {
		assert house != 0 && house != board.length / 2
				: "Bin should not be a store.";
		return board.length - house;
	}

	private boolean isPlayersSideEmpty(int player) {
		int startBin = getStoreOfPlayer(player) + 1;

		for (int i = startBin; i < startBin + 6; i++) {
			if (board[i] != 0) {
				return false;
			}
		}

		return true;
	}

	private void emptySideIntoStore(int player) {
		int store = getStoreOfPlayer(player);

		for (int i = store+1; i <= store+6; i++) {
			board[store] += board[i];
			board[i] = 0;
		}
	}
}
