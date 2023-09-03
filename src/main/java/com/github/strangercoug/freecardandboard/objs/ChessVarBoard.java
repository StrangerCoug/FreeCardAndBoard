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
package com.github.strangercoug.freecardandboard.objs;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class ChessVarBoard {
	protected ChessVarPiece[][] board;

	public abstract void initBoard();

	public ChessVarPiece[][] getBoard() {
		return board;
	}

	/**
	 * While this method has a self-explanatory name, it does not check if the
	 * move being submitted is actually legal, and it cannot handle the castling
	 * or en passant captures of Western chess by itself. Those types of moves
	 * have their own functions with special handling, although they all call
	 * this method.
	 *
	 * @param start the starting coordinate
	 * @param end the ending coordinate
	 */
	public void movePiece(int[] start, int[] end) {
		if (start.length != 2 || end.length != 2) {
			throw new IllegalArgumentException("Each coordinate must be of " + 
					"length 2.");
		}
		ChessVarPiece pieceMoving = board[start[0]][start[1]];
		board[start[0]][start[1]] = null;
		board[end[0]][end[1]] = pieceMoving;
	}

	/**
	 * Overwrites any piece at the specified square on the board with the
	 * specified chess piece. In standard chess and most of its variants, this
	 * should be called only to promote a piece, but it can also be used for
	 * drops in bughouse chess as well as in shogi, hence its neutral name.
	 *
	 * @param piece the piece to be promoted to or dropped
	 * @param location the coordinates of the promoted or dropped piece
	 */
	public void replacePiece(ChessVarPiece piece, int[] location) {
		if (location.length != 2) {
			throw new IllegalArgumentException("The coordinate must be of " +
					"length 2.");
		}
		board[location[0]][location[1]] = piece;
	}

}
