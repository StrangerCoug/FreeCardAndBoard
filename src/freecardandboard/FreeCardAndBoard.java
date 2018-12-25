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
package freecardandboard;

import freecardandboard.games.board.Backgammon;
import freecardandboard.games.board.Checkers;
import freecardandboard.games.board.Chess;
import freecardandboard.games.board.ChineseCheckers;
import freecardandboard.games.board.Go;
import freecardandboard.games.board.Halma;
import freecardandboard.games.board.Janggi;
import freecardandboard.games.board.Reversi;
import freecardandboard.games.board.Shogi;
import freecardandboard.games.board.Xiangqi;
import freecardandboard.games.card.Bridge;
import freecardandboard.games.card.Canasta;
import freecardandboard.games.card.CrazyEights;
import freecardandboard.games.card.Cribbage;
import freecardandboard.games.card.Hearts;
import freecardandboard.games.card.NinetyNine;
import freecardandboard.games.card.OldMaid;
import freecardandboard.games.card.Rummy;
import freecardandboard.games.card.Spades;
import freecardandboard.games.card.Whist;
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
        ArrayList players;
        
        while (true) {
            do {
                System.out.println("Select game to play or type \"QUIT\" to "
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
                        + "13. Ninety-nine\n"
                        + "14. Old Maid\n"
                        + "15. Reversi\n"
                        + "16. Rummy\n"
                        + "17. Shogi\n"
                        + "18. Spades\n"
                        + "19. Whist\n"
                        + "20. Xiangqi");
                entry = input.nextLine();
                if (entry.equalsIgnoreCase("quit")) {
                    input.close();
                    System.exit(0);
                }
                try {
                    int gameSelected = Integer.parseInt(entry);
                    if (gameSelected >= 1 && gameSelected <= 18)
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
            case 13: return new NinetyNine();
            case 14: return new OldMaid();
            case 15: return new Reversi();
            case 16: return new Rummy();
            case 17: return new Shogi();
            case 18: return new Spades();
            case 19: return new Whist();
            case 20: return new Xiangqi();
            default: throw new IllegalArgumentException();
        }
    }
}
