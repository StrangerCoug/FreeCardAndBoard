/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecardandboard.objs;

import freecardandboard.enums.ShogiPieceColor;
import freecardandboard.enums.ShogiPieceType;


/**
 *
 * @author Jeffrey Hope
 */
public class ShogiBoard extends ChessVarBoard {
    public ShogiBoard() {
        board = new ShogiPiece[9][9];
    }
    
    @Override
    public void initBoard() {
        ShogiPieceType[] rankAPieces = {ShogiPieceType.LANCE,
            ShogiPieceType.KNIGHT, ShogiPieceType.SILVER, ShogiPieceType.GOLD,
            ShogiPieceType.KING, ShogiPieceType.GOLD, ShogiPieceType.SILVER,
            ShogiPieceType.KNIGHT, ShogiPieceType.LANCE};
        ShogiPieceType[] rankBPieces = {null, ShogiPieceType.BISHOP, null, null,
            null, null, null, ShogiPieceType.ROOK, null};
        ShogiPieceType[] rankHPieces = {null, ShogiPieceType.ROOK, null, null,
            null, null, null, ShogiPieceType.BISHOP, null};
        ShogiPieceType[] rankIPieces = {ShogiPieceType.LANCE,
            ShogiPieceType.KNIGHT, ShogiPieceType.SILVER, ShogiPieceType.GOLD,
            ShogiPieceType.JEWEL, ShogiPieceType.GOLD, ShogiPieceType.SILVER,
            ShogiPieceType.KNIGHT, ShogiPieceType.LANCE};
        
        for (int i = 0; i < board.length; i++) {
            board[i][0] = new ShogiPiece(ShogiPieceColor.GOTE, rankAPieces[i]);
            if (rankBPieces[i] == null) board[i][1] = null;
            else board[i][1] = new ShogiPiece(ShogiPieceColor.GOTE,
                    rankBPieces[i]);
            board[i][2] = new ShogiPiece(ShogiPieceColor.GOTE, ShogiPieceType.PAWN);
            board[i][3] = board[i][4] = board[i][5] = null;
            board[i][6] = new ShogiPiece(ShogiPieceColor.SENTE, ShogiPieceType.PAWN);
            if (rankHPieces[i] == null) board[i][7] = null;
            else board[i][7] = new ShogiPiece(ShogiPieceColor.SENTE,
                    rankHPieces[i]);
            board[i][8] = new ShogiPiece(ShogiPieceColor.SENTE, rankIPieces[i]);
        }
    }
    
    @Override
    public String toString() {
        String textBoard = "";
        
        for (int j = 0; j < board[0].length; j++) {
            for (int i = board.length-1; i >= 0; i--) {
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
