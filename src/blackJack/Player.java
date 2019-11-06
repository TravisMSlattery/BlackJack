package blackJack;


import static javax.swing.JOptionPane.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Player {

    private String name;

    private Card[] hand = new Card[10];

    public int numCards;
    public int balance;
    private int splitCount = 0;


    private int handsPlayed;
    private int handsWon;
    private int handsLost;
    private int handsDraws;
    private double winprecent;

    public Player(String aName) {
        balance = 1000;
        this.name = aName;
        this.emptyHand();
        handsPlayed = 0;
        handsWon = 0;
        handsLost = 0;
        handsDraws = 0;

        // this.secondHand();
    }

    public void emptyHand() {

        for (int c = 0; c < 10; c++) {
            this.hand[c] = null;

        }
        this.numCards = 0;
    }

    public boolean addCard(Card aCard) {

        if (this.numCards == 10) {
            showMessageDialog(null, "%s' hand already has 10 cards cannot add another card\n" + this.name, "", INFORMATION_MESSAGE);

            System.exit(1);
        }

        this.hand[this.numCards] = aCard;
        this.numCards++;

        return (this.getHandSum() <= 21);

    }

    public int getHandSum() {

        int handSum = 0;
        int cardNum;
        int numAces = 0;

        for (int c = 0; c < this.numCards; c++) {

            cardNum = this.hand[c].getNumber();

            if (cardNum == 1) {
                numAces++;
                handSum += 11;
            } else if (cardNum > 10) {
                handSum += 10;
            } else {
                handSum += cardNum;
            }

        }

        while (handSum > 21 && numAces > 0) {
            handSum -= 10;
            numAces--;
        }

        return handSum;
    }
    public int getSplitCount() {
        return splitCount;
    }

    /**
     * @return Whether or not the deck has been split
     */
    public boolean hasBeenSplit() {
        return (getSplitCount() != 0);
    }

    /**
     * @return Whether or not the deck can be split (only two cards, both same face, hasn't already been split twice
     */
    public boolean canSplit() {
        return (numCards == 2 && this.hand[0].getNumber() == this.hand[1].getNumber() && getSplitCount() < 2);
    }



    public void printHand(boolean showFirstCard) {
        //showMessageDialog(null, this.name + "%s cards :\n", "", INFORMATION_MESSAGE);
        for (int c = 0; c < this.numCards; c++) {
            if (c == 0 && !showFirstCard) {
                showMessageDialog(null, "    [HIDDEN]", "", INFORMATION_MESSAGE);
            } else {
                showMessageDialog(null, " \n" + this.hand[c].toString(), "", INFORMATION_MESSAGE);

            }
        }
    }

    //public int playerBalance(int balance){
   /* public int playerBalance(){
       // this.balance = balance;
        int mySum = getHandSum();
        int dealerSum = getHandSum();

        if (mySum > dealerSum ){
            this.balance += 100;
        }else if (mySum == dealerSum && mySum <= 21 && dealerSum <= 21){
            this.balance = balance;
        } else {
            this.balance -= 100;

        }

        return this.balance;

        //this method
    }*/
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void incBalance() {
        this.balance = balance + 100;
    }

    public void dicBalance() {
        this.balance = balance - 100;
    }

    public void dIncBalance() {
        this.balance = balance + 200;
    }

    public void dDicBalance() {
        this.balance = balance - 200;
    }



    public void setHandsPlayed(int handsPlayed) {
        this.handsPlayed = handsPlayed;
    }
    public void incHandsPlayed() {
        this.handsPlayed = handsPlayed++;
    }

    public int getHandsPlayed() {
        return handsPlayed;
    }

    public void setHandsWon(int handsWon) {
        this.handsWon = handsWon;
    }
    public void incHandsWon() {
        this.handsWon = handsWon++;
    }

    public int getHandsWon() {
        return handsWon;
    }

    public void setHandsLost(int handsLost) {
        this.handsLost = handsLost;
    }
    public void incHandsLost() {
        this.handsLost = handsLost++; }

    public int getHandsLost() {
        return handsLost;
    }

    public void setHandsDraws(int handsDraws) {
        this.handsDraws = handsDraws;
    }
    public void incHandsDraws() {
        this.handsDraws = handsDraws;
    }

    public int getHandsDraws() {
        return handsDraws;
    }





    public double getWinprecent() {
        return winprecent;
    }

    public void setWinprecent(double winprecent) {
        this.winprecent = winprecent;
    }


}
