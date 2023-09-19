package org.example;

public class ScoreBoard {

    private static int playerScore;
    private static int houseScore;
    private static int playerWins;
    private static int houseWins;
    private static int gamesPlayed;

    public ScoreBoard() {
        playerScore = 0;
        houseScore = 0;
        playerWins = 0;
        houseWins = 0;
        gamesPlayed = 0;
    }

    public static void resetScores() {
        playerScore = 0;
        houseScore = 0;
    }


    public static int getPlayerScore() {
        return playerScore;
    }
    public static void setPlayerScore(int newPlayerScore) {
        playerScore = newPlayerScore;
    }
    public static int addToPlayerScore(int num) {
        playerScore += num;
        return playerScore;
    }

    public static int getHouseScore() {
        return houseScore;
    }
    public static int addToHouseScore(int num) {
        houseScore += num;
        return houseScore;
    }
    public static void setHouseScore(int newHouseScore) {
        houseScore = newHouseScore;
    }

    public static int getPlayerWins() {
        return playerWins;
    }
    public static void tallyPlayerWin() {
        playerWins++;
    }

    public static int getHouseWins() {
        return houseWins;
    }
    public static void tallyHouseWin() {
        houseWins++;
    }

    public static int getGamesPlayed() {
        return gamesPlayed;
    }
    public static void tallyGame() {
        gamesPlayed++;
    }
}
