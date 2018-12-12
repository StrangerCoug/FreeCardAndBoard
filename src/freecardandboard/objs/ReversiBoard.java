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
package freecardandboard.objs;

import freecardandboard.enums.PieceColor;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public class ReversiBoard extends GoBoard {
    public ReversiBoard() {
        super(8);
    }
    
    public ReversiBoard(int i) {
        super(i);
    }
    
    public void initBoard() {
        super.clearBoard();
        
        //FIXME: This seems to be always 0; it should be half the board length.
        int half = board.length % 2;
        
        super.placePiece(PieceColor.WHITE, new int[]{half, half});
        super.placePiece(PieceColor.WHITE, new int[]{half-1, half-1});
        super.placePiece(PieceColor.BLACK, new int[]{half-1, half});
        super.placePiece(PieceColor.BLACK, new int[]{half, half-1});
    }
    
    public void flipPiece(int[] location) {
        if (location.length != 2) {
            throw new IllegalArgumentException("The coordinate must be of " +
                    "length 2.");
        }
        if (board[location[0]][location[1]] == null)
            throw new NullPointerException("There's no piece at (" + location[0]
                    + ", " + location[1] + ".");
        
        if (board[location[0]][location[1]] == PieceColor.BLACK)
            board[location[0]][location[1]] = PieceColor.WHITE;
        else board[location[0]][location[1]] = PieceColor.BLACK;
    }
}
