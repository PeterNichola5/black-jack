package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*=================================================================================================*\
|| This is a simple java-based command line game developed by Peter Nicholas that allows the user  ||
|| to play a quick round of Black Jack. The program uses standard house rules to determine how the ||
|| CPU will play. Future planned implementation for this project can be viewed in the above To do  ||
|| list. This program is being developed as a side project for the 2023 Fall Tech Elevator cohort. ||
|| Comments throughout the codebase provide in depth documentation of the program.                 ||
||                                                                                                 ||
|| 9/18/2023 ~ Peter Nicholas                                                                      ||
\*=================================================================================================*/

//TODO: fancyText method that changes console text weight and color
//TODO: win streak and win-lose tracking
//TODO: add multiple positive and negative feedback statements besides "Good choice!" and "That may not be enough..."
//TODO: add "Told 'ya so" and "apology" statements after a game depending on the expected vs actual outcome
//TODO: add replay functionality with the option to shuffles discarded cards back into the deck
//TODO: implement discardPile functionality
//TODO: two player functionality
//TODO: Refactor codebase so that there are no methods in Main
//TODO: define Thread.sleep() times as fixed constants
//TODO: clean up and streamline all calls to System.out
//TODO: add documentation about thread (throws InterruptedError, Thread.sleep, etc.)
//TODO: add tie logic (right now, tie goes to the house


//The Main class is where all of the project's functionality comes together
public class Main {

    //Initializing Deck and Scanner
    static Deck deck = new Deck();
    static Scanner userInput = new Scanner(System.in);


    //Initializing ArrayLists to represent the 'areas of play'
    static ArrayList<Card> discardPile = new ArrayList<>();
    static ArrayList<Card> playerHand = new ArrayList<>();
    static ArrayList<Card> houseHand = new ArrayList<>();

    //Initializing variables
    static boolean didWin;

    //Constant for Black Jack
    public static final int BLACK_JACK_VALUE = 21;
    public static final int HOUSE_MIN_VALUE = 17;

    //main method
    public static void main(String[] args) throws InterruptedException {

        //Begin game by shuffling the deck
        deck.shuffle();

        //Sequence for program startup
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

        //Initializes and begins the game loop
        setUp();
        prompt();


    }

    //Prints a statement one word at a time for some additional flare
    public static void fancyPrint(String str) throws InterruptedException {

        //Splits the inputted string into an array of every word in the string
        String[] arr = str.split(" ");

        //Iterates through the string array, printing each word individually
        for(String string : arr) {
            System.out.print(string + " ");
            Thread.sleep(200);
        }
    }

    //Game setup
    public static void setUp() throws InterruptedException {

        //Zeroes out both hands and scores
        playerHand.clear();
        houseHand.clear();

        //Draws the players two beginning cards from the deck and stores them in the player's 'hand'
        playerHand.add(deck.drawCard());
        playerHand.add(deck.drawCard());

        /*
        This foreach loop iterates through the players 'hand', displaying the drawn cards to the console.
        It then calculates the player's current score, and stores it in the playerScore variable
        */

        for(Card card : playerHand) {
            Thread.sleep(500);
            System.out.println("You drew a " + card.getName() + ".");
            ScoreBoard.addToPlayerScore(card.getValue());
        }
    }

    //prompts the user for input
    public static void prompt() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Your current score: " + ScoreBoard.getPlayerScore() + "\n");

