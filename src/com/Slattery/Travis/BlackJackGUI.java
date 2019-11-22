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

    private static double balance = 0.0; // Setting the initial amounts for the Balance,
    private static int betAmount = 0, roundCount = 0; // the amount the player bets and the number of rounds.

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
                //newGame(); // Start game
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

    public static boolean isValidAmount(String s) { // This is to assure that the values entered for the initial balance and the player's bet are natural numbers.
        try {
            if (Integer.parseInt(s) > 0) // Ensure amount entered is > 0
                return true;
            else
                return false;
        } catch (NumberFormatException e) { // If not valid integer
            return false;
        }
    }


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
    public static void showBetGui() { // This runs when a new game is started. It initializes and displays the current balance label, deal amount and deal button

        endButton.setEnabled(true);

        currentBalance = new JLabel("Current Balance:"); // Current balance label
        currentBalance.setHorizontalAlignment(SwingConstants.CENTER);
        currentBalance.setFont(new Font("Arial", Font.BOLD, 16));
        currentBalance.setForeground(Color.WHITE);
        currentBalance.setBounds(315, 578, 272, 22);
        frame.getContentPane().add(currentBalance);

        balanceLabel = new JLabel(); // Balance label, shows current balance
        balanceLabel.setText(String.format("€%.2f", balance));
        balanceLabel.setForeground(Color.ORANGE);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 40));
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        balanceLabel.setBounds(315, 600, 272, 50);
        frame.getContentPane().add(balanceLabel);

        gameInfo = new JLabel("Please enter a bet and click Deal"); // Deal info label
        gameInfo.setBackground(Color.ORANGE);
        gameInfo.setOpaque(false);
        gameInfo.setForeground(Color.ORANGE);
        gameInfo.setFont(new Font("Arial", Font.BOLD, 16));
        gameInfo.setHorizontalAlignment(SwingConstants.CENTER);
        gameInfo.setBounds(290, 482, 320, 28);
        frame.getContentPane().add(gameInfo);

        betAmountField = new JTextField(); // Bet amount text field
        betAmountField.setText("");
        betAmountField.setBounds(790, 580, 89, 28);
        frame.getContentPane().add(betAmountField);

        enterBetLabel = new JLabel("Enter Bet:"); // Bet amount info label
        enterBetLabel.setFont(new Font("Arial", Font.BOLD, 14));
        enterBetLabel.setForeground(Color.WHITE);
        enterBetLabel.setBounds(689, 586, 100, 16);
        frame.getContentPane().add(enterBetLabel);

        dealButton = new JButton("Deal"); // Deal button
        dealButton.setBounds(679, 610, 200, 50);
        dealButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deal(); // When clicked, deal
            }
        });
        frame.getContentPane().add(dealButton);
        dealButton.requestFocus();

        frame.repaint();

    }

    public static void deal() { // Runs when the Deal button is pressed. Draws two player and dealer cards (only displaying one of the dealer's cards) and asks for an action from the player, or if there's an immediate outcome (eg. blackjack straight away), it takes action

        if (shuffleInfo != null) // (Every 30 rounds the deck is reshuffled and this label is displayed. Hide it when a new round is started
            frame.getContentPane().remove(shuffleInfo);

        // Initialise dealer/player card arrays
        dealerCards = new Deck();
        playerCards = new Deck();

        if (isValidAmount(betAmountField.getText()) == true) { // Parse bet amount given
            betAmount = Integer.parseInt(betAmountField.getText());
        } else {
            gameInfo.setText("Error: Bet must be a whole number!"); // Give an error
            betAmountField.requestFocus();
            return;
        }

        if (betAmount > balance) { // If bet is higher than balance
            gameInfo.setText("Error: Bet higher than balance!"); // Give an error
            betAmountField.requestFocus();
            return;
        }
        balance -= betAmount; // Subtract bet from balance

        balanceLabel.setText(String.format("€%.2f", balance));

        betAmountField.setEnabled(false);
        dealButton.setEnabled(false);

    }


    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        // Start of program

        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");


        initGuiObjects(); // Displays the initial GUI objects to enter an initial balance and start/stop a game

        frame.setVisible(true);

    }
}




