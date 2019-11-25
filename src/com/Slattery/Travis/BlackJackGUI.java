package com.Slattery.Travis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*#####################################
 * Blackjack Game 2.0
 * Travis Slattery
 * This is a one-player blackjack game
 * where the player plays against the
 * dealer. Said player sets an initial
 * balance, deals and then hits or
 * stands until either getting a black-
 * jack, winning, losing or tie game.
 * When out of money the option to
 * top up or end the game is given.
 #####################################*/

public class BlackJackGUI {

    private static JFrame frame = new GameWindow(); // Creating an instance of the MainFrame class.

    private static Deck deck, dealerCards, playerCards; //splitCards; //Declaring Variables:
    private static CardArray dealerCardPanel = null, playerCardPanel = null; //splitCardPanel = null; // The deck of cards, the dealer's cards, the player's cards, the panels for the player's and dealer's cards
    private static Card dealerHiddenCard; //  and the hidden card of the dealer.

    private static double balance = 0.0, sBalance = 0.0; //Setting balance
    private static int betAmount = 0, roundCount = 0, wonHands = 0, tieHands = 0;// the amount the player bets and the number of rounds hands won tied

    // Creating the GUI elements in the window builder
    private static JTextField balanceField;
    private static JButton startButton;
    private static JButton endButton;
    private static JTextField betAmountField;
    private static JButton dealButton;
    private static JLabel balanceLabel;
    private static JLabel dLabel;
    private static JLabel pLabel;
    private static JButton hitButton;
    private static JButton stayButton;
    private static JButton dblButton;
    //private static JButton splitButton;
    private static JLabel amountBet;
    private static JLabel lblBetAmountDesc;
    private static JLabel gameInfo;
    private static JButton continueButton;
    private static JLabel shuffleInfo = null;
    private static JLabel statsInfo = null;

    private static boolean validAmount(String s) { // This is to assure that the values entered for the initial balance and the player's bet are natural numbers.
        try {
            // Ensure amount entered is > 0
            return Integer.parseInt(s) > 0;
        } catch (NumberFormatException e) { // If not valid integer
            return false;
        }
    }

    // This function runs when the program starts or when the game ends. It displays the initial GUI objects to enter an initial balance and start/stop a game
    private static void loadGuiObjects() {
        startButton = new JButton("New Game"); // New game button
        startButton.addActionListener(e -> {
            newGame(); // Start game
        });
        startButton.setBounds(20, 610, 99, 50);
        frame.getContentPane().add(startButton);

        endButton = new JButton("End Game"); // End game button, this removes all GUI objects and starts from scratch
        endButton.setEnabled(false);
        endButton.setBounds(121, 610, 99, 50);
        endButton.addActionListener(e -> {
            frame.getContentPane().removeAll(); // Remove all objects from screen
            frame.repaint(); // Repaint to show update
            loadGuiObjects(); // Restart the game logic and display the New Game menu
        });
        frame.getContentPane().add(endButton);

        balanceField = new JTextField(); // Text field to store initial balance
        balanceField.setText("1000");
        balanceField.setBounds(131, 580, 89, 28);
        frame.getContentPane().add(balanceField);
        balanceField.setColumns(10);


        JLabel startBalance = new JLabel("Initial Balance:"); // Initial balance label
        startBalance.setFont(new Font("Arial", Font.BOLD, 13));
        startBalance.setForeground(Color.WHITE);
        startBalance.setBounds(30, 586, 100, 16);
        frame.getContentPane().add(startBalance);
    }

    private static void showBetGui() { // This runs when a new game is started. It initializes and displays the current balance label, deal amount and deal button

        endButton.setEnabled(true);

        JLabel currentBalance = new JLabel("Current Balance:"); // Current balance label
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

        JLabel enterBetLabel = new JLabel("Enter Bet:"); // Bet amount info label
        enterBetLabel.setFont(new Font("Arial", Font.BOLD, 14));
        enterBetLabel.setForeground(Color.WHITE);
        enterBetLabel.setBounds(689, 586, 100, 16);
        frame.getContentPane().add(enterBetLabel);

        dealButton = new JButton("Deal"); // Deal button
        dealButton.setBounds(679, 610, 200, 50);
        dealButton.addActionListener(e -> {
            deal(); // When clicked, deal
        });
        frame.getContentPane().add(dealButton);
        dealButton.requestFocus();
        sBalance = Double.parseDouble(balanceField.getText());

        frame.repaint();

    }


