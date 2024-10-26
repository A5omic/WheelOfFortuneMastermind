import java.util.Random;

public class WheelOfFortuneAIGame extends WheelOfFortuneInheritance {
    private Random random;

    /**
     * Constructor initializes the AI game.
     */
    public WheelOfFortuneAIGame() {
        super();
        this.random = new Random();
    }

    /**
     * Get the next guess from the AI.
     */
    @Override
    protected char getGuess(String previousGuesses) {
        // Generate a random guess from 'a' to 'z'
        char guess;
        do {
            guess = (char) ('a' + random.nextInt(26)); // Random letter from 'a' to 'z'
        } while (previousGuesses.indexOf(guess) != -1); // Ensure it's a new guess
        return guess;
    }

    @Override
    protected String getGuess() {
        // Not needed for AI; instead, we use the method with previous guesses context
        return "";
    }

    /**
     * Main method to run the AI game
     */
    public static void main(String[] args) {
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame();
        AllGamesRecord record = aiGame.playAll();

        // Display game statistics
        System.out.println("AI Game - Average Score: " + record.average());
        System.out.println("AI Game - Top Scores: ");
        for (GameRecord gr : record.highGameList(3)) {
            System.out.println("Player: " + gr.getPlayerId() + ", Score: " + gr.getScore());
        }
    }

    @Override
    public GameRecord play() {
        // Use the same play logic as in WheelOfFortuneUserGame or customize for AI
        return super.play();
    }

    @Override
    public boolean playNext() {
        // AI decision to play another round, can be automatic or controlled
        return false;
    }
}
