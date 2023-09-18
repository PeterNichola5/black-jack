package org.example;

public class Card extends Object {
    private String name;
    private int value;
    private String suit;

    public Card(String suit, int value) {
        this.value = value;
        this.suit = suit;

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
