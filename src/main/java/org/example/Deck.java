package org.example;
import java.util.ArrayList;

public class Deck extends ArrayList<Card> {

    public Deck() {
        populate();
    }

    private void populate() {
        String suit = "";

        for(int i = 0; i < 4; i++) {
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
            for(int j = 1; j <= 13; j++) {
                this.add(new Card(suit, j));
            }
        }
    }
    private void swapCards(int index1, int index2) {
        Card card1 = this.get(index1);
        Card card2 = this.set(index2, card1);
        this.set(index1, card2);
    }
    public void shuffle() {
        for(int i = 0; i < this.size(); i++) {
            swapCards(i, (int)Math.floor(Math.random() * 51));
        }
    }

    public Card drawCard() {
        return this.remove(0);
    }

    public void view() {
        for(Card card : this) {
            System.out.println(card.getName());
        }
    }

    public void reset() {
        this.clear();
        populate();
    }




}
