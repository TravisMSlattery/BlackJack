package blackJack;

import java.util.Random;

public class Deck {

    public Card[] myCards;
    private int numCards;

    // no shuffle constructor
    public Deck() {
        this(6, false);
    }

    public Deck(int numDecks, boolean shuffle) {

        this.numCards = numDecks * 52;
        this.myCards = new Card[this.numCards];

        int c = 0;
        // for each deck
        for (int d = 0; d < numDecks; d++) {

            // for each suit
            for (int s = 0; s < 4; s++) {

                // for each deck
                for (int n = 1; n <= 13; n++) {

                    this.myCards[c] = new Card(Suit.values()[s], n);
                    c++;

                }
            }

        }
        if (shuffle) {
            this.shuffle();
        }

    }
        public void shuffle() {

            Random rng = new Random();
            Card temp;

            int j;
            for (int i = 0; i < this.numCards; i++) {

                j = rng.nextInt(this.numCards);

                temp = this.myCards[i];
                this.myCards[i] = this.myCards[j];
                this.myCards[j] = temp;
            }

        }


        public Card dealNextCard() {
            Card top = this.myCards[0];

            for (int c = 1; c < this.numCards; c++) {
                this.myCards[c - 1] = this.myCards[c];
            }

            this.myCards[this.numCards - 1] = null;

            this.numCards--;

            return top;
        }

        public void printDeck ( int numToPrint){
            for (int c = 0; c < numToPrint; c++) {
                System.out.printf("%3d/%d %s\n", c + 1, this.numCards, this.myCards[c].toString());
            }
            System.out.printf("\t\t[%d other]\n", this.numCards - numToPrint);
        }

    }








