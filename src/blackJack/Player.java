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


    private int hands;
    private int wins;
    private int lose;
    private int draws;
    private double winprecent;

    public Player(String aName) {
        balance = 1000;
        this.name = aName;
        this.emptyHand();
        hands = 0;
        wins = 0;
        lose = 0;
        draws = 0;

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


    public void printHand(boolean showFirstCard) {
        showMessageDialog(null, this.name + "%s cards :\n", "", INFORMATION_MESSAGE);
        for (int c = 0; c < this.numCards; c++) {
            if (c == 0 && !showFirstCard) {
                showMessageDialog(null, "    [HIDDEN]", "", INFORMATION_MESSAGE);
            } else {
                showMessageDialog(null, "    %s\n" + this.hand[c].toString(), "", INFORMATION_MESSAGE);

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


    public int getHands() {
        return hands;
    }

    public void setHands() {
        this.hands = hands + 1;
    }

    public int getWins() {
        return wins;
    }

    public void setWins() {
        this.wins = wins + 1;
    }

    public int getLose() {
        return lose;
    }

    public void setLose() {
        this.lose = lose + 1;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws() {
        this.draws = draws + 1;
    }

    public double getWinprecent() {
        return winprecent;
    }

    public void setWinprecent(double winprecent) {
        this.winprecent = winprecent;
    }


}
