package org.project.deck;

import java.util.ArrayList;

//TODO: add joker toggle?
//TODO: custom card set on initialization (mostly so discardPile can be type Deck rather than ArrayList<>)

//The Deck class adds shuffle and draw functionality to an ArrayList to simulate a deck of cards
public class Deck extends ArrayList<Card> {

    //Deck constructor automatically fills it with all 52 cards in a standard deck (excludes jokers)
    public Deck() {
        this.populate();
    }

    //This method is used to fill the deck with cards
    private void populate() {
        String suit = "";

        //Loops four times. One for each suit
        for(int i = 0; i < 4; i++) {

            //Determines which suit we are currently looping through using the number of loops finished
            switch(i) {
                case 0:
                    suit = "Hearts";
                    break;
                case 1:
                    suit = "Diamonds";
                    break;
                case 2:
                    suit = "Clubs";
                    break;
                case 3:
                    suit = "Spades";
                    break;
                default:
                    break;
            }

            //Loops 13 times. Once for each card in the suit
            for(int j = 1; j <= 13; j++) {
                //Adds a new Card object with suit: suit and value: j to the Deck
                this.add(new Card(suit, j));
            }
        }
    }

    //This is a helper method for shuffle(). Given the index of both Cards, it will swap their position in the Deck
    private void swapCards(int index1, int index2) {
        Card card1 = this.get(index1);
        Card card2 = this.set(index2, card1);
        this.set(index1, card2);
    }

    //Shuffles the entire Deck
    public void shuffle() {
        //Iterates through the entire Deck, swapping each Card with another random Card
        for(int i = 0; i < this.size(); i++) {
            swapCards(i, (int)Math.floor(Math.random() * 51));
        }
    }

    //Draws a card from the deck by removing and returning the card at the 'top' (index 0)
    public Card drawCard() {
        return this.remove(0);
    }

    //prints out a list of every card in the deck (temporary method)
    public void view() {
        for(Card card : this) {
            System.out.println(card.getName());
        }
    }

    //Resets the deck so that it contains all cards again
    public void reset() {
        this.clear();
        populate();
    }
}
