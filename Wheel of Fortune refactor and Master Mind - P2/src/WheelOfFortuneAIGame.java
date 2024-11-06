import java.util.List;

public class WheelOfFortuneAIGame extends WheelOfFortuneInheritance {

    private final List<WheelOfFortunePlayer> players;
    private int currentPlayerIndex;
    private static int totalPlays = 0;


    /**
     * Default constructor with a single default AI player.
     */
    public WheelOfFortuneAIGame() {
        this(List.of(new RandomGuessPlayer("Default_AI_Player")));
    }


    /**
     * Constructor
     */
    public WheelOfFortuneAIGame(WheelOfFortunePlayer player) {
        this(List.of(player));
    }


    /**
     * Constructor with a list of AI players
     */
    public WheelOfFortuneAIGame(List<WheelOfFortunePlayer> players) {
        super();
        this.players = players;
        this.currentPlayerIndex = 0;
    }


    /**
     * Gets the next guess from the current AI player
     */
    @Override
    protected char getGuess(String previousGuesses) {
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        return player.nextGuess(previousGuesses, hiddenPhrase.toString());
    }


    /**
     * Get the next guess from the current AI player
     */
    @Override
    protected String getGuess() {
        WheelOfFortunePlayer player = players.get(currentPlayerIndex);
        return String.valueOf(player.nextGuess(previousGuesses.toString(), hiddenPhrase.toString()));
    }


    /**
     * Plays a single game with the AI player
     */
    @Override
    public GameRecord play() {
        totalPlays += 1;
        // Initialize game state
        phrase = selectRandomPhrase();
        if (phrase == null) {
            System.out.println("No more phrases available.");
            return null;
        }
        hiddenPhrase = new StringBuilder(generateHiddenPhrase());
        previousGuesses.clear();

        int lives = 5;
        WheelOfFortunePlayer currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.reset();

        while (hiddenPhrase.toString().contains("*") && lives > 0) {
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);

            char guess = getGuess(previousGuesses.toString());

            if (guess == '\0') {
                System.out.println("AI has no valid guesses left.");
                break;
            }

            System.out.println("AI Guess: " + guess);

            if (!previousGuesses.contains(guess)) {
                int updatedLives = processGuess(guess, lives);
                if (updatedLives == lives) {
                    System.out.println("Correct guess: " + guess);
                } else {
                    System.out.println("Incorrect guess: " + guess + ". Lives decremented.");
                }
                lives = updatedLives;
            } else {
                System.out.println("Repeated guess by AI: " + guess);
            }
        }

        // Show the final results to the user
        if (!hiddenPhrase.toString().contains("*")) {
            System.out.println("AI successfully guessed the phrase: " + phrase);
        } else {
            System.out.println("AI ran out of lives. The correct phrase was: " + phrase);
        }

        // Calculate score: 100 if guessed correctly, otherwise partial score based on revealed characters
        int revealedLetters = 0;
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) == phrase.charAt(i)) {
                revealedLetters++;
            }
        }
        int maxLetters = phrase.replaceAll("[^a-zA-Z]", "").length();
        int score = (revealedLetters == maxLetters) ? 100 : (int) ((double) revealedLetters / maxLetters * 100);

        // Return the GameRecord with the calculated score
        GameRecord record = new GameRecord(score, currentPlayer.playerId());

        // Move to the next player for the next game
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return record;
    }


    /**
     * Determines if the game should continue to another round
     */
    @Override
    public boolean playNext() {
        int totalPlaysLimit = players.size() * gameData.size();
        return totalPlays < totalPlaysLimit;
    }

    /**
     * Main method to run a set of games
     */
    public static void main(String[] args) {
        // Create a list of AI players
        List<WheelOfFortunePlayer> aiPlayers = List.of(
                new RandomGuessPlayer("AI_Random_Guess"),
                new VowelFirstPlayer("AI_Vowel_Guess"),
                new FrequentLetterPlayer("AI_Frequent_Guess")
        );

        // Create and run the AI game
        WheelOfFortuneAIGame aiGame = new WheelOfFortuneAIGame(aiPlayers);
        AllGamesRecord record = aiGame.playAll();

        // Display results using AllGamesRecord methods
        System.out.println("Average Score: " + record.average());
        System.out.println("Top 12 Games: ");
        for (GameRecord gr : record.highGameList(totalPlays)) {
            System.out.println("Player: " + gr.getPlayerId() + ", Score: " + gr.getScore());
        }
    }


    /**
     * Overriding the toString for rubric
     */
    @Override
    public String toString() {
        return "WheelOfFortuneAIGame{" + "players=" + players + ", currentPlayerIndex=" + currentPlayerIndex + '}';
    }


    /**
     * Overriding the equals for rubric
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) obj;
        return currentPlayerIndex == that.currentPlayerIndex &&
                players.equals(that.players);
    }
}
