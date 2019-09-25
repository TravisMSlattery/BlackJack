package blackJack;
import java.util.Scanner;


public class GamePlayer {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Deck theDeck = new Deck(6, true);

        Player dealer = new Player("Dealer");
        Player me = new Player("Travis");

        //deal cards
        dealer.addCard(theDeck.dealNextCard());
        me.addCard(theDeck.dealNextCard());
        dealer.addCard(theDeck.dealNextCard());
        me.addCard(theDeck.dealNextCard());


        //print hands
        System.out.println("Cards are dealt\n");
        me.printHand(true);
        dealer.printHand(false);
        System.out.println("\n");

        boolean meDone = false;
        boolean dealerDone = false;
        String ans;

        while(!meDone || !dealerDone){

            if(!meDone){

                System.out.println("You have: " + me.getHandSum() + "\n");
                System.out.print("Hit or Stay? (Enter H or S): ");
                ans = sc.next();

                if(ans.compareToIgnoreCase("H")== 0){
                    meDone = !me.addCard(theDeck.dealNextCard());
                    me.printHand(true);


                } else {
                    meDone = true;
                }
            }
            if(!dealerDone){

                if (dealer.getHandSum() < 17) {
                    System.out.println("Dealer Hits\n");
                    dealerDone = !dealer.addCard(theDeck.dealNextCard());
                    dealer.printHand(false);

                } else {
                    System.out.println("Dealer Stays\n");
                    dealerDone = true;
                }
                }
            System.out.println();
            }
        sc.close();

        me.printHand(true);
        dealer.printHand(true);

        int mySum = me.getHandSum();
        int dealerSum = dealer.getHandSum();

        if(mySum > dealerSum && mySum <=21 || dealerSum > 21){
            System.out.println("\nYou Win!");
        }else if((mySum == dealerSum) && (mySum <=21 && dealerSum <= 21) && (!meDone && !dealerDone)) {
            System.out.println("A TIE!");
        }else{
            System.out.println("\nDealer Win!");

        }

        }

    }

