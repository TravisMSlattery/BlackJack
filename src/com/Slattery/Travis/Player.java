package com.Slattery.Travis;

import java.io.Serializable;
import java.util.ArrayList;

/**
*This is my player class it contains setters and getters for username, password, balance, winning hands tied hands and amount of hands played it also contains toString method
 */
public class Player implements Serializable { //implements serializable this is java class to save to a file

    ArrayList<Player> players = new ArrayList<>();

    private String username, password; // string variables
    private double balance; // double balance variable
    private int wonHands, tieHands, handsplayed;


    public Player() {
        this("Unknown", "unknown", 0.0, 0, 0, 0);
    }

    /**
     * @param username    is where the username is stored
     * @param password    is where the password is stored
     * @param balance     is where the balance is stored
     * @param wonHands    is where the hands won is stored
     * @param tieHands    is where the hands tied is stored
     * @param handsplayed is where the hands played is stored
     */
    Player(final String username, final String password, final double balance, int wonHands, int tieHands, int handsplayed) {
        this.username = username;
        this.balance = balance;
        this.password = password;
        this.wonHands = wonHands;
        this.tieHands = tieHands;
        this.handsplayed = handsplayed;

    }

    /**
     * @param username is set
     */
    public void setUsername(final String username) {
        this.username = username;
    } //sets username

    /**
     * @return the username
     */
    String getName() {
        return this.username;
    } //returns username

    /**
     * @return the password
     */
    String getPassword() {
        return this.password;
    } //returns password

    /**
     * @param password is set
     */
    public void setPassword(final String password) {
        this.password = password;
    } //sets password

    /**
     * @return the balance
     */
    double getBalance() {
        return this.balance;
    } //returns balance

    /**
     * @param balance is set
     */
    void setBalance(final double balance) {
        this.balance = balance;
    } // set balance

    /**
     * @return the hands won
     */
    int getWonHands() {
        return this.wonHands;
    } //returns winning hands

    /**
     * @param wonHands is set
     */
    void setWonHands(int wonHands) {
        this.wonHands = wonHands;
    } // set winning hans

    /**
     * @return the hands tied
     */
    int getTieHands() {
        return this.tieHands;
    } //returns tie hands

    /**
     * @param tieHands is set
     */
    void setTieHands(int tieHands) {
        this.tieHands = tieHands;
    } // set tied hands

    /**
     * @return the hands played
     */
    int getHandsplayed() {
        return this.handsplayed;
    } //returns hands played

    /**
     * @param handsplayed is set
     */
    void setHandsplayed(int handsplayed) {
        this.handsplayed = handsplayed;
    } // set hands played

    /**
     * @return the toString method
     */
    @Override
    public String toString() {
        return this.username + " " + " \u20ac" + String.format("%.2f", this.balance);
    } //  toString method
}
/*

 */