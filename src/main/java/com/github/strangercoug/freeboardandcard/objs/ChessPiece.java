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
package com.github.strangercoug.freeboardandcard.objs;

import com.github.strangercoug.freeboardandcard.enums.ChessPieceType;
import com.github.strangercoug.freeboardandcard.enums.PieceColor;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class ChessPiece extends ChessVarPiece {
	private final PieceColor PIECE_COLOR;
	private final ChessPieceType PIECE_TYPE;

	public ChessPiece(PieceColor pieceColor, ChessPieceType pieceType) {
		PIECE_COLOR = pieceColor;
		PIECE_TYPE = pieceType;
	}

	public PieceColor getPieceColor() {
		return PIECE_COLOR;
	}

	public ChessPieceType getPieceType() {
		return PIECE_TYPE;
	}

	public String getAbbreviation() {
		return switch (PIECE_COLOR) {
			case WHITE -> PIECE_TYPE.getAbbrev().toUpperCase();
			default -> PIECE_TYPE.getAbbrev().toLowerCase();
		};
	}   

	/**
	 * 
	 * @return the Unicode character that encodes this piece.
	 */
	public char getUnicodeChar() {
		return (char) (0x2654 + (6 * PIECE_COLOR.ordinal()) +
				PIECE_TYPE.ordinal());
	}
}
