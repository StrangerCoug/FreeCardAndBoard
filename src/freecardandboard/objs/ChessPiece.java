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
package freecardandboard.objs;

import freecardandboard.enums.ChessPieceColor;
import freecardandboard.enums.ChessPieceType;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class ChessPiece extends ChessVarPiece {
    private final ChessPieceColor PIECE_COLOR;
    private final ChessPieceType PIECE_TYPE;
    
    public ChessPiece(ChessPieceColor pieceColor, ChessPieceType pieceType) {
        PIECE_COLOR = pieceColor;
        PIECE_TYPE = pieceType;
    }
    
    public ChessPieceColor getPieceColor() {
        return PIECE_COLOR;
    }
    
    public ChessPieceType getPieceType() {
        return PIECE_TYPE;
    }
    
    public String getAbbreviation() {
        switch (PIECE_COLOR) {
            case WHITE: return PIECE_TYPE.getAbbrev().toUpperCase();
            default: return PIECE_TYPE.getAbbrev().toLowerCase();
        }
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
