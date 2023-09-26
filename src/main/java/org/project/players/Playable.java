package org.project.players;

import org.project.deck.Card;

import java.util.List;

public interface Playable {
    List<Card> getHand();
    void addCard(Card card);
    int getScore();
    int getWins();
    void tallyWin();
    int getGamesPlayed();
    void tallyGame();
}
