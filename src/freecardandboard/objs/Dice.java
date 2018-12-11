/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecardandboard.objs;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Dice {
    private int[] dice;
    private final int SIDES;
    
    /**
     * Creates a number of dice.
     * 
     * @param number the number of dice to create
     * @param sides the number of sides per die
     */
    public Dice(int number, int sides) {
        this.dice = new int[number];
        this.SIDES = sides;
    }
    
    /**
     * Creates a number of six-sided dice.
     * 
     * @param number 
     */
    public Dice(int number) {
        this(number, 6);
    }
    
    /**
     * Creates two six-sided dice.
     */
    public Dice() {
        this(2, 6);
    }
    
    public int[] getDice() {
        int[] theseDice = new int[dice.length];
        
        System.arraycopy(dice, 0, theseDice, 0, dice.length);
        
        return theseDice;
    }
    
    /**
     * 
     * @param index the die from which to return the face
     * @return the face value
     */
    public int getDieFace(int index) {
        return dice[index];
    }
    
    /**
     * 
     * @return the sum of the dice 
     */
    public int getTotal() {
        int total = 0;
        
        for (int i = 0; i < dice.length; i++)
            total += getDieFace(i);
        
        return total;
    }
    
    /**
     * Rolls the dice.
     */
    public void rollDice() {
        for (int i = 0; i < dice.length; i++)
            dice[i] = (int) (Math.random() * SIDES + 1);
    }
}
