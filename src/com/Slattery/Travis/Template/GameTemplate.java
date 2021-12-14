package com.Slattery.Travis.Template;
import com.Slattery.Travis.BlackJackGUI;

import static com.Slattery.Travis.BlackJackGUI.*;

public abstract class GameTemplate {
    double betAmount = BlackJackGUI.getBetAmount();
    double balance = BlackJackGUI.getBal();

   public void updateDisplay(String txt) {
        gameInfo.setText(txt);

    }

    public void updateBalance(double betAmount, CalcType type){
       System.out.println("Balance before update " + balance);
        System.out.println(type);
       balance = type.updateBalance(betAmount, balance);
       System.out.println("Balance after update " + balance);
    }

   public void  updateBalanceDisplay(){

   }
   abstract public void updateScoresAndBalances();


}


/*  TEMPLATE PATTERN IMPLEMENTATION

  CONTROLLER CLASS

  class BlackJackGUI
      method CheckForGameEnding
        if( dealer.score > 21 ){
          gameUpdate = new DealerBust
        } else if( player.score > 21 ){
          gameUpdate = new PlayerBust
        } else if( ... )

      ... hit ...
        CheckForGameEnding

      ... stand ...
        CheckForGameEnding

      ... double ...
        CheckForGameEnding


  CONCRETE CLASSES

  class Tie extends HandleGameEndings
    method updateScoresAndBalances()

  class DealerBlackjack extends HandleGameEndings
    method updateScoresAndBalances()

  class DealerBust extends HandleGameEndings
    method updateScoresAndBalances()

  class DealerWin extends HandleGameEndings
    method updateScoresAndBalances()

  class PlayerBlackjack extends HandleGameEndings
    method updateScoresAndBalances()

  class PlayerBust extends HandleGameEndings
    method updateScoresAndBalances()

  class PlayerWin extends HandleGameEndings
    method updateScoresAndBalances()


  ABSTRACT CLASS

  class HandleGameEndings
    method updatePlayerScore
    method updateDealerScore
    method updateBalanceDisplay()
    abstract method updateScoresAndBalances()

 */

/*  OBSERVER PATTERN IMPLEMENTATION

   When the game starts, instantiate the PlayerNotifier singleton

   class PlayerNotifier
     method warning( String message ) {
     }
     method error( String message ) {
     }

   In BlackJackGUI around line 283

     if( betAmount < minimumBetAmount ) {
       PlayerNotifier.warning( 'This table has a minimum bet of EUR ' + minimumBetAmount );
     }

     if( !validAmount( betAmount ) ) {
       PlayerNotifier.error( 'No fractions in your bets, please' );
     }

 */

/*  SINGLETON PATTERN IMPLEMENTATION

  When the game starts, try to touch a lock file /tmp/blackjack.pid

     String lockfileName = "/tmp/blackjack.lock";
     Path lockfilePath = new path( lockfileName );
     if( fileAlreadyExists( lockfilePath ) ) {
       PlayerNotifier.error( 'You cannot run two games at the same time' );
     }

 */