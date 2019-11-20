package com.Slattery.Travis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackJackGUI {
    private static JFrame frame = new GameWindow(); // Creating an instance of the MainFrame class.

    private static Deck deck, dealerCards, playerCards; //Declaring Variables:
    private static CardArray dealerCardPanel = null, playerCardPanel = null; // The deck of cards, the dealer's cards, the player's cards, the panels for the player's and dealer's cards
    private static Card dealerHiddenCard;


    // Creating the GUI elements in the window builder
    private static JTextField balanceField;
    private static JLabel startBalance;
    private static JButton startButton;
    private static JButton endButton;
    private static JTextField betAmountField;
    private static JLabel enterBetLabel;
    private static JButton dealButton;
    private static JLabel currentBalance;
    private static JLabel balanceLabel;
    private static JLabel dLabel;
    private static JLabel pLabel;
    private static JButton hitButton;
    private static JButton stayButton;
    private static JButton dblButton;
    private static JLabel amountBet;
    private static JLabel lblBetAmountDesc;
    private static JLabel gameInfo;
    private static JButton continueButton;
    private static JLabel shuffleInfo = null;

    //array of cards
    //Card[] myCards;


    //info Dialogs


    // This function runs when the program starts or when the game ends. It displays the initial GUI objects to enter an initial balance and start/stop a game
    public static void initGuiObjects() {
        startButton = new JButton("New Game"); // New game button
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        startButton.setBounds(20, 610, 99, 50);
        frame.getContentPane().add(startButton);

        endButton = new JButton("End Game"); // End game button, this removes all GUI objects and starts from scratch
        endButton.setEnabled(false);
        endButton.setBounds(121, 610, 99, 50);
        endButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll(); // Remove all objects from screen
                frame.repaint(); // Repaint to show update
                initGuiObjects(); // Restart the game logic and display the New Game menu
            }
        });
        frame.getContentPane().add(endButton);

        balanceField = new JTextField(); // Text field to store initial balance
        balanceField.setText("1000");
        balanceField.setBounds(131, 580, 89, 28);
        frame.getContentPane().add(balanceField);
        balanceField.setColumns(10);

        startBalance = new JLabel("Initial Balance:"); // Initial balance label
        startBalance.setFont(new Font("Arial", Font.BOLD, 13));
        startBalance.setForeground(Color.WHITE);
        startBalance.setBounds(30, 586, 100, 16);
        frame.getContentPane().add(startBalance);
    }


    public class Board extends JPanel {

        public void paintComponent(Graphics g) {
            g.setColor(colorBackground);
            g.fillRect(0, 0, aW, aH);

            //temp grid
            g.setColor(Color.black);
            g.drawRect(gridX, gridY, gridW, gridH);
            // temp game info box
            g.setColor(Color.black);
            g.drawRect(gameInfoX, gameInfoY, gameInfoW, gameInfoH);

            // temp info dialog
            g.drawRect(infoX, infoY, infoW, infoH);


            //}
            //for(int i = 0; i < 5; i++){


            //  g.drawRect(gridX+i*cardTW + cardSpacing,gridY+cardSpacing,cardAW,cardAH);
            //  g.drawRect(gridX+i*cardTW + cardSpacing,gridY+cardSpacing+cardTH,cardAW,cardAH);
            // }


        }


    }
}

}




