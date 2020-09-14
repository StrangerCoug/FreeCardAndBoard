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
package com.github.strangercoug.freecardandboard.objs;

import com.github.strangercoug.freecardandboard.enums.PieceColor;
import com.github.strangercoug.freecardandboard.enums.ShogiPieceType;


/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class ShogiBoard extends ChessVarBoard {
	public ShogiBoard() {
		board = new ShogiPiece[9][9];
	}
	
	@Override
	public void initBoard() {
		ShogiPieceType[] backRankPieces = {ShogiPieceType.LANCE,
			ShogiPieceType.KNIGHT, ShogiPieceType.SILVER, ShogiPieceType.GOLD,
			ShogiPieceType.KING, ShogiPieceType.GOLD, ShogiPieceType.SILVER,
			ShogiPieceType.KNIGHT, ShogiPieceType.LANCE};
		ShogiPieceType[] rankBPieces = {null, ShogiPieceType.BISHOP, null, null,
			null, null, null, ShogiPieceType.ROOK, null};
		ShogiPieceType[] rankHPieces = {null, ShogiPieceType.ROOK, null, null,
			null, null, null, ShogiPieceType.BISHOP, null};
		
		for (int i = 0; i < board.length; i++) {
			board[i][0] = new ShogiPiece(PieceColor.WHITE, backRankPieces[i]);
			if (rankBPieces[i] == null) board[i][1] = null;
			else board[i][1] = new ShogiPiece(PieceColor.WHITE,
					rankBPieces[i]);
			board[i][2] = new ShogiPiece(PieceColor.WHITE, ShogiPieceType.PAWN);
			board[i][3] = board[i][4] = board[i][5] = null;
			board[i][6] = new ShogiPiece(PieceColor.BLACK, ShogiPieceType.PAWN);
			if (rankHPieces[i] == null) board[i][7] = null;
			else board[i][7] = new ShogiPiece(PieceColor.BLACK,
					rankHPieces[i]);
			board[i][8] = new ShogiPiece(PieceColor.BLACK, backRankPieces[i]);
		}
	}
	
	@Override
	public String toString() {
		String textBoard = "";
		
		for (int j = 0; j < board[0].length; j++) {
			for (int i = board.length-1; i >= 0; i--) {
				if (board[i][j] == null)
					textBoard += "__";
				else {
					if (board[i][j].getAbbreviation().length() == 1)
						textBoard += ".";
					textBoard += board[i][j].getAbbreviation();
				}
				textBoard += " ";
			}
			textBoard += "\n";
		}
		
		return textBoard;
	}
}
