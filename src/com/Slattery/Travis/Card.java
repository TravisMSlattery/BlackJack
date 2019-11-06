/**
 * This is how the cards are created
 * <p>
 * Travis Slattery
 **/
package com.Slattery.Travis;


import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class Card {

    private Suit mySuit;
    private int myNumber;
    private ImageIcon cardImage;



    public Card(Suit aSuit, int aNumber) {

        this.mySuit = aSuit;
        this.myNumber = aNumber;
        String imageLink = "playingcards/" + myNumber + mySuit + ".png";
        cardImage = new ImageIcon(getClass().getResource(imageLink));

        if (aNumber >= 1 && aNumber <= 13) {
            this.myNumber = aNumber;
        } else {
            showMessageDialog(null, aNumber + " is not a valid number", "Not Valid", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ImageIcon getCardImage()
    {
        return cardImage;
    }


    // returns the number of the card
    public int getNumber() {
        return myNumber;
    }


    public String toString() {

        String numStr = "Error";

        switch (this.myNumber) {

            case 2:
                numStr = "2";
                break;
            case 3:
                numStr = "3";
                break;
            case 4:
                numStr = "4";
                break;
            case 5:
                numStr = "5";
                break;
            case 6:
                numStr = "6";
                break;
            case 7:
                numStr = "7";
                break;
            case 8:
                numStr = "8";
                break;
            case 9:
                numStr = "9";
                break;
            case 10:
                numStr = "10";
                break;
            case 11:
                numStr = "11";
                break;
            case 12:
                numStr = "12";
                break;
            case 13:
                numStr = "13";
                break;
            case 1:
                numStr = "1";
                break;
        }

        return numStr + mySuit.toString();
    }

}
