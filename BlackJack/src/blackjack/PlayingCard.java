/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JLabel;

/**
 *
 * @author lenovo
 */
public class PlayingCard {
     public static enum ShapeTypes{spade,diamond,clubs,hearts};
    public int Value;
    public ShapeTypes Shape;
    public String CardName;
    public String ImageName;
    public JLabel Holder;
    public String [] shapes={"spades","hearts","diamonds","clubs"};  
    
}
