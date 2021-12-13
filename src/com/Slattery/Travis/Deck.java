package com.Slattery.Travis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Deck {

    public ArrayList<Card> myCards = new ArrayList<>();

    void Shoe() {
        this.myCards.clear();
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int[] rankValues = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        int numDecks = 6;

        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        for (int j = 1; j < numDecks; j++) {
            for (int i = 0; i < ranks.length; i++) {
                for (String suit : suits) {
                    this.myCards.add(new Card(ranks[i], suit, rankValues[i]));
                }
            }
        }
    }

    public Card takeCard() { // Removes card from top of card group's ArrayList
        // and returns it
        if (this.myCards.size() < 1) {
            System.out.println("Error: no more cards!");
            System.exit(0);
        }
        Card tempCard = this.myCards.get(this.myCards.size() - 1);
        this.myCards.remove(this.myCards.size() - 1);
        return tempCard;
    }

    void shuffle() {
        Random rng = new Random(); // Seed for random class
        Collections.shuffle(this.myCards, rng); // Swapping a randomly selected element into the "current position"
    }

    public int getTotalValue() {
        int totalValue = 0;
        for (Card myCard : this.myCards) totalValue += myCard.value;
        return totalValue;
    }

    public int getNumAces() {
        int numAces = 0;
        for (Card myCard : this.myCards)
            if (myCard.rank.equals("Ace"))
                numAces++;
        return numAces;
    }

    int getCount() {
        return this.myCards.size();
    }

    public void printDeck() {
        for (Card myCard : this.myCards) {
            myCard.print();
        }
    }
}









