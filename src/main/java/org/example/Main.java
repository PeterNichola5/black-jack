package org.example;

import java.util.ArrayList;
import java.util.Scanner;

// house hits on 16 or under

public class Main {
    static Deck deck = new Deck();
    static Scanner userInput = new Scanner(System.in);
    static int playerScore = 0;
    static int houseScore = 0;
    static ArrayList<Card> discardPile = new ArrayList<Card>();
    static ArrayList<Card> playerHand = new ArrayList<Card>();
    static ArrayList<Card> houseHand = new ArrayList<Card>();

    public static void main(String[] args) throws InterruptedException {

        deck.shuffle();

        System.out.println("Welcome to Black Jack! Try to get as close to 21 as possible, but don't go over!");
        Thread.sleep(1000);
        System.out.println("Starting hand: \n");

        setUp();
        prompt();


    }


    public static void setUp() throws InterruptedException {
        playerScore = 0;
        houseScore = 0;
        playerHand.clear();
        houseHand.clear();
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());

        for(Card card : playerHand) {
            Thread.sleep(500);
            System.out.println(card.getName());
            playerScore += card.getValue();

        }




    }

    public static void prompt() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Score: " + playerScore + "\n");
        if(playerScore < 21) {
            Thread.sleep(250);
            System.out.print("Would you like to (h)it or (s)tay? ");
            String response = userInput.nextLine().toLowerCase();
            System.out.println();
            if(response.equals("h")) {
                hit();
            } else if(response.equals("s")) {
                //house turn
            } else {
                System.out.println("Invalid input \n");
                Thread.sleep(700);
                System.out.println("Your current score is: " + playerScore);
                prompt();
            }

        } else if(playerScore == 21) {
            //win case
            System.out.println("You win!");
        } else {
            //lose case
            System.out.println("You lose");
        }
    }


    public static void hit() throws InterruptedException {
        Card drawnCard = deck.drawCard();
        playerHand.add(drawnCard);
        playerScore += drawnCard.getValue();

        for(Card card : playerHand) {
            System.out.println(card.getName());
        }
        prompt();
    }
}