/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecardandboard.enums;

/**
 *
 * @author Jeffrey Hope
 */
public enum ShogiPieceType {
    KING("king", "k"),
    JEWEL("king", "K"),
    ROOK("rook", "R"),
    DRAGON("dragon", "+R"),
    BISHOP("bishop", "B"),
    HORSE("horse", "+B"),
    GOLD("gold general", "G"),
    SILVER("silver general", "S"),
    NARIGIN("promoted silver general", "+S"),
    KNIGHT("knight", "N"),
    NARIKEI("promoted knight", "+N"),
    LANCE("lance", "L"),
    NARIKYOU("promoted lance", "+L"),
    PAWN("pawn", "P"),
    TOKIN("promoted pawn", "+P");
    
    private final String name;
    private final String abbrev;
    
    ShogiPieceType(String name, String abbrev) {
        this.name = name;
        this.abbrev = abbrev;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAbbrev() {
        return abbrev;
    }
}