    private static void deal() { // Runs when the Deal button is pressed. Draws two player and dealer cards (only displaying one of the dealer's cards) and asks for an action from the player, or if there's an immediate outcome (eg. blackjack straight away), it takes action

        if (shuffleInfo != null) // (Every 25 rounds the deck is reshuffled and this label is displayed. Hide it when a new round is started
            frame.getContentPane().remove(shuffleInfo);
        if (statsInfo != null) // (Every 25 rounds the deck is reshuffled and this label is displayed. Hide it when a new round is started
            frame.getContentPane().remove(statsInfo);

        // Initialise dealer/player card arrays
        dealerCards = new Deck();
        playerCards = new Deck();

        if (validAmount(betAmountField.getText())) { // Parse bet amount given
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

        gameInfo.setText("Please Hit, Stand or Double"); // Next instruction

        dLabel = new JLabel("Dealer"); // Dealer label
        dLabel.setForeground(Color.WHITE);
        dLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        dLabel.setBounds(415, 10, 82, 28);
        frame.getContentPane().add(dLabel);

        pLabel = new JLabel("Player"); // Player label
        pLabel.setForeground(Color.WHITE);
        pLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
        pLabel.setBounds(415, 320, 82, 28);
        frame.getContentPane().add(pLabel);

        hitButton = new JButton("Hit"); // Hit button
        hitButton.setBounds(200, 515, 140, 35);
        hitButton.addActionListener(e -> {
            hit(); // When pressed, hit
        });
        frame.getContentPane().add(hitButton);
        hitButton.requestFocus();

        stayButton = new JButton("Stand"); // Stand button
        stayButton.setBounds(380, 515, 140, 35);
        stayButton.addActionListener(e -> {
            stand(); // When pressed, stand
        });
        frame.getContentPane().add(stayButton);


        dblButton = new JButton("Double"); // Stand button
        dblButton.setBounds(560, 515, 140, 35);
        dblButton.addActionListener(e -> {
            dblBet(); // When pressed, stand
        });
        frame.getContentPane().add(dblButton);

        //splitButton = new JButton("Split"); // Stand button
        //splitButton.setBounds(655, 515, 140, 35);
        //splitButton.addActionListener(new ActionListener() {
        //public void actionPerformed(ActionEvent e) {
        //	split(); // When pressed, split
        //}
        //});
        //frame.getContentPane().add(splitButton);

        continueButton = new JButton("Continue"); // When the final outcome is reached, press this to accept and continue the game
        continueButton.setEnabled(false);
        continueButton.setVisible(false);
        continueButton.setBounds(290, 444, 320, 35);
        continueButton.addActionListener(e -> {
            continueGame(); // Accept outcome
        });
        frame.getContentPane().add(continueButton);

        amountBet = new JLabel(); // Show bet amount
        amountBet.setText("€" + betAmount);
        amountBet.setHorizontalAlignment(SwingConstants.CENTER);
        amountBet.setForeground(Color.ORANGE);
        amountBet.setFont(new Font("Arial", Font.BOLD, 40));
        amountBet.setBounds(679, 458, 200, 50);
        frame.getContentPane().add(amountBet);

        lblBetAmountDesc = new JLabel("Bet Amount:"); // Bet amount info label
        lblBetAmountDesc.setHorizontalAlignment(SwingConstants.CENTER);
        lblBetAmountDesc.setForeground(Color.WHITE);
        lblBetAmountDesc.setFont(new Font("Arial", Font.BOLD, 16));
        lblBetAmountDesc.setBounds(689, 435, 190, 22);
        frame.getContentPane().add(lblBetAmountDesc);

        frame.repaint(); // Redraw frame to show changes

        dealerHiddenCard = deck.takeCard(); // Take a card from top of deck for dealer but hide it
        dealerCards.myCards.add(new Card("", "", 0)); // Add turned over card to dealer's cards
        dealerCards.myCards.add(deck.takeCard()); // Add card from top of deck to dealer's cards

        // Add two cards from top of deck to player's cards
        playerCards.myCards.add(deck.takeCard());
        playerCards.myCards.add(deck.takeCard());

        updateCardPanels(); // Display the two card panels

        gameOutcomes(); // Check for any automatic outcomes (i.e. immediate blackjack)

    }

    private static void hit() { // Add another card to player cards, show the new card and check for any outcomes

        playerCards.myCards.add(deck.takeCard());
        updateCardPanels();
        gameOutcomes();

    }
    private static void dblBet() { // Add another card to player cards, show the new card and check for any outcomes
        betAmount = betAmount*2;
        playerCards.myCards.add(deck.takeCard());
        updateCardPanels();

        int playerScore = playerCards.getTotalValue(); // Get player score as total of cards he has
        if (gameOutcomes()) // Check for any normal outcomes. If so, we don't need to do anything here so return.
            return;

        if (playerScore > 21 && playerCards.getNumAces() > 0) // If player has at least one ace and would otherwise lose (>21), subtract 10
            playerScore -= 10;

        dealerCards.myCards.set(0, dealerHiddenCard); // Replace hidden dealer's card with actual card

        int dealerScore = dealerCards.getTotalValue(); // Get dealer score as total of cards he has

        while (dealerScore < 17) { // If dealer's hand is < 17, he needs to get more cards until it's > 17
            dealerCards.myCards.add(deck.takeCard()); // Take a card from top of deck and add
            dealerScore = dealerCards.getTotalValue();
            if (dealerScore > 21 && dealerCards.getNumAces() > 0) // If there's an ace and total > 21, subtract 10
                dealerScore -= 10;
        }
        updateCardPanels(); // Display new dealer's cards

        // Determine final outcomes, give profits if so and display outcomes
        if (playerScore > dealerScore) { // Player wins
            gameInfo.setText("Player wins! Profit: €" + betAmount);
            balance += betAmount * 2;
            wonHands++;
        } else if (dealerScore == 21) { // Dealer blackjack
            gameInfo.setText("Dealer gets Blackjack! Loss: €" + betAmount);
            balance -= betAmount;
        } else if (dealerScore > 21) { // Dealer bust
            gameInfo.setText("Dealer goes Bust! Profit: €" + betAmount);
            balance += betAmount * 2;
        } else if (playerScore == dealerScore) { // Tie
            gameInfo.setText("Tie!");
            balance += betAmount;
            tieHands++;
        } else { // Otherwise - dealer wins
            gameInfo.setText("Dealer Wins! Loss: €" + betAmount);
            balance -= betAmount;
        }

        balanceLabel.setText(String.format("€%.2f", balance));
        gameOver(); // If something's happened, this round is over. Show the results of round and Continue button

    }

    public static boolean gameOutcomes() { // This runs automatically whenever deal is pressed or the player hits
        boolean gameHasFinished = false;
        int playerScore = playerCards.getTotalValue(); // Get player score as total of cards he has
        if (playerScore > 21 && playerCards.getNumAces() > 0) // If player has at least one ace and would otherwise lose (>21), subtract 10
            playerScore -= 10;

        if (playerScore == 21) { // Potential player blackjack

            dealerCards.myCards.set(0, dealerHiddenCard); // Replace hidden dealer's card with actual card
            updateCardPanels(); // Display new card
            if (dealerCards.getTotalValue() == 21) { // If dealer ALSO gets a blackjack
                gameInfo.setText("TIE!"); // tie game
                balance += betAmount; // Give bet back to player
            } else {
                // Player gets a blackjack only
                gameInfo.setText(String.format("Player gets Blackjack! Profit: €%.2f", (1.5f * betAmount)));
                balance +=  (1.5f * betAmount); // Add profits to balance
            }
            balanceLabel.setText(String.format("€%.2f", balance)); // Show new balance

            gameHasFinished = true;
            gameOver(); // If something's happened, this round is over. Show the results of round and Continue button
        } else if (playerScore > 21) { // If player goes bust
            gameInfo.setText("Player goes Bust! Loss: €" + betAmount);
            balance -= betAmount;
            balanceLabel.setText(String.format("€%.2f", balance));
            dealerCards.myCards.set(0, dealerHiddenCard); // Replace hidden dealer's card with actual card
            updateCardPanels();
            gameHasFinished = true;
            gameOver(); // If something's happened, this round is over. Show the results of round and Continue button
        }
        return gameHasFinished;

    }
    private static void stand() { // When stand button is pressed
        if (gameOutcomes()) // Check for any normal outcomes. If so, we don't need to do anything here so return.
            return;

        int playerScore = playerCards.getTotalValue(); // Get player score as total of cards he has
        if (playerScore > 21 && playerCards.getNumAces() > 0) // If player has at least one ace and would otherwise lose (>21), subtract 10
            playerScore -= 10;

        dealerCards.myCards.set(0, dealerHiddenCard); // Replace hidden dealer's card with actual card

        int dealerScore = dealerCards.getTotalValue(); // Get dealer score as total of cards he has

        while (dealerScore < 17) { // If dealer's hand is < 16, he needs to get more cards until it's > 17
            dealerCards.myCards.add(deck.takeCard()); // Take a card from top of deck and add
            dealerScore = dealerCards.getTotalValue();
            if (dealerScore > 21 && dealerCards.getNumAces() > 0) // If there's an ace and total > 21, subtract 10
                dealerScore -= 10;
        }
        updateCardPanels(); // Display new dealer's cards

        // Determine final outcomes, give profits if so and display outcomes
        if (playerScore > dealerScore) { // Player wins
            gameInfo.setText("Player wins! Profit: €" + betAmount);
            balance += betAmount * 2;
            wonHands++;
        } else if (dealerScore == 21) { // Dealer blackjack
            gameInfo.setText("Dealer gets Blackjack! Loss: €" + betAmount);
        } else if (dealerScore > 21) { // Dealer bust
            gameInfo.setText("Dealer goes Bust! Profit: €" + betAmount);
            balance += betAmount * 2;
        } else if (playerScore == dealerScore) { // tie game
            gameInfo.setText("TIE!");
            balance += betAmount;
            tieHands++;
        } else { // Otherwise - dealer wins
            gameInfo.setText("Dealer Wins! Loss: €" + betAmount);
        }
        balanceLabel.setText(String.format("€%.2f", balance));
        gameOver(); // If something's happened, this round is over. Show the results of round and Continue button

    }

    private static void gameOver() { //If something's happened, this round is over. Show the results of round and Continue button

        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
        dblButton.setEnabled(false);
        //splitButton.setEnabled(false);

        // continue button display and refocus
        gameInfo.setOpaque(true);
        gameInfo.setForeground(Color.RED);
        continueButton.setEnabled(true);
        continueButton.setVisible(true);
        continueButton.requestFocus();
    }




    private static void continueGame() { // When outcome is reached

        gameInfo.setOpaque(false);
        gameInfo.setForeground(Color.ORANGE);

        // Remove deal objects

        frame.getContentPane().remove(dLabel);
        frame.getContentPane().remove(pLabel);
        frame.getContentPane().remove(hitButton);
        frame.getContentPane().remove(stayButton);
        frame.getContentPane().remove(dblButton);
        //frame.getContentPane().remove(splitButton);
        frame.getContentPane().remove(amountBet);
        frame.getContentPane().remove(lblBetAmountDesc);
        frame.getContentPane().remove(continueButton);
        frame.getContentPane().remove(dealerCardPanel);
        frame.getContentPane().remove(playerCardPanel);
        gameInfo.setText("Please enter a bet and click Deal");
        betAmountField.setEnabled(true);
        dealButton.setEnabled(true);
        dealButton.requestFocus();
        frame.repaint();

        if (balance <= 0) { // If out of funds, either top up or end game
            int choice = JOptionPane.showOptionDialog(null, "You have run out of funds. Press Yes to add €1000, or No to end the current game.", "Out of funds", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (choice == JOptionPane.YES_OPTION) {
                balance += 1000;
                balanceLabel.setText(String.format("€%.2f", balance));
                sBalance += 1000;
            } else {
                frame.getContentPane().removeAll();
                frame.repaint();
                loadGuiObjects();
                return;
            }
        }

        roundCount++;
        // If 30 rounds, reinitialise the deck and reshuffle to prevent running out of cards
        if (roundCount >= 10) {
            deck.Shoe();
            deck.shuffle();
            float percentage = (float) (wonHands * 100) / (roundCount - tieHands);
            double profit = balance - sBalance;

            shuffleInfo = new JLabel("Deck has been replenished and reshuffled!");
            shuffleInfo.setBackground(new Color (32, 128, 0));
            shuffleInfo.setForeground(Color.ORANGE);
            shuffleInfo.setFont(new Font("Arial", Font.BOLD, 20));
            shuffleInfo.setHorizontalAlignment(SwingConstants.CENTER);
            shuffleInfo.setBounds(200, 100, 500, 42);
            frame.getContentPane().add(shuffleInfo);
            statsInfo = new JLabel("<html>You played " + roundCount + "<br>Starting balance " + String.format("€%.2f",sBalance) +
                    "<br>You Won " + wonHands + "<br>Tie Games " + tieHands + "<br>Your final balance is " + String.format("€%.2f", balance) +
                     "<br>Profit/Loss is " + String.format("€%.2f",profit) +
                    "<br>Your win percentage is " + String.format("%.0f",percentage)+"%" +"</html>", SwingConstants.CENTER);
            statsInfo.setForeground(Color.WHITE);
            statsInfo.setBackground(new Color (32, 128, 0));
            statsInfo.setFont(new Font("Arial", Font.BOLD, 30));
            statsInfo.setBounds(200, 100, 500, 500);
            frame.getContentPane().add(statsInfo);
            roundCount = 0;
        }
    }

    private static void newGame() { // When new game is started

        if (validAmount(balanceField.getText())) { // Check that balance is valid
            balance = Integer.parseInt(balanceField.getText());
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid balance! Please ensure it is a natural number.", "Error", JOptionPane.ERROR_MESSAGE);
            balanceField.requestFocus();
            return;
        }

        startButton.setEnabled(false);
        balanceField.setEnabled(false);

        showBetGui(); // Show bet controls

        roundCount = 0;

        deck = new Deck(); // Initialize dealer deck
        deck.Shoe(); // Add all the cards (default 6 decks)
        deck.shuffle(); // Shuffle

    }

    private static void updateCardPanels() { // Displays dealer and player cards as images
        if (dealerCardPanel != null) { // If they're already added, remove them
            frame.getContentPane().remove(dealerCardPanel);
            frame.getContentPane().remove(playerCardPanel);
            //frame.getContentPane().remove(splitCardPanel);
        }
        // Create and display two panels
        dealerCardPanel = new CardArray(dealerCards, 420 - (dealerCards.getCount() * 40), 50, 70, 104, 10);
        frame.getContentPane().add(dealerCardPanel);
        playerCardPanel = new CardArray(playerCards, 420 - (playerCards.getCount() * 40), 370, 70, 104, 10);
        frame.getContentPane().add(playerCardPanel);
        //splitCardPanel = new CardArray(splitCards, 420 - (splitCards.getCount() * 40), 150, 70, 104, 10);
        //frame.getContentPane().add(splitCardPanel);
        frame.repaint();
    }

    public static void main(String[] args) {

        // Start of program


        loadGuiObjects(); // Displays the initial GUI objects to enter an initial balance and start/stop a game

        frame.setVisible(true);

    }

    public static int heightFromWidth(int width) { // 500x726 original size, helper function to get height proportional to width
        return (int) (1f * width * (380f / 255f));
    }
}