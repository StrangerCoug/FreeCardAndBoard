package com.github.strangercoug.freecardandboard.games.controller.board;

import com.github.strangercoug.freecardandboard.games.model.board.Chess;
import com.github.strangercoug.freecardandboard.games.view.board.ChessView;

public class ChessController extends BoardGameController {

	public ChessController(Chess model, ChessView view) {
		super(model, view);
	}

	@Override
	public void play() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void updateView() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
