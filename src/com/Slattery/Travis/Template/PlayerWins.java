package com.Slattery.Travis.Template;

import static com.Slattery.Travis.BlackJackGUI.*;

public class PlayerWins extends GameTemplate {


    public void updateScoresAndBalances() {
        updateDisplay("Player Wins");
        System.out.println("Player WIns bet amount: " + betAmount);
        updateBalance(betAmount, CalcType.WIN_BAL);

    }
}