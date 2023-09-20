package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CardTable {
    private Deck deck;
    private Display display = new Display();
    private Stack<Card> discardPile;
    private List<Card> playerHand;
    private List<Card> houseHand;
    private boolean isPlayerTurn;

    private final Scanner userInput = new Scanner(System.in);


    public CardTable() throws InterruptedException {
        deck = new Deck();
        discardPile = new Stack<>();
        playerHand = new ArrayList<>();
        houseHand = new ArrayList<>();

        isPlayerTurn = false;

        playerHand.clear();
        houseHand.clear();

        display.startUp(userInput);
    }

    public void startNewGame() {
        ScoreBoard.tallyGame();
        isPlayerTurn = true;

        playerHand.clear();
        houseHand.clear();

        deck.shuffle();

        Card drawnCard = deck.drawCard();
        this.playerHand.add(drawnCard);
        display.newCard(drawnCard.getName(), "You");
        ScoreBoard.addToPlayerScore(drawnCard.getValue());

        drawnCard = deck.drawCard();
        this.playerHand.add(drawnCard);
        display.newCard(drawnCard.getName(), "You");
        ScoreBoard.addToPlayerScore(drawnCard.getValue());
    }

    private void gameRuntime() {
        while(isPlayerTurn) {
            this.isPlayable(ScoreBoard.getPlayerScore());
        }

    }

    private boolean isPlayable(int score) {
        return score < Constants.BLACK_JACK_VALUE;
    }
}
