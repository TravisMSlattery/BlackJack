package com.Slattery.Travis;
import java.io.Serializable;
import java.util.ArrayList;

/*
This is my player class it contains setter and getter for username password and balance it also contains toString method

 */
public class Player implements Serializable{ //implements serializable this is java class to save to a file

    ArrayList<Player> players = new ArrayList<>();

    private String username, password; // string variables
    private double balance; // double balance variable
    private int wonHands, tieHands, handsplayed;

    public Player() {
        this("Unknown","unknown", 0.0,0,0,0);
    }

    Player(final String username, final String password, final double balance, int wonHands, int tieHands, int handsplayed) {
        this.username = username;
        this.balance = balance;
        this.password = password;
        this.wonHands = wonHands;
        this.tieHands = tieHands;
        this.handsplayed = handsplayed;

    }

    public void setUsername(final String username) {
        this.username = username;
    } //sets username

    public void setPassword(final String password) {
        this.password = password;
    } //sets password

    void setBalance(final double balance) {
        this.balance = balance;
    } // set balance

    void setWonHands(int wonHands) {
        this.wonHands = wonHands;
    } // set winning hans

    void setTieHands(int tieHands) {
        this.tieHands = tieHands;
    } // set tied hands

    void setHandsplayed(int handsplayed) {
        this.handsplayed = handsplayed;
    } // set hands played

    String getName() {
        return this.username;
    } //returns username

    String getPassword() {
        return this.password;
    } //returns password

    double getBalance() {
        return this.balance;
    } //returns balance

    int getWonHands() {
        return this.wonHands;
    } //returns winning hands

    int getTieHands() {
        return this.tieHands;
    } //returns tie hands

    int getHandsplayed() {
        return this.handsplayed;
    } //returns hands played

    @Override
    public String toString() {
        return this.username + " " +  " \u20ac" + String.format("%.2f", this.balance);
    } //  toString method
}
/*

 */