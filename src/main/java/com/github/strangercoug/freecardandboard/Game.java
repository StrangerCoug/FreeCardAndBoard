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
package com.github.strangercoug.freecardandboard;

import java.util.ArrayList;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public abstract class Game {
	protected byte minPlayers, maxPlayers;
	protected ArrayList<Player> players;
	protected int currentPlayerIndex;
	protected boolean gameWon;
	
	public final byte getMinPlayers() {
		return minPlayers;
	}
	
	public final byte getMaxPlayers() {
		return maxPlayers;
	}
	
	public final ArrayList<Player> getPlayers() {
		return players;
	}
	
	public final int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}
	
	public final boolean getGameWon() {
		return gameWon;
	}
	
	/** 
	 * Initializes the variables that this game needs before it starts. If a
	 * game needs more information to start than that on the players, for
	 * example to accommodate variants with different board sizes, then the
	 * default initializer supplied here should be overloaded to account for
	 * that.
	 * 
	 * @param players  the list of players in turn order. Should never be null.
	 */
	public abstract void init(ArrayList<Player> players);
	
	/**
	 * Starts play of this game. Game logic belongs in this method.
	 */
	public abstract void play();
	
	protected void advanceToNextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
	}
}