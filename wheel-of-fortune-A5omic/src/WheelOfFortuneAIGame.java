import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortuneInheritance {
    private final List<WheelOfFortunePlayer> players;
    private int currentPlayerIndex;

    /**
     * Constructor to list of AI players
     */
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        super();
        this.players = players;
        this.currentPlayerIndex = 0;
    }

    /**
     * Get the next guess from the current AI player
     */
    @Override
    protected char getGuess(String previousGuesses) {
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        return player.nextGuess(previousGuesses, hiddenPhrase.toString());
    }

    /**
     * Plays a single game with the AI player
     */
    @Override
    public GameRecord play() {
        // Initialize game state
        phrase = selectRandomPhrase();
        if (phrase == null) {
            System.out.println("No more phrases available.");
            return null;
        }
        hiddenPhrase = new StringBuilder(generateHiddenPhrase(phrase));
        previousGuesses.clear();

        int lives = 5;
        WheelOfFortunePlayer currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.reset(); // Reset player for a new game

        while (hiddenPhrase.toString().contains("*") && lives > 0) {
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);

            char guess = getGuess(previousGuesses.toString());
            if (!previousGuesses.contains(guess)) {
                lives = processGuess(guess, lives);
            }
        }

        // Calculate score: 100 if guessed correctly, otherwise partial score based on revealed characters
        int revealedLetters = 0;
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) == phrase.charAt(i)) {
                revealedLetters++;
            }
        }
        int maxLetters = phrase.replaceAll("[^a-zA-Z]", "").length(); // Count only alphabet letters in the phrase
        int score = (revealedLetters == maxLetters) ? 100 : (int) ((double) revealedLetters / maxLetters * 100);

        // Return the GameRecord with calculated score
        GameRecord record = new GameRecord(score, currentPlayer.playerId());

        // Move to the next player for the next game
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return record;
    }

    /**
     * For a new game it will return true or false
     */
    @Override
    public boolean playNext() {
        return !phrases.isEmpty();
    }

    /**
     * Main method to run a set of games with AI players
     */
    public static void main(String[] args) {
        // Create a list of AI players
        List<WheelOfFortunePlayer> aiPlayers = List.of(
                new RandomGuessPlayer("AI_Player_1"),
                new RandomGuessPlayer("AI_Player_2"),
                new RandomGuessPlayer("AI_Player_3")
        );

        // Create and run the AI game
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame(aiPlayers);
        AllGamesRecord record = aiGame.playAll();

        // Display results using AllGamesRecord methods
        System.out.println("Average Score: " + record.average());
        System.out.println("Top 3 Games: ");
        for (GameRecord gr : record.highGameList(3)) {
            System.out.println("Player: " + gr.getPlayerId() + ", Score: " + gr.getScore());
        }
    }
}
