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

        System.out.println("\n\nWelcome to Black Jack! Try to get as close to 21 as possible, but don't go over!\n".toUpperCase());
        Thread.sleep(2500);
        System.out.println("(press enter to start)");
        userInput.nextLine();
        Thread.sleep(1000);
        System.out.print("LOADING");
        Thread.sleep(1500);
        System.out.print(" .");
        Thread.sleep(1500);
        System.out.print(" .");
        Thread.sleep(1500);
        System.out.print(" .");
        Thread.sleep(1500);
        System.out.println();
        Thread.sleep(1000);
        System.out.println("Starting hand: \n");

        setUp();
        prompt();


    }

    public static void fancyPrint(String str) throws InterruptedException {
        String[] arr = str.split(" ");
        for(String string : arr) {
            System.out.print(string + " ");
            Thread.sleep(200);
        }
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
            System.out.println("You drew a " + card.getName() + ".");
            playerScore += card.getValue();
        }

    }

    public static void prompt() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Your current score: " + playerScore + "\n");
        if(playerScore < 21) {
            Thread.sleep(750);
            fancyPrint("Would you like to (h)it or (s)tay? ");
            String response = userInput.nextLine().toLowerCase();
            if(response.equals("h")) {
                Thread.sleep(500);
                System.out.println("\n");
                fancyPrint("You chose to hit!");
                System.out.println();
                hit();
            } else if(response.equals("s")) {
                //house turn
                Thread.sleep(500);
                System.out.print("\n");
                fancyPrint("You chose to stay at " + playerScore + ". ");
                Thread.sleep(500);
                fancyPrint((playerScore > 16 ? "Good choice!" : "That may not be enough..."));
                System.out.println();
                Thread.sleep(1000);
                houseTurn();
            } else {
                System.out.println("Invalid input \n");
                Thread.sleep(700);
                System.out.println("Your current score is: " + playerScore);
                prompt();
            }

        } else if(playerScore == 21) {
            //win case
            Thread.sleep(750);
            System.out.println("You got BlackJack!!!");
            Thread.sleep(750);
            fancyPrint("Y O U  W I N !");
        } else {
            //lose case
            Thread.sleep(750);
            System.out.println("You busted :(");
            Thread.sleep(750);
            fancyPrint("Y O U  L O S E");
        }
    }


    public static void hit() throws InterruptedException {
        Card drawnCard = deck.drawCard();
        playerHand.add(drawnCard);
        playerScore += drawnCard.getValue();

        Thread.sleep(750);
        System.out.println("You drew a " + drawnCard.getName() + ".");
        prompt();
    }

    public static void houseTurn() throws InterruptedException {

        System.out.println("\nIt's the house's turn!\n");

        houseHand.add(deck.drawCard());
        houseHand.add(deck.drawCard());
        for(Card card : houseHand) {
            Thread.sleep(750);
            System.out.println("The house drew a " + card.getName() + ".");
            houseScore += card.getValue();
        }

        while(houseScore < 17) {
            Thread.sleep(750);
            Card cardDrawn = deck.drawCard();
            System.out.println("The house currently has a score of " + houseScore + ".\n");
            Thread.sleep(1000);
            System.out.println("The house is hitting");
            houseHand.add(cardDrawn);
            houseScore += cardDrawn.getValue();

            Thread.sleep(750);
            System.out.println("The house drew a " + cardDrawn.getName());

        }

        if(houseScore == 21) {
            Thread.sleep(750);
            System.out.println("The house got BlackJack!!!");
            Thread.sleep(750);
            fancyPrint("Y O U  L O S E");
        } else {
            Thread.sleep(750);
            System.out.println("The house currently has a score of " + houseScore + ".\n");

            if(houseScore > 21) {
                //win case
                Thread.sleep(350);
                System.out.println("The house has busted!");
                Thread.sleep(750);
                fancyPrint("Y O U  W I N !");
            } else {
                Thread.sleep(1000);
                System.out.println("The house has chosen to stay at " + houseScore + ".");
                if(playerScore > houseScore) {
                    //win case
                    Thread.sleep(750);
                    System.out.println("Your score of " + playerScore + " beats the house's score of " + houseScore);
                    Thread.sleep(750);
                    fancyPrint("Y O U  W I N !");

                } else {
                    //lose case
                    Thread.sleep(750);
                    System.out.println("The house's score of " + houseScore + " beats your score of " + playerScore);
                    Thread.sleep(750);
                    fancyPrint("Y O U  L O S E");
                }

            }
        }



    }
}