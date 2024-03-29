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
package com.github.strangercoug.freecardandboard.enums;

/**
 *
 * @author Jeffrey Hope <strangercoug@hotmail.com>
 */
public enum ShogiPieceType implements PieceType {
	KING("king", "K", "K"), // covers both players' kings for simplicity
	ROOK("rook", "R", "R"),
	DRAGON("dragon", "+R", "RF"),
	BISHOP("bishop", "B", "B"),
	HORSE("horse", "+B", "BW"),
	GOLD("gold general", "G", "WfF"),
	SILVER("silver general", "S", "FfW"),
	NARIGIN("promoted silver general", "+S", "WfF"),
	KNIGHT("knight", "N", "ffN"),
	NARIKEI("promoted knight", "+N", "WfF"),
	LANCE("lance", "L", "fR"),
	NARIKYOU("promoted lance", "+L", "WfF"),
	PAWN("pawn", "P", "fW"),
	TOKIN("promoted pawn", "+P", "WfF");

	private final String name;
	private final String abbrev;
	private final String movement;

	ShogiPieceType(String name, String abbrev, String movement) {
		this.name = name;
		this.abbrev = abbrev;
		this.movement = movement;
	}

	public String getName() {
		return name;
	}

	public String getAbbrev() {
		return abbrev;
	}

	public String getMovement() {
		return movement;
	}
}
