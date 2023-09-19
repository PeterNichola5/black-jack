package org.example;


//The Card class holds various properties that mimic a playing card (suit, number, name, etc.)
public class Card extends Object {

    //Instantiating properties
    private String name;
    private int value;
    private String suit;
    private String color;

    //Constructor sets the card's value, suit, name, and color
    public Card(String suit, int value) {

        //Initiating properties
        this.value = value;
        this.suit = suit;

        //Uses the suit to
        color = suit.toLowerCase().equals("hearts") || suit.toLowerCase().equals("diamonds") ? "red" : "black";

        switch(value) {
            case 1:
                name = "Ace of " + (String.valueOf(suit));
                break;
            case 11:
                name = "Jack of " + (String.valueOf(suit));
                this.value = 10;
                break;
            case 12:
                name = "Queen of " + (String.valueOf(suit));
                this.value = 10;
                break;
            case 13:
                name = "King of " + (String.valueOf(suit));
                this.value = 10;
                break;
            default:
                name = value + " of " + (String.valueOf(suit));
                break;
        }
    }

    public String getName() {
        return this.name;
    }
    public int getValue() {
        return this.value;
    }
    public String getSuit() {
        return this.suit;
    }
}
