package com.Slattery.Travis.Template;


public class DealerWins extends GameTemplate {

    public void updateScoresAndBalances() {
        updateDisplay("Dealer Wins");
        updateBalance(betAmount, CalcType.SUBTRACT_BAL);
    }
}