package blackJack;
import java.util.Scanner;



public class GamePlayer {
    public static void main(String[] args) {

        Player dealer = new Player("Dealer");
        Player me = new Player("Travis");
        int myBalance = me.getBalance();
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
                if (me.getHandSum() == 21 && dealer.getHandSum() != 21) {
                    me.incBalance();
                    System.out.println("\nYou Win!" + "\nYour Balance is €" + me.getBalance());
                    System.out.println("You have: " + me.getHandSum() + "\n");
                    boolean meDone = true;
                    me.emptyHand();
                    dealer.emptyHand();
                } else {
                    boolean meDone = false;
                    boolean dealerDone = false;


                    while (!meDone || !dealerDone) {

                        if (!meDone) {

                            System.out.println("You have: " + me.getHandSum() + "\n");
                            System.out.print("Hit, Stay, Double or Split? (Enter H, S, D or Sp):");
                            ans = sc.next();

                            if (ans.compareToIgnoreCase("H") == 0) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHand(true);

                            } else if (ans.compareToIgnoreCase("D") == 0) {
                                meDone = !me.addCard(theDeck.dealNextCard());
                                me.printHand(true);
                                meDone = true;
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

                    while (ans.compareToIgnoreCase("D") == 1) {

                        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                            me.dIncBalance();
                            System.out.println("\nYou Win!" + "\nYour Balance is €" + me.getBalance());
                        } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                            me.dDicBalance();
                            System.out.println("\nDealer Win!" + "\nYour Balance is €" + me.getBalance());
                        } else {
                            System.out.println("\nA TIE!" + "\nYour Balance is €" + me.getBalance());
                        }

                        me.emptyHand();
                        dealer.emptyHand();
                    }

                    if (mySum > dealerSum && mySum <= 21 || dealerSum > 21) {
                        me.incBalance();
                        System.out.println("\nYou Win!" + "\nYour Balance is €" + me.getBalance());
                    } else if (dealerSum > mySum && dealerSum <= 21 || mySum > 21) {
                        me.dicBalance();
                        System.out.println("\nDealer Win!" + "\nYour Balance is €" + me.getBalance());
                    } else {
                        System.out.println("\nA TIE!" + "\nYour Balance is €" + me.getBalance());
                    }
                    me.emptyHand();
                    dealer.emptyHand();


                }

            } else {
                System.exit(0);
            }
        }
    }
}
