package com.Slattery.Travis.Template;
import static com.Slattery.Travis.BlackJackGUI.*;

public class Tie extends GameTemplate {

    public void updateScoresAndBalances() {
        updateDisplay("Tie");
        updateBalance(betAmount, CalcType.ADD_BAL);
        updateBalanceDisplay();
        tieHands++;
    }
}