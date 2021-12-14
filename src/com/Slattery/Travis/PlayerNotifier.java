package com.Slattery.Travis;

import java.awt.Component;
import javax.swing.JOptionPane;

public class PlayerNotifier {
    public PlayerNotifier() {
    }


    static BlackJackGUI black =new BlackJackGUI();

    public static void warning() {
        JOptionPane.showMessageDialog((Component)null, "This table has a minimum bet of €100", "WARNING!", 2);
        BlackJackGUI.betAmountField.requestFocus();
        black.continueGame();
    }

    public static void error() {
        JOptionPane.showMessageDialog((Component)null, "You cannot bet fractions", "ERROR", 0);
        BlackJackGUI.betAmountField.requestFocus();
        black.continueGame();
    }

    public static void funds() {
        JOptionPane.showMessageDialog((Component)null, "You cannot bet more than your balance", "ERROR", 0);
        BlackJackGUI.betAmountField.requestFocus();
        black.continueGame();
    }
    public static void insufficientfunds() {
        JOptionPane.showMessageDialog((Component)null, "You need to deposit more that the minimum bet", "ERROR", 0);
        BlackJackGUI.betAmountField.requestFocus();

        black.loadGame();
    }
}
