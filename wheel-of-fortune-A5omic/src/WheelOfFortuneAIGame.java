import java.util.Random;

public class WheelOfFortuneAIGame extends WheelOfFortuneInheritance {

    private Random random;


    /**
     * Constructor initializes the AI game
     */
    public WheelOfFortuneAIGame() {
        super();
        this.random = new Random();
    }


    /**
     * Get the next guess from the AI
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


    /**
     * Play a single game with the AI
     */
    @Override
    public GameRecord play() {
        // Initialize the game state
        phrase = selectRandomPhrase();
        if (phrase == null) {
            System.out.println("No more phrases available.");
            return null;
        }
        hiddenPhrase = new StringBuilder(generateHiddenPhrase(phrase));
        previousGuesses.clear();

        int lives = 5;
        while (hiddenPhrase.toString().contains("*") && lives > 0) {
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);
            char guess = getGuess(previousGuesses.toString());
            if (!previousGuesses.contains(guess)) {
                lives = processGuess(guess, lives);
            } else {
                System.out.println("AI has already guessed that letter: " + guess);
            }
        }

        // Show the final results
        if (!hiddenPhrase.toString().contains("*")) {
            System.out.println("AI successfully guessed the phrase: " + phrase);
        } else {
            System.out.println("AI ran out of lives. The correct phrase was: " + phrase);
        }

        // Calculate the score based on the final state of the hidden phrase
        int revealedLetters = 0;
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) == phrase.charAt(i)) {
                revealedLetters++;
            }
        }
        int maxLetters = phrase.replaceAll("[^a-zA-Z]", "").length(); // Count only alphabet letters in the phrase
        int score = (revealedLetters == maxLetters) ? 100 : (int) ((double) revealedLetters / maxLetters * 100);

        // Return a GameRecord with the calculated score
        return new GameRecord(score, "AI_Player");
    }


    /**
     * AI decision to play another game
     */
    @Override
    public boolean playNext() {
        // Automatically end after one game for AI.
        return false;
    }
}
