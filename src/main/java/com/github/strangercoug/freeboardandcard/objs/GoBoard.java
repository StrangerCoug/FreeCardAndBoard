/*
 * Copyright (c) 2018, Jeffrey Hope <>
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

import com.github.strangercoug.freeboardandcard.enums.PieceColor;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class GoBoard {
	protected PieceColor[][] board;

	public GoBoard() {
		this(19);
	}

	public GoBoard(int i) {
		board = new PieceColor[i][i];
	}

	public PieceColor[][] getBoard() {
		return board;
	}

	public void clearBoard() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = null;
	}

	public void placePiece(PieceColor piece, int[] location) {
		if (location.length != 2) {
			throw new IllegalArgumentException("The coordinate must be of " +
					"length 2.");
		}
		board[location[0]][location[1]] = piece;
	}

	public void removePiece (int[] location) {
		if (location.length != 2) {
			throw new IllegalArgumentException("The coordinate must be of " +
					"length 2.");
		}
		board[location[0]][location[1]] = null;	   
	}

	@Override
	public String toString() {
		String textBoard = "";

		for (int j = 0; j < board.length; j++) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][j] == null)
					textBoard += "_";
				else if (board[i][j] == PieceColor.BLACK)
					textBoard += "X";
				else textBoard += "O";
				textBoard += " ";
			}
			textBoard += "\n";
		}

		return textBoard;
	}
}
