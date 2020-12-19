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
package com.github.strangercoug.freeboardandcard.objs;

import com.github.strangercoug.freeboardandcard.enums.ChessPieceType;
import com.github.strangercoug.freeboardandcard.enums.PieceColor;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class Chess960Board extends ChessBoard {
	private short startingPosition;
	protected byte queenRookFile, kingFile, kingRookFile;

	@Override
	public void initBoard() {
		startingPosition = (short)(Math.random() * 960);
		if (startingPosition == 518) // this corresponds to the standsrd SP, so save some work and memory in this case
			super.initBoard();
		else initBoard(startingPosition);
	}

	/**
	 * This method is public primarily for testing and debugging purposes. In
	 * production use, it is generally not desirable to call this method
	 * directly.
	 * 
	 * @param idn the Scharnagl SP
	 */

	public void initBoard(short idn) {
		if (idn < 0 || idn > 959)
			throw new IllegalArgumentException("idn must be between 0 and 959" +
					" inclusive.");

		ChessPieceType[] pieceForFile;
		ChessPieceType[][] bishopTable = {
			{ChessPieceType.BISHOP,ChessPieceType.BISHOP,null,null,null,null,null,null},
			{ChessPieceType.BISHOP,null,null,ChessPieceType.BISHOP,null,null,null,null},
			{ChessPieceType.BISHOP,null,null,null,null,ChessPieceType.BISHOP,null,null},
			{ChessPieceType.BISHOP,null,null,null,null,null,null,ChessPieceType.BISHOP},
			{null,ChessPieceType.BISHOP,ChessPieceType.BISHOP,null,null,null,null,null},
			{null,null,ChessPieceType.BISHOP,ChessPieceType.BISHOP,null,null,null,null},
			{null,null,ChessPieceType.BISHOP,null,null,ChessPieceType.BISHOP,null,null},
			{null,null,ChessPieceType.BISHOP,null,null,null,null,ChessPieceType.BISHOP},
			{null,ChessPieceType.BISHOP,null,null,ChessPieceType.BISHOP,null,null,null},
			{null,null,null,ChessPieceType.BISHOP,ChessPieceType.BISHOP,null,null,null},
			{null,null,null,null,ChessPieceType.BISHOP,ChessPieceType.BISHOP,null,null},
			{null,null,null,null,ChessPieceType.BISHOP,null,null,ChessPieceType.BISHOP},
			{null,ChessPieceType.BISHOP,null,null,null,null,ChessPieceType.BISHOP,null},
			{null,null,null,ChessPieceType.BISHOP,null,null,ChessPieceType.BISHOP,null},
			{null,null,null,null,null,ChessPieceType.BISHOP,ChessPieceType.BISHOP,null},
			{null,null,null,null,null,null,ChessPieceType.BISHOP,ChessPieceType.BISHOP},
		};
		ChessPieceType[][] knightTable = {
			{ChessPieceType.KNIGHT, ChessPieceType.KNIGHT, null, null, null},
			{ChessPieceType.KNIGHT, null, ChessPieceType.KNIGHT, null, null},
			{ChessPieceType.KNIGHT, null, null, ChessPieceType.KNIGHT, null},
			{ChessPieceType.KNIGHT, null, null, null, ChessPieceType.KNIGHT},
			{null, ChessPieceType.KNIGHT, ChessPieceType.KNIGHT, null, null},
			{null, ChessPieceType.KNIGHT, null, ChessPieceType.KNIGHT, null},
			{null, ChessPieceType.KNIGHT, null, null, ChessPieceType.KNIGHT},
			{null, null, ChessPieceType.KNIGHT, ChessPieceType.KNIGHT, null},
			{null, null, ChessPieceType.KNIGHT, null, ChessPieceType.KNIGHT},
			{null, null, null, ChessPieceType.KNIGHT, ChessPieceType.KNIGHT}
		};

		// Place the bishops
		byte q1 = (byte)(idn/16);
		byte r = (byte)(idn%16);
		pieceForFile = bishopTable[r];

		// Place the queen
		byte q2 = (byte)(q1/6);
		r = (byte)(q1%6);
		for (int i = 0; i < pieceForFile.length; i++) {
			if (pieceForFile[i] != null)
				continue;
			if (r == 0) {
				pieceForFile[i] = ChessPieceType.QUEEN;
				break;
			}
			r--;
		}

		// Place the knights
		int j = 0;
		for (int i = 0; i < pieceForFile.length && j < knightTable[q2].length;
				i++) {
			if (pieceForFile[i] != null)
				continue;
			pieceForFile[i] = knightTable[q2][j];
			j++;
		}

		// Place the rooks and king
		j = 0;
		for (int i = 0; i < pieceForFile.length && j < 3; i++) {
			if (pieceForFile[i] != null)
				continue;
			if (j == 1) {
				pieceForFile[i] = ChessPieceType.KING;
				kingFile = (byte) i;
			}
			else {
				pieceForFile[i] = ChessPieceType.ROOK;
				if (j == 0)
					queenRookFile = (byte) i;
				else kingRookFile = (byte) i;
			}
			j++;
		}

		// Place the pieces according to our calculations
		for (int i = 0; i < board.length; i++) {
			board[i][0] = new ChessPiece(PieceColor.WHITE, pieceForFile[i]);
			board[i][1] = new ChessPiece(PieceColor.WHITE, ChessPieceType.PAWN);
			board[i][2] = board[i][3] = board[i][4] = board[i][5] = null;
			board[i][6] = new ChessPiece(PieceColor.BLACK, ChessPieceType.PAWN);
			board[i][7] = new ChessPiece(PieceColor.BLACK, pieceForFile[i]);
		}
	}

	/**
	 * While this method has a self-explanatory name, it does not check if the
	 * castle is legal.
	 * 
	 * The reason this method does not simply move both castling pieces normally
	 * is because it sometimes overwrites one of them with the other. To work
	 * around this; the rook move is effectively in the middle of the king move.
	 * This is analogous to the recommended way to execute a castle in Chess960
	 * with physical equipment: first move the king off the board, then move the
	 * rook, then put the king back on the board in its final castling position.
	 * 
	 * @param player the player castling
	 * @param kingside true for a kingside castle, false for a queenside castle
	 */
	@Override
	public void castle(PieceColor player, boolean kingside) {
		byte startRank;
		switch (player) {
			case WHITE: startRank = 0;
				break;
			case BLACK: startRank = 7;
				break;
			default: throw new NullPointerException();
		}
		board[kingFile][startRank] = null;

		if (kingside) {
			movePiece(new int[]{kingRookFile, startRank}, new int[]{3,
					startRank});
			board[2][startRank] = new ChessPiece(player, ChessPieceType.KING);
		}
		else {
			movePiece(new int[]{queenRookFile, startRank}, new int[]{5,
					startRank});
			board[6][startRank] = new ChessPiece(player, ChessPieceType.KING);
		}
	}
}
