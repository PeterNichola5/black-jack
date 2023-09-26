package org.project.deck;


//The Card class holds various properties that mimic a playing card (suit, number, name, etc.)
public class Card extends Object {

    //Instantiating properties
    private String name;
    private int rank;
    private String suit;
    private String color;

    //Constructor sets the card's value, suit, name, and color
    public Card(String suit, int rank) {

        //Initiating properties
        this.rank = rank;
        this.suit = suit;

        //Uses the suit to determine whether the color of the card is black or red
        this.color = this.suit.toLowerCase().equals("hearts") || this.suit.toLowerCase().equals("diamonds") ? "red" : "black";

        //This switch statement takes the value of a card and determines its name based on that and the suit
        switch(rank) {
            case 1:
                this.name = "Ace of " + (String.valueOf(suit));
                break;
            case 11:
                this.name = "Jack of " + (String.valueOf(suit));
                this.rank = 10;
                break;
            case 12:
                this.name = "Queen of " + (String.valueOf(suit));
                this.rank = 10;
                break;
            case 13:
                this.name = "King of " + (String.valueOf(suit));
                this.rank = 10;
                break;
            default:
                this.name = rank + " of " + (String.valueOf(suit));
                break;
        }
    }

    //Getters
    public String getName() {
        return this.name;
    }
    public int getRank() {
        return this.rank;
    }
    public String getSuit() {
        return this.suit;
    }
}
