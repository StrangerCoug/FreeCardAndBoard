/*
 * Copyright (c) 2018, Jeffrey Hope
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
package com.github.strangercoug.freecardandboard;

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
import java.util.ArrayList;
import java.util.Scanner;

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
		boolean validInput = false, playAgain = false;
		String entry;
		Game game = null;
		ArrayList<Player> players;
		
		while (true) {
			do {
				System.out.println("Select game to play or type \"Q\" or \"QUIT\" to "
						+" quit:\n"
						+ "1. Backgammon\n"
						+ "2. Bridge\n"
						+ "3. Canasta\n"
						+ "4. Checkers\n"
						+ "5. Chess\n"
						+ "6. Chinese Checkers\n"
						+ "7. Crazy Eights\n"
						+ "8. Cribbage\n"
						+ "9. Go\n"
						+ "10. Halma\n"
						+ "11. Hearts\n"
						+ "12. Janggi\n"
						+ "13. Mancala\n"
						+ "14. Ninety-nine\n"
						+ "15. Old Maid\n"
						+ "16. Reversi\n"
						+ "17. Rummy\n"
						+ "18. Shogi\n"
						+ "19. Spades\n"
						+ "20. Whist\n"
						+ "21. Xiangqi");
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
			int numPlayers = 0;
			do {
				try {
					System.out.print("How many players would you like? ");
					entry = input.nextLine();
					numPlayers = Integer.parseInt(entry);
					if (numPlayers < 1) {
						System.out.println("You must enter a positive "
								+ "integer.");
					}
				}
				catch(NumberFormatException e) {
					System.out.print("Invalid input.");
				}
			} while (numPlayers < 1);
			players = new ArrayList<Player>(numPlayers);
			for (int i = 1; i <= numPlayers; i++) {
				System.out.print("Enter name of player #" + i + ": ");
				entry = input.nextLine();
				players.add(new Player(entry));
			}
			System.out.println("Good luck!");
			
			do {
				game.init(players);
				game.play();
				validInput = false;
				do {
					System.out.print("Play again? (Y/N): ");
					char selection = input.nextLine().charAt(0);
					switch (selection) {
						case 'Y': case 'y':
							validInput = true;
							playAgain = true;
							break;
						case 'N': case 'n':
							validInput = true;
							playAgain = false;
							break;
						default:
							validInput = false;
							System.out.println("Invalid selection.");
					}
				} while (!validInput);
			} while (playAgain);
		}
	}
	
	private static Game returnGame(int i) {
		switch (i) {
			case 1: return new Backgammon();
			case 2: return new Bridge();
			case 3: return new Canasta();
			case 4: return new Checkers();
			case 5: return new Chess();
			case 6: return new ChineseCheckers();
			case 7: return new CrazyEights();
			case 8: return new Cribbage();
			case 9: return new Go();
			case 10: return new Halma();
			case 11: return new Hearts();
			case 12: return new Janggi();
			case 13: return new Mancala();
			case 14: return new NinetyNine();
			case 15: return new OldMaid();
			case 16: return new Reversi();
			case 17: return new Rummy();
			case 18: return new Shogi();
			case 19: return new Spades();
			case 20: return new Whist();
			case 21: return new Xiangqi();
			default: throw new IllegalArgumentException();
		}
	}
}
