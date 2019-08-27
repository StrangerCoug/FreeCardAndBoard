/*
 * Copyright (c) 2019, Jeffrey Hope <>
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
package com.github.strangercoug.freecardandboard.games.board;

import com.github.strangercoug.freecardandboard.Player;
import java.util.ArrayList;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Mancala extends BoardGame {
	int[] board;
	
	@Override
	public void init(ArrayList<Player> players) {
		if (players.size() != 2) {
			throw new IllegalArgumentException("You tried to start a game of " +
					"mancala with " + players.size() + " players. The game " +
					"requires 2 players.");
		}
		this.players = players;
		this.currentPlayerIndex = 0;
		this.gameWon = false;
				
		board = new int[] {0, 4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4};
	}

	@Override
	public void play() {
		displayBoard();
	}
	
	public void displayBoard() {
		System.out.println(String.format("%2d",(board[8])) + " " +
				String.format("%2d",(board[9])) + " " +
				String.format("%2d",(board[10])) + " " +
				String.format("%2d",(board[11])) + " " +
				String.format("%2d",(board[12])) + " " +
				String.format("%2d",(board[13])) + "\n" +
				String.format("%2d",(board[7])) + "             " +
				String.format("%2d",(board[0])) + "\n" +
				String.format("%2d",(board[6])) + " " +
				String.format("%2d",(board[5])) + " " +
				String.format("%2d",(board[4])) + " " +
				String.format("%2d",(board[3])) + " " +
				String.format("%2d",(board[2])) + " " +
				String.format("%2d",(board[1])));
	}
}
