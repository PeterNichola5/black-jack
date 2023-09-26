package org.project.players;

import org.project.deck.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player implements Playable {
    private List<Card> hand = new ArrayList<>();
    private int wins;
    private int gamesPlayed;

    public Player() {
        this.wins = 0;
        this.gamesPlayed = 0;
    }

    @Override
    public List<Card> getHand() {
        return this.hand;
    }

    @Override
    public void addCard(Card card) {
        this.hand.add(card);
    }

    @Override
    public int getScore() {
        int score = 0;
        for(Card card : hand) {
            score += card.getRank();
        }
        return score;
    }


    @Override
    public int getWins() {
        return this.wins;
    }

    @Override
    public void tallyWin() {
        this.wins++;
    }

    @Override
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    @Override
    public void tallyGame() {
        this.gamesPlayed++;
    }
}
