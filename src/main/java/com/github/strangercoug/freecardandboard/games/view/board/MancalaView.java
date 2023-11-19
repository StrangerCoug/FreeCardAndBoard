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
package com.github.strangercoug.freecardandboard.games.view.board;

import com.github.strangercoug.freecardandboard.Player;
import com.github.strangercoug.freecardandboard.RandomPlayer;

import java.util.List;
import java.util.Scanner;

public class MancalaView extends BoardGameView {

	private int promptMove(List<Player> players, int[] board, int currentPlayerIndex) {
		boolean isValid = false;
		int selection = 0;
		Scanner keyboard = new Scanner(System.in);

		while (!isValid) {
			if (players.get(currentPlayerIndex) instanceof RandomPlayer randomPlayer) {
				return Integer.parseInt(randomPlayer.getMove());
			} else {
				System.out.print("\n" + players.get(currentPlayerIndex).getName()
						+ ", select a house to sow stones from: ");

				try {
					selection = Integer.parseInt(keyboard.nextLine());
					if (selection < 1 || selection > 6) {
						throw new IllegalArgumentException();
					} else if (board[currentPlayerIndex*(board.length/2) + selection] == 0) {
						System.out.println("You don't have any stones in that house.");
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
		}
		System.out.println();
		return selection;
	}

	public void printBoard(List<Player> players, int[] board, int currentPlayerIndex) {
		if (currentPlayerIndex == 0) {
			displayBoard(players, board);
			for (int i = 0; i < players.get(1).getName().length() + 4; i++) System.out.print(" ");
			System.out.println(" ↑  ↑  ↑  ↑  ↑  ↑");
			for (int i = 0; i < players.get(1).getName().length() + 4; i++) System.out.print(" ");
			System.out.println(" 6  5  4  3  2  1");
		}
		else {
			for (int i = 0; i < players.get(1).getName().length() + 4; i++) System.out.print(" ");
			System.out.println(" 1  2  3  4  5  6");
			for (int i = 0; i < players.get(1).getName().length() + 4; i++) System.out.print(" ");
			System.out.println(" ↓  ↓  ↓  ↓  ↓  ↓");
			displayBoard(players, board);
		}
	}

	private void displayBoard(List<Player> players, int[] board) {

		System.out.print(players.get(1).getName());
		System.out.print(String.format("%3d",(board[7])) + " ");
		System.out.println(String.format("%2d",(board[8])) + " "
				+ String.format("%2d",(board[9])) + " "
				+ String.format("%2d",(board[10])) + " "
				+ String.format("%2d",(board[11])) + " "
				+ String.format("%2d",(board[12])) + " "
				+ String.format("%2d",(board[13])));

		for (int i = 0; i < players.get(1).getName().length() + 1; i++) System.out.print(" ");
		System.out.println(
				"  "
						+ String.format("%3d",(board[6])) + " "
						+ String.format("%2d",(board[5])) + " "
						+ String.format("%2d",(board[4])) + " "
						+ String.format("%2d",(board[3])) + " "
						+ String.format("%2d",(board[2])) + " "
						+ String.format("%2d",(board[1])) + " "
						+ String.format("%2d", board[0]) + "  "
						+ players.get(0).getName());
	}

	private void declareGameOver(List<Player> players, int[] board) {
		displayBoard(players, board);
		System.out.println("Game over!");
	}
}
