package blackJack;

import blackJack.Card;

public class Player {

    private String name;

    private Card[] hand = new Card[10];

    private int numCards;

    public int balance;


    public Player(String aName){

        this.name = aName;

        this.emptyHand();
    }

    public void emptyHand(){

        for(int c = 0;c < 10; c++){
            this.hand[c] = null;

        }
        this.numCards = 0;
    }

    public boolean addCard(Card aCard){

        if (this.numCards == 10){
            System.err.printf("%s' hand already has 10 cards cannot add another card\n", this.name);

            System.exit(1);
        }

        this.hand[this.numCards] = aCard;
        this. numCards++;

        return(this.getHandSum() <=21);

    }
    public int getHandSum(){

        int handSum = 0;
        int cardNum;
        int numAces = 0;

        for(int c = 0;c < this.numCards; c++){

            cardNum = this.hand[c].getNumber();

            if(cardNum == 1){
                numAces++;
                handSum += 11;
            }else if (cardNum > 10){
                handSum += 10;
            }else {
                handSum += cardNum;
            }

        }

        while (handSum > 21 && numAces > 0){
            handSum -= 10;
            numAces --;
        }

        return handSum;
    }

    public void printHand(boolean showFirstCard){
        System.out.printf("\n%s cards :\n", this.name);
        for (int c =0; c < this.numCards; c++){
            if (c == 0 && !showFirstCard){
                System.out.println("    [HIDDEN]");
            } else {
                System.out.printf("    %s\n", this.hand[c].toString());

            }
        }
    }

    public int playerBalance(){
        int mySum = getHandSum();
        int dealerSum = getHandSum();
        balance=1000;


        if (mySum > dealerSum && mySum <= 21 || dealerSum > 21){
            balance = balance + 200;
        }else if ((mySum == dealerSum) && (mySum <= 21 && dealerSum <= 21)){
            balance = balance;
        } else {
            balance = balance - 100;
        }

        return balance;
    }

}
