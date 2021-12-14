package com.Slattery.Travis.Template;

import static com.Slattery.Travis.BlackJackGUI.*;

public class PlayerWins extends GameTemplate {


    public void updateScoresAndBalances() {
        updateDisplay("Player Wins");
        updateBalance(betAmount, CalcType.WIN_BAL);
        updateBalanceDisplay();
        System.out.println(balance);
        wonHands++;
    }
}