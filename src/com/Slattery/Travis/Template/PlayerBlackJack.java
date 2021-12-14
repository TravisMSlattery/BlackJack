package com.Slattery.Travis.Template;

import static com.Slattery.Travis.BlackJackGUI.*;

public class PlayerBlackJack extends GameTemplate {

    public void updateScoresAndBalances() {
        updateDisplay("BlackJack! You win");
        updateBalance(betAmount, CalcType.BJ_BAL);

    }
}