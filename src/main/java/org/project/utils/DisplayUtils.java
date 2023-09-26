package org.project.utils;

import java.util.Scanner;

public class DisplayUtils {
    public DisplayUtils() {

    }

    public void startUp(Scanner input) throws InterruptedException {

        System.out.println("\n\nWelcome to Black Jack! Try to get as close to 21 as possible, but don't go over!\n".toUpperCase());
        Thread.sleep(2500);
        System.out.println("(press enter to start)");

        input.nextLine();

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
    }

    public void newCard(String cardName, String noun) {
        System.out.println(noun + " drew a " + cardName + ".");
    }
}