        //If statement determines whether the player has busted or not
        if(ScoreBoard.getPlayerScore() < BLACK_JACK_VALUE) {

            Thread.sleep(750);

            fancyPrint("Would you like to (h)it or (s)tay? ");
            String response = userInput.nextLine().toLowerCase();

            //Determines whether the player wants to hit or stay
            if(response.equals("h")) {

                //Informs player that another card is about to be drawn
                Thread.sleep(500);
                System.out.println();
                fancyPrint("You chose to hit!");
                System.out.println();

                //TODO Add fancyDraw() method and call it either here or in hit()

                hit();

            } else if(response.equals("s")) {

                //Informs player that it is now the house's turn
                Thread.sleep(500);
                System.out.print("\n");
                fancyPrint("You chose to stay at " + ScoreBoard.getPlayerScore() + ". ");
                Thread.sleep(500);

                //Uses a ternary operator to respond to the user's decision based on how the house plays
                fancyPrint((ScoreBoard.getPlayerScore() >= HOUSE_MIN_VALUE ? "Good choice!" : "That may not be enough...")); //TODO: Multiple feedback statements (see above)
                System.out.println();
                Thread.sleep(1000);

                //Begins the house's (CPU) turn
                houseTurn();
            } else {

                //Handles inputs that are not 'h' or 's' by informing the player that their input is invalid.
                System.out.println("Invalid input: " + response + "\n");
                Thread.sleep(700);

                //Reminds the user of their current score and prompts them for input again
                System.out.println("Your current score is: " + ScoreBoard.getPlayerScore());
                prompt();
            }

        //Checks if the player has Black Jack
        } else if(ScoreBoard.getPlayerScore() == BLACK_JACK_VALUE) {

            //Informs the player that they have Black Jack, resulting in an automatic win
            Thread.sleep(750);
            System.out.println("You got BlackJack!!!");
            Thread.sleep(750);

            //Sets game result
            didWin = true;
            fancyPrint("Y O U  W I N !");
        } else {

            //Informs the player that their score went over 21, resulting in a BUST
            Thread.sleep(750);
            System.out.println("You busted :(");
            Thread.sleep(750);

            //sets game result
            didWin = false;
            fancyPrint("Y O U  L O S E");
        }
    }

    //Draws the next card in 'deck'
    public static void hit() throws InterruptedException {
        Card drawnCard = deck.drawCard();

        //Adds the drawn card to playerHand and updates the player's score
        playerHand.add(drawnCard);
        ScoreBoard.addToPlayerScore(drawnCard.getValue());
        Thread.sleep(750);

        //Informs the player of the card they have just drawn and prompts them for input (see the 'prompt' method)
        System.out.println("You drew a " + drawnCard.getName() + ".");
        prompt();
    }

    //The 'houseTurn' method controls all house (CPU) functionality
    public static void houseTurn() throws InterruptedException {

        System.out.println("\nIt's the house's turn!\n");

        //Draws the house's two beginning cards and stores them in the house's 'hand'
        houseHand.add(deck.drawCard());
        houseHand.add(deck.drawCard());

        /*
        This foreach loop iterates through the house's 'hand', displaying the drawn cards to the console.
        It then calculates the house's current score, and stores it in the houseScore variable
        */

        for(Card card : houseHand) {
            Thread.sleep(750);
            System.out.println("The house drew a " + card.getName() + ".");
            ScoreBoard.addToHouseScore(card.getValue());
        }

        //The following while loop will run until the house has a score of at least 17 (HOUSE_MIN_VALUE)
        while(ScoreBoard.getHouseScore() < HOUSE_MIN_VALUE) {

            //Displays the house score and informs the player that the house has chosen to hit
            Thread.sleep(750);
            System.out.println("The house currently has a score of " + ScoreBoard.getHouseScore() + ".\n");
            Thread.sleep(1000);
            System.out.println("The house is hitting");

            //Draws a card from 'deck', adds it to the house's hand, and updates the house's score
            Card cardDrawn = deck.drawCard();
            houseHand.add(cardDrawn);
            ScoreBoard.addToHouseScore(cardDrawn.getValue());

            //Displays the card that the house drew
            Thread.sleep(750);
            System.out.println("The house drew a " + cardDrawn.getName());
        }

        //Checks if the house has Black Jack
        if(ScoreBoard.getHouseScore() == BLACK_JACK_VALUE) {

            //Informs the player that the house got Black Jack, resulting in an automatic loss
            Thread.sleep(750);
            System.out.println("The house got BlackJack!!!");
            Thread.sleep(750);

            //Sets game result
            didWin = false;
            fancyPrint("Y O U  L O S E");
        } else {

            //Displays the house's final score
            Thread.sleep(750);
            System.out.println("The house currently has a score of " + ScoreBoard.getHouseScore() + ".\n");

            //Checks if the house busted
            if(ScoreBoard.getHouseScore() > BLACK_JACK_VALUE) {

                //Informs the player that the house's score was greater than 21, resulting in a BUST
                Thread.sleep(350);
                System.out.println("The house has busted!");
                Thread.sleep(750);

                //Sets game result
                didWin = true;
                fancyPrint("Y O U  W I N !");
            } else {

                //Informs the player that the house has chosen to stay
                Thread.sleep(1000);
                System.out.println("The house has chosen to stay at " + ScoreBoard.getHouseScore() + ".");

                //Checks whether the player or the house has the higher score
                if(ScoreBoard.getPlayerScore() > ScoreBoard.getHouseScore()) {

                    //Informs the player that their score is higher than the house's
                    Thread.sleep(750);
                    System.out.println("Your score of " + ScoreBoard.getPlayerScore() + " beats the house's score of " + ScoreBoard.getHouseScore());
                    Thread.sleep(750);

                    //Sets game result
                    didWin = true;
                    fancyPrint("Y O U  W I N !");

                } else {

                    //Informs the player that their score is lower than the house's
                    Thread.sleep(750);
                    System.out.println("The house's score of " + ScoreBoard.getHouseScore() + " beats your score of " + ScoreBoard.getPlayerScore());
                    Thread.sleep(750);

                    //Sets game result
                    didWin = false;
                    fancyPrint("Y O U  L O S E");
                }
            }
        }
    }

    public static void nextGamePrompt() {
    //TODO
    }

}