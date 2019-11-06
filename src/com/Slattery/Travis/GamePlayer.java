package com.Slattery.Travis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;


public class GamePlayer extends JFrame {
    Player dealer = new Player("Dealer");
    Player me = new Player("Travis");
    Deck theDeck = new Deck(6, true);
    int choice;
    int myBalance = me.getBalance();
    int myHandsWon = me.getHandsWon();
    int myHandsLost = me.getHandsLost();
    int myHandsDraws = me.getHandsDraws();
    JTextArea dealerText, playerText, statsText;


    public GamePlayer() {
        JFrame jFrameWindow;
        jFrameWindow = new JFrame("M.T Pockets Casino");
        JFrame frame = new JFrame("Border Layout");
        jFrameWindow.setSize(720, 720);
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel dealerLabel = new JLabel("Dealer");
        jFrameWindow.add(dealerLabel);
        JLabel playerLabel = new JLabel("Player");
        jFrameWindow.add(playerLabel);
        JButton hitButton = new JButton("Hit!");
        JButton stayButton = new JButton("Stick!");
        JButton dblButton = new JButton("Double!");
        JButton splitButton = new JButton("Split!");
        jFrameWindow.add(hitButton,BorderLayout.SOUTH);
        jFrameWindow.add(stayButton, BorderLayout.SOUTH);
        jFrameWindow.add(dblButton, BorderLayout.SOUTH);
        jFrameWindow.add(splitButton, BorderLayout.SOUTH);
        ButtonEventHandler hitHandler = new ButtonEventHandler();
        hitButton.addActionListener(hitHandler);
        ButtonEventHandler stayHandler = new ButtonEventHandler();
        stayButton.addActionListener(stayHandler);
        ButtonEventHandler dblHandler = new ButtonEventHandler();
        dblButton.addActionListener(dblHandler);
        ButtonEventHandler splitHandler = new ButtonEventHandler();
        splitButton.addActionListener(splitHandler);

        jFrameWindow.setVisible(true);

    }//end gui constructor


    public static void main(String[] args) {

        GamePlayer myGame = new GamePlayer();
    } // end main

    class ButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            while (me.getBalance() != 0) {


                //deal cards
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());

                //print hands
                // showMessageDialog(null, "Cards are dealt\n", "", INFORMATION_MESSAGE);
                me.printHand(true);
                dealer.printHand(false);
                showMessageDialog(null, "\n", "", INFORMATION_MESSAGE);
                if (me.getHandSum() == 21 && dealer.getHandSum() != 21) {
                    me.incBalance();
                    me.incHandsWon();
                    showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                            , "You Win!", INFORMATION_MESSAGE);
                    boolean meDone = true;
                    me.emptyHand();
                    dealer.emptyHand();
                } else {
                    boolean meDone = false;
                    boolean dealerDone = false;


                    while (!meDone || !dealerDone) {
                        Object hitButton = null;
                        Object dblButton = null;
                        Object stayButton = null;
                        Object splitButton = null;

                        if (!meDone) {
                            showMessageDialog(null, "\nYou have: " + me.getHandSum(), "Game Play", INFORMATION_MESSAGE);

                            if (e.getSource() == hitButton) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHand(true);

                            } else if (e.getSource() == dblButton) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHand(true);
                                meDone = true;
                            } else if (e.getSource() == stayButton) {
                                meDone = true;
                            }
                        }

                        if (!dealerDone) {

                            if (dealer.getHandSum() < 17) {
                                showMessageDialog(null, "Dealer Hits", "Dealer Hits!", INFORMATION_MESSAGE);
                                dealerDone = !dealer.addCard(theDeck.dealNextCard());
                                dealer.printHand(false);

                            } else {
                                showMessageDialog(null, "Dealer Sticks", "Dealer Sticks!", INFORMATION_MESSAGE);
                                dealer.printHand(true);
                                dealerDone = true;
                            }
                        }
                    }


                    me.printHand(true);
                    dealer.printHand(true);

                    int mySum = me.getHandSum();
                    int dealerSum = dealer.getHandSum();
                    Object dblButton = null;

                    while (e.getSource() == dblButton) {

                        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                            me.dIncBalance();
                            me.incHandsWon();
                            dealer.incHandsLost();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                            + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                    , "You Win!", INFORMATION_MESSAGE);
                        } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                            me.dDicBalance();
                            me.incHandsLost();
                            dealer.incHandsWon();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                            + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                    , "You Lose!", INFORMATION_MESSAGE);
                        } else {
                            me.incHandsDraws();
                            dealer.incHandsDraws();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                            + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                    , "Tie!", INFORMATION_MESSAGE);
                        }

                        me.emptyHand();
                        dealer.emptyHand();
                    }

                    if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                        me.incBalance();
                        me.incHandsWon();
                        dealer.incHandsLost();
                        showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                        + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                , "You Win!", INFORMATION_MESSAGE);
                    } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                        me.dicBalance();
                        me.incHandsLost();
                        dealer.incHandsWon();
                        showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                        + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                , "You Lose!", INFORMATION_MESSAGE);
                    } else {
                        me.incHandsDraws();
                        dealer.incHandsDraws();
                        showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                        + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                                , "Tie!", INFORMATION_MESSAGE);
                    }
                    me.emptyHand();
                    dealer.emptyHand();


                }


            }
        }
    }// button handler
}

