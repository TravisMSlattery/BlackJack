package com.Slattery.Travis.Template;
import com.Slattery.Travis.BlackJackGUI;

import static com.Slattery.Travis.BlackJackGUI.*;

public abstract class GameTemplate {
    double betAmount = BlackJackGUI.getBetAmount();
    double balance = BlackJackGUI.getBal();

   public void updateDisplay(String txt) {
        gameInfo.setText(txt);
    }
    public double updateBalance(double betAmount, CalcType type){
       balance = type.updateBalance(betAmount, balance);
        return balance;
    }
    public void updateBalanceDisplay(){
        balanceLabel.setText(String.valueOf(balance));
    }
   abstract public void updateScoresAndBalances();


}
