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
package com.github.strangercoug.freecardandboard.games.model;

import com.github.strangercoug.freecardandboard.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class Game {
	@Getter	protected byte minPlayers;
	@Getter	protected byte maxPlayers;
	@Getter	@Setter	protected List<Player> players;
	@Getter	@Setter	protected int currentPlayerIndex;
	@Getter	@Setter	protected boolean gameWon;

	/** 
	 * Initializes the variables that this game needs before it starts. If a
	 * game needs more information to start than that on the players, for
	 * example to accommodate variants with different board sizes, then the
	 * default initializer supplied here should be overloaded to account for
	 * that.
	 * 
	 * @param players  the list of players in turn order. Should never be null.
	 */
	public abstract void init(List<Player> players);

	protected void advanceToNextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}

}