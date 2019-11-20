package com.Slattery.Travis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends BlackJackGUI {
    Player dealer = new Player("Dealer");
    Player me = new Player("Travis");
    Deck theDeck = new Deck(6, true);
    //JTextArea gameInfo = new JTextArea("Welcome to M.T Pockets Casino");
    int myBalance = me.getBalance();
    int myHandsWon = me.getHandsWon();
    int myHandsLost = me.getHandsLost();
    int myHandsDraws = me.getHandsDraws();


    public void actionPerformed(ActionEvent e) {
        gameInfo.setText("Welcome to M.T Pockets Casino");

            while(me.getBalance()!=0)

            {


                //deal cards
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());

                //print hands
                //System.out.print( "Cards are dealt\n");
                me.printHandP(true);
                dealer.printHandD(false);
                //System.out.print( "\n");
                if (me.getHandSum() == 21 && dealer.getHandSum() != 21) {
                    me.incBalance();
                    me.incHandsWon();
                    gameInfo.setText("\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance());
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
                        //Object splitButton = null;

                        if (!meDone) {
                            gameInfo.setText("\nYou have: " + me.getHandSum());

                            if (e.getSource() == hitButton) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHandP(true);

                            } else if (e.getSource() == dblButton) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHandP(true);
                                meDone = true;
                            } else if (e.getSource() == stayButton) {
                                meDone = true;
                            }
                        }

                        if (!dealerDone) {

                            if (dealer.getHandSum() < 17) {
                                gameInfo.setText("Dealer Hits");
                                dealerDone = !dealer.addCard(theDeck.dealNextCard());
                                dealer.printHandD(false);

                            } else {
                                gameInfo.setText("Dealer Sticks");
                                dealer.printHandD(true);
                                dealerDone = true;
                            }
                        }
                    }


                    me.printHandP(true);
                    dealer.printHandD(true);

                    int mySum = me.getHandSum();
                    int dealerSum = dealer.getHandSum();
                    // Object dblButton = null;


                    while (e.getSource() == dblButton) {

                        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                            me.dIncBalance();
                            me.incHandsWon();
                            dealer.incHandsLost();
                            gameInfo.setText("\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                    + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                            );
                        } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                            me.dDicBalance();
                            me.incHandsLost();
                            dealer.incHandsWon();
                            gameInfo.setText("\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                    + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                            );
                        } else {
                            me.incHandsDraws();
                            dealer.incHandsDraws();
                            gameInfo.setText("\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                    + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                            );
                        }

                        me.emptyHand();
                        dealer.emptyHand();
                    }

                    if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                        me.incBalance();
                        me.incHandsWon();
                        dealer.incHandsLost();
                        gameInfo.setText("\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                        );
                    } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                        me.dicBalance();
                        me.incHandsLost();
                        dealer.incHandsWon();
                        gameInfo.setText("\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                        );
                    } else {
                        me.incHandsDraws();
                        dealer.incHandsDraws();
                        gameInfo.setText("\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                + "\nYour hands played is " + me.getHandsPlayed() + "\nYour hands won is " + me.getHandsWon() + "\nYour hands lost is " + me.getHandsLost() + "\nYour hands tied is " + me.getHandsDraws()
                        );
                    }
                    me.emptyHand();
                    dealer.emptyHand();


                }


            }
        }
    }

