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
package com.github.strangercoug.freecardandboard;

import java.util.ArrayList;
import java.util.Scanner;

import com.github.strangercoug.freecardandboard.games.board.Backgammon;
import com.github.strangercoug.freecardandboard.games.board.Checkers;
import com.github.strangercoug.freecardandboard.games.board.Chess;
import com.github.strangercoug.freecardandboard.games.board.ChineseCheckers;
import com.github.strangercoug.freecardandboard.games.board.Go;
import com.github.strangercoug.freecardandboard.games.board.Halma;
import com.github.strangercoug.freecardandboard.games.board.Janggi;
import com.github.strangercoug.freecardandboard.games.board.Mancala;
import com.github.strangercoug.freecardandboard.games.board.Reversi;
import com.github.strangercoug.freecardandboard.games.board.Shogi;
import com.github.strangercoug.freecardandboard.games.board.Xiangqi;
import com.github.strangercoug.freecardandboard.games.card.Bridge;
import com.github.strangercoug.freecardandboard.games.card.Canasta;
import com.github.strangercoug.freecardandboard.games.card.CrazyEights;
import com.github.strangercoug.freecardandboard.games.card.Cribbage;
import com.github.strangercoug.freecardandboard.games.card.Hearts;
import com.github.strangercoug.freecardandboard.games.card.NinetyNine;
import com.github.strangercoug.freecardandboard.games.card.OldMaid;
import com.github.strangercoug.freecardandboard.games.card.Rummy;
import com.github.strangercoug.freecardandboard.games.card.Spades;
import com.github.strangercoug.freecardandboard.games.card.Whist;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class FreeCardAndBoard {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean validInput = false;
		boolean playAgain = false;
		String entry;
		Game game = null;
		ArrayList<Player> players;

		do {
			System.out.println("""
					Select game to play or type "Q" or "QUIT" to  quit:
					1. Backgammon
					2. Bridge
					3. Canasta
					4. Checkers
					5. Chess
					6. Chinese Checkers
					7. Crazy Eights
					8. Cribbage
					9. Go
					10. Halma
					11. Hearts
					12. Janggi
					13. Mancala
					14. Ninety-nine
					15. Old Maid
					16. Reversi
					17. Rummy
					18. Shogi
					19. Spades
					20. Whist
					21. Xiangqi""");
			entry = input.nextLine();
			if (entry.equalsIgnoreCase("q") || entry.equalsIgnoreCase("quit")) {
				input.close();
				System.exit(0);
			}
			try {
				int gameSelected = Integer.parseInt(entry);
				if (gameSelected >= 1 && gameSelected <= 21)
					validInput = true;
				game = returnGame(gameSelected);
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}
			catch (IllegalArgumentException e) {
				System.out.println("Invalid game number.");
			}
		} while (!validInput);

		validInput = false;
		int numPlayers = game.getMinPlayers();

		if (numPlayers != game.getMaxPlayers()) {
			while (!validInput) {
				try {
					System.out.print("How many players would you like? ("
							+ game.getMinPlayers() + "-" + game.getMaxPlayers() + "): ");
					entry = input.nextLine();
					numPlayers = Integer.parseInt(entry);

					if (numPlayers < game.getMinPlayers()
							|| numPlayers > game.getMaxPlayers()) {
						throw new IllegalArgumentException();
					}

					validInput = true;
				}
				catch(NumberFormatException e) {
					System.out.print("Invalid input.");
				}
				catch(IllegalArgumentException e) {
					System.out.println("Out of range.");
				}
			}
		}

		players = new ArrayList<>(numPlayers);
		for (int i = 1; i <= numPlayers; i++) {
			boolean isHuman = false;
			Player newPlayer;

			validInput = false;
			while (!validInput) {
				System.out.print("Is player #" + i + " human? (Y/N): ");
				char selection = input.nextLine().charAt(0);
				switch (selection) {
					case 'Y', 'y' -> {
						validInput = true;
						isHuman = true;
					}
					case 'N', 'n' -> {
						validInput = true;
						isHuman = false;
					}
					default -> {
						validInput = false;
						System.out.println("Invalid selection.");
					}
				}
			}

			if (isHuman) {
				System.out.print("Enter name of player #" + i + ": ");
				entry = input.nextLine();
				newPlayer = new Player(entry);
			} else {
				newPlayer = new RandomPlayer();
			}

			newPlayer.setGamePlaying(game);
			players.add(newPlayer);
		}
		System.out.println("Good luck!");

		do {
			game.init(players);
			game.play();
			validInput = false;
			while (!validInput) {
				System.out.print("Play again? (Y/N): ");
				char selection = input.nextLine().charAt(0);
				switch (selection) {
					case 'Y', 'y' -> {
						validInput = true;
						playAgain = true;
					}
					case 'N', 'n' -> {
						validInput = true;
						playAgain = false;
					}
					default -> {
						validInput = false;
						System.out.println("Invalid selection.");
					}
				}
			}
		} while (playAgain);
	}

	private static Game returnGame(int i) {
		return switch (i) {
			case 1 -> new Backgammon();
			case 2 -> new Bridge();
			case 3 -> new Canasta();
			case 4 -> new Checkers();
			case 5 -> new Chess();
			case 6 -> new ChineseCheckers();
			case 7 -> new CrazyEights();
			case 8 -> new Cribbage();
			case 9 -> new Go();
			case 10 -> new Halma();
			case 11 -> new Hearts();
			case 12 -> new Janggi();
			case 13 -> new Mancala();
			case 14 -> new NinetyNine();
			case 15 -> new OldMaid();
			case 16 -> new Reversi();
			case 17 -> new Rummy();
			case 18 -> new Shogi();
			case 19 -> new Spades();
			case 20 -> new Whist();
			case 21 -> new Xiangqi();
			default -> throw new IllegalArgumentException();
		};
	}
}
