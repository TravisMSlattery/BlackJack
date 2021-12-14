package com.Slattery.Travis.Observer;

import com.Slattery.Travis.BlackJackGUI;

import java.awt.Component;
import javax.swing.JOptionPane;

public class PlayerNotifier {
    public PlayerNotifier() {
    }


    static BlackJackGUI black =new BlackJackGUI();

    public static void warning( String message ) {
        JOptionPane.showMessageDialog((Component)null, message, "WARNING!", 2);
        BlackJackGUI.betAmountField.requestFocus();
        black.continueGame();
    }

    public static void error( String message ) {
        JOptionPane.showMessageDialog((Component)null, message, "ERROR", 0);
        BlackJackGUI.betAmountField.requestFocus();
        black.continueGame();
    }

    public static void insufficientfunds(String message) {
        JOptionPane.showMessageDialog((Component)null, message, "ERROR", 0);
        //BlackJackGUI.betAmountField.requestFocus();
        black.loadGame();
    }
}
