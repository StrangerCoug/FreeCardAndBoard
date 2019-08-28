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
import java.util.Scanner;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Mancala extends BoardGame {
	int[] board;
	Scanner keyboard;
	
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
		keyboard = new Scanner(System.in);
	}

	@Override
	public void play() {
		while (!gameWon) {
			playMove(promptMove());
			if (isGameOver()) {
				gameWon = true;
				displayBoard();
				System.out.println("Game over!");
			}
		}
	}
	
	private boolean isGameOver() {
		return isPlayersSideEmpty(0) || isPlayersSideEmpty(1);
	}
	
	private int promptMove() {
		boolean isValid = false;
		int selection = 0;
		
		if (currentPlayerIndex == 0) {
			displayBoard();
			System.out.println(" ↑  ↑  ↑  ↑  ↑  ↑\n 6  5  4  3  2  1");
		}
		else {
			System.out.println(" 1  2  3  4  5  6\n ↓  ↓  ↓  ↓  ↓  ↓");
			displayBoard();
		}
		
		while (!isValid) {
			System.out.print("\n" + players.get(currentPlayerIndex).getName()
					+ ", select a bin to sow stones from: ");
			
			try {
				selection = Integer.parseInt(keyboard.nextLine());
				if (selection < 1 || selection > 6) {
					throw new IllegalArgumentException();
				} else if (board[currentPlayerIndex*(board.length/2) + selection] == 0) {
					System.out.println("You don't have any stones in that bin.");
				} else {
					isValid = true;
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid bin number.");
			}
		}
		
		return selection;
	}
	
	private void playMove(int move) {
		int startBin = getOwnScoringBin() + move;
		int seedsToSow = board[startBin];
		
		// Pick up the stones to sow them
		board[startBin] = 0;
		
		/* The reason for Math.floorMod(i-1, board.length) instead of
		 * (i-1) % boardLength is that the former has the same sign as the
		 * divisor while the latter has the same sign as the dividend--the bin
		 * number must always be non-negative or we'll get an
		 * IndexOutOfBounds exception.
		 */
		for (int i = (startBin-1) % board.length; seedsToSow > 0;
				i = Math.floorMod(i-1, board.length)) {
			// Skip opponent's scoring bin
			if (i == getOpponentScoringBin()) {
				continue;
			} else {
				board[i]++;
				seedsToSow--;
				
				if (seedsToSow == 0 && i != getOwnScoringBin()) {
					// Capture if applicable
					if (i/(board.length/2) == currentPlayerIndex && board[i] == 1
							&& board[getAdjacentBin(i)] != 0) {
						board[getOwnScoringBin()] += board[i] + board[getAdjacentBin(i)];
						board[i] = board[getAdjacentBin(i)] = 0;
					}
					
					advanceToNextPlayer();
				}
			}
		}
	}
	
	private void displayBoard() {
		System.out.println(String.format("%2d",(board[8])) + " "
				+ String.format("%2d",(board[9])) + " "
				+ String.format("%2d",(board[10])) + " "
				+ String.format("%2d",(board[11])) + " "
				+ String.format("%2d",(board[12])) + " "
				+ String.format("%2d",(board[13])) + "\n"
				+ String.format("%2d",(board[7])) + "             "
				+ String.format("%2d",(board[0])) + "\n"
				+ String.format("%2d",(board[6])) + " "
				+ String.format("%2d",(board[5])) + " "
				+ String.format("%2d",(board[4])) + " "
				+ String.format("%2d",(board[3])) + " "
				+ String.format("%2d",(board[2])) + " "
				+ String.format("%2d",(board[1])));
	}
	
	private int getOwnScoringBin () {
		return currentPlayerIndex * (board.length/2);
	}
	
	private int getOpponentScoringBin() {
		return (1-currentPlayerIndex) * (board.length/2);
	}
	
	/**
	 * Returns the number of the bin that would be across from the input bin.
	 *  
	 * @param bin the bin on the player's side
	 * @return the adjacent bin on the opponent's side
	 * @throws AssertionError when assertions are enabled and the input bin is a
	 *                        scoring bin
	 */
	private int getAdjacentBin(int bin) {
		assert bin != 0 && bin != board.length / 2
				: "Bin should not be a scoring bin.";
		return board.length - bin;
	}
	
	private boolean isPlayersSideEmpty(int player) {
		int startBin = player*(board.length/2) + 1;
		
		for (int i = startBin; i < startBin + 6; i++) {
			if (board[i] != 0) {
				return false;
			}
		}
		
		return true;
	}
}
