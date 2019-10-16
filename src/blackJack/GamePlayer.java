package blackJack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.*;

import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;


public class GamePlayer {

    Player dealer = new Player("Dealer");
    Player me = new Player("Travis");
    GamePlayer myGame = new GamePlayer();

    int myBalance = me.getBalance();
    Deck theDeck = new Deck(6, true);
    int choice;

    public GamePlayer() {
        JFrame jFrameWindow;
        jFrameWindow = new JFrame("M.T Pockets Casino");
        FlowLayout flowLayout = new FlowLayout();
        jFrameWindow.setLayout(flowLayout);
        jFrameWindow.setSize(720, 720);
        jFrameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel dealerLabel = new JLabel("Dealer");
        jFrameWindow.add(dealerLabel);
        JLabel playerLabel = new JLabel("Player");
        jFrameWindow.add(playerLabel);
        JButton hitButton = new JButton("Hit!");
        jFrameWindow.add(hitButton);
        JButton stayButton = new JButton("Stick!");
        jFrameWindow.add(stayButton);
        JButton dblButton = new JButton("Double!");
        jFrameWindow.add(dblButton);
        JButton splitButton = new JButton("Split!");
        jFrameWindow.add(splitButton);
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

    } // end main

    class ButtonEventHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            while (me.getBalance() != 0) {
                choice = showConfirmDialog(null, "You must bet or leave ", "Bet or Leave", YES_NO_OPTION);

                if (choice != 1) {

                    //deal cards
                    me.addCard(theDeck.dealNextCard());
                    dealer.addCard(theDeck.dealNextCard());
                    me.addCard(theDeck.dealNextCard());
                    dealer.addCard(theDeck.dealNextCard());
                    dealer.setHands();
                    me.setHands();
                    Object hitButton = null;
                    Object dblButton = null;
                    Object stayButton = null;
                    Object splitButton = null;


                    //print hands
                    showMessageDialog(null, "Cards are dealt\n", "", INFORMATION_MESSAGE);
                    me.printHand(true);
                    dealer.printHand(false);
                    showMessageDialog(null, "\n", "", INFORMATION_MESSAGE);
                    if (me.getHandSum() == 21 && dealer.getHandSum() != 21) {
                        me.incBalance();
                        me.setWins();
                        dealer.setDraws();
                        showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                , "You Win!", INFORMATION_MESSAGE);
                        boolean meDone = true;
                        me.emptyHand();
                        dealer.emptyHand();
                    } else {
                        boolean meDone = false;
                        boolean dealerDone = false;


                        while (!meDone || !dealerDone) {

                            if (!meDone) {
                                showMessageDialog(null, "\nYou have: " + me.getHandSum(), "Game Play", INFORMATION_MESSAGE);
                                showInputDialog("Hit, Stay, Double or Split? (Enter H, S, D or Sp)", INFORMATION_MESSAGE);

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

                        while (e.getSource() == dblButton) {

                            if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                                me.dIncBalance();
                                me.setWins();
                                dealer.setLose();
                                showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                        , "You Win!", INFORMATION_MESSAGE);
                            } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                                me.dDicBalance();
                                me.setLose();
                                dealer.setLose();
                                showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                        , "You Lose!", INFORMATION_MESSAGE);
                            } else {
                                me.setDraws();
                                dealer.setDraws();
                                showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                        , "Tie!", INFORMATION_MESSAGE);
                            }

                            me.emptyHand();
                            dealer.emptyHand();
                        }

                        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                            me.incBalance();
                            me.setWins();
                            dealer.setLose();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nYou Win!" + "\nYour Balance is €" + me.getBalance()
                                    , "You Win!", INFORMATION_MESSAGE);
                        } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                            me.dicBalance();
                            me.setLose();
                            dealer.setWins();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nYou Lose!" + "\nYour Balance is €" + me.getBalance()
                                    , "You Lose!", INFORMATION_MESSAGE);
                        } else {
                            me.setDraws();
                            dealer.setDraws();
                            showMessageDialog(null, "\nYou have: " + me.getHandSum() + "\nDealer has: " + dealer.getHandSum() + "\nIt's a Tie!" + "\nYour Balance is €" + me.getBalance()
                                    , "Tie!", INFORMATION_MESSAGE);
                        }
                        me.emptyHand();
                        dealer.emptyHand();


                    }

                } else System.exit(0);
            }
        }
    }// button handler
}


