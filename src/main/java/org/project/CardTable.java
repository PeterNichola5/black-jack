package org.project;

import org.project.deck.Card;
import org.project.deck.Deck;
import org.project.players.House;
import org.project.utils.Constants;
import org.project.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CardTable {
    private Deck deck;
    private DisplayUtils displayUtils = new DisplayUtils();

    private static final House house = new House();
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

        displayUtils.startUp(userInput);
    }

    public void startNewGame() {
        ScoreBoard.tallyGame();
        isPlayerTurn = true;

        playerHand.clear();
        houseHand.clear();

        deck.shuffle();

        Card drawnCard = deck.drawCard();
        this.playerHand.add(drawnCard);
        displayUtils.newCard(drawnCard.getName(), "You");
        ScoreBoard.addToPlayerScore(drawnCard.getRank());

        drawnCard = deck.drawCard();
        this.playerHand.add(drawnCard);
        displayUtils.newCard(drawnCard.getName(), "You");
        ScoreBoard.addToPlayerScore(drawnCard.getRank());
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
