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
import com.github.strangercoug.freecardandboard.enums.ChessPieceType;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class ChessBoard extends ChessVarBoard {
    public ChessBoard() {
        board = new ChessPiece[8][8];
    }
    
    public void initBoard() {
        ChessPieceType[] pieceForFile = {ChessPieceType.ROOK, ChessPieceType.KNIGHT,
            ChessPieceType.BISHOP, ChessPieceType.QUEEN, ChessPieceType.KING, ChessPieceType.BISHOP,
            ChessPieceType.KNIGHT, ChessPieceType.ROOK};

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
     * en passant capture is legal or even if it is an actual en passant
     * capture.
     * 
     * @param start the starting coordinate
     * @param end the ending coordinate
     */
    public void captureEnPassant(int[] start, int[] end) {
        movePiece(start, end);
        board[end[0]][start[1]] = null;
    }
    
    
    /**
     * While this method has a self-explanatory name, it does not check if the
     * castle is legal.
     * 
     * @param player the player castling
     * @param kingside true for a kingside castle, false for a queenside castle
     */
    public void castle(PieceColor player, boolean kingside) {
        byte startRank;
        switch (player) {
            case WHITE: startRank = 0;
                break;
            case BLACK: startRank = 7;
                break;
            default: throw new NullPointerException();
        }
        
        if (kingside) {
            movePiece(new int[]{4,startRank}, new int[]{6,startRank});
            movePiece(new int[]{7,startRank}, new int[]{5,startRank});
        }
        else {
            movePiece(new int[]{4,startRank}, new int[]{2,startRank});
            movePiece(new int[]{0,startRank}, new int[]{3,startRank});
        }
    }
    
    @Override
    public String toString() {
        String textBoard = "";
        
        for (int j = board.length-1; j >= 0; j--) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == null)
                    textBoard += "_";
                else textBoard += board[i][j].getAbbreviation();
                textBoard += " ";
            }
            textBoard += "\n";
        }
        
        return textBoard;
    }
}
