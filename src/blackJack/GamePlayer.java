package blackJack;
import java.util.Scanner;


public class GamePlayer {
    public static void main(String[] args) {

        Player dealer = new Player("Dealer");
        Player me = new Player("Travis");
        Scanner sc = new Scanner(System.in);


        while (me.getBalance() != 0) {

            Deck theDeck = new Deck(6, true);
            String ans = "Y";


            if (ans.compareToIgnoreCase("Y") == 0) {
                System.out.print("Bet or Leave? (Enter Y or N): ");
                ans = sc.next();


                //deal cards
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());
                me.addCard(theDeck.dealNextCard());
                dealer.addCard(theDeck.dealNextCard());


                //print hands
                System.out.println("Cards are dealt\n");
                me.printHand(true);
                dealer.printHand(false);
                System.out.println("\n");
                if (me.getHandSum() == 21) {
                    boolean meDone = true;
                } else {
                    boolean meDone = false;
                    boolean dealerDone = false;


                    while (!meDone || !dealerDone) {

                        if (!meDone) {

                            System.out.println("You have: " + me.getHandSum() + "\n");
                            System.out.print("Hit or Stay? (Enter H or S): ");
                            ans = sc.next();

                            if (ans.compareToIgnoreCase("H") == 0) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHand(true);


                            } else {
                                meDone = true;
                            }
                        }
                        if (!dealerDone) {

                            if (dealer.getHandSum() < 17) {
                                System.out.println("Dealer Hits\n");
                                dealerDone = !dealer.addCard(theDeck.dealNextCard());
                                dealer.printHand(false);

                            } else {
                                System.out.println("Dealer Stays\n");
                                dealer.printHand(true);
                                dealerDone = true;
                            }
                        }
                        System.out.println();
                    }


                    me.printHand(true);
                    dealer.printHand(true);

                    int mySum = me.getHandSum();
                    int dealerSum = dealer.getHandSum();
                    int myBalance = me.playerBalance(me.getBalance());


                    if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                        System.out.println("\nYou Win!" + "\nYour Balance is €" + myBalance);
                    } else if ((mySum == dealerSum) && (mySum <= 21 && dealerSum <= 21)) {
                        System.out.println("\nA TIE!" + "\nYour Balance is €" + myBalance);
                    } else {
                        System.out.println("\nDealer Win!" + "\nYour Balance is €" + myBalance);

                    }
                    me.emptyHand();
                    dealer.emptyHand();


                } else{
                    System.exit(0);
                }

            }
        }
    }
}
