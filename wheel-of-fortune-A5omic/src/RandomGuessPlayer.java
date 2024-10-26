import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomGuessPlayer implements WheelOfFortunePlayer {
    private String id;
    private Set<Character> previousGuesses;
    private Random random;


    /**
     * Constructor for the random player guess
     */
    public RandomGuessPlayer(String id) {
        this.id = id;
        this.previousGuesses = new HashSet<>();
        this.random = new Random();
    }


    /**
     * Returns the next random guess
     */
    @Override
    public char nextGuess(String previousGuesses, String hiddenPhrase) {
        char guess;
        do {
            guess = (char) ('a' + random.nextInt(26)); // Random letter from 'a' to 'z'
        } while (this.previousGuesses.contains(guess));

        // Add to the set of guessed letters to avoid repeating
        this.previousGuesses.add(guess);
        return guess;
    }


    /**
     * Returns the player id
     */
    @Override
    public String playerId() {
        return id;
    }


    /**
     * Resets the player guesses for a new game
     */
    @Override
    public void reset() {
        previousGuesses.clear();
    }
}
