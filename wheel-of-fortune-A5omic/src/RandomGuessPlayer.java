import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomGuessPlayer implements WheelOfFortunePlayer {

    private final String playerId;
    private final Random random;
    private final Set<Character> guessedLetters;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";


    /**
     * Constructor
     */
    public RandomGuessPlayer(String playerId) {
        this.playerId = playerId;
        this.random = new Random();
        this.guessedLetters = new HashSet<>();
    }


    /**
     * Generates a random guess by selecting a random letter
     */
    @Override
    public char nextGuess(String previousGuesses, String hiddenPhrase) {
        if (guessedLetters.size() >= ALPHABET.length()) {
            return '\0';  // No more letters to guess
        }

        char guess;
        do {
            guess = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        } while (previousGuesses.indexOf(guess) != -1 || guessedLetters.contains(guess));

        guessedLetters.add(guess);
        return guess;
    }


    /**
     * Returns the player's id
     */
    @Override
    public String playerId() {
        return playerId;
    }


    /**
     * Resets the player's guessed letters for a new game
     */
    @Override
    public void reset() {
        guessedLetters.clear();
    }


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "RandomGuessPlayer{" +
                "playerId='" + playerId + '\'' +
                ", guessedLetters=" + guessedLetters +
                '}';
    }


    /**
     * Overriding the equals for rubric and if needed for debug
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RandomGuessPlayer that = (RandomGuessPlayer) o;
        return playerId.equals(that.playerId) && guessedLetters.equals(that.guessedLetters);
    }
}
