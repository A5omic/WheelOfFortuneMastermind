import java.util.Arrays;
import java.util.List;

public class FrequentLetterPlayer implements WheelOfFortunePlayer {

    private final String playerId;
    private final List<Character> commonLetters = Arrays.asList('e', 't', 'a', 'o', 'i', 'n', 's');
    private int currentLetterIndex;


    /**
     * Constructor
     */
    public FrequentLetterPlayer(String playerId) {
        this.playerId = playerId;
        this.currentLetterIndex = 0;
    }


    /**
     * Generates the next guess by choosing letters that show up a lot in words
     */
    @Override
    public char nextGuess(String previousGuesses, String hiddenPhrase) {
        for (char guess : commonLetters) {
            if (previousGuesses.indexOf(guess) == -1) {
                return guess;
            }
        }
        return '\0'; // No more letters to guess
    }


    /**
     * Returns the player's id
     */
    @Override
    public String playerId() {
        return playerId;
    }


    /**
     * Resets the player's current letter index to start guessing again
     */
    @Override
    public void reset() {
        currentLetterIndex = 0;
    }


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "FrequentLetterPlayer{" + "playerId='" + playerId + '\'' + ", currentLetterIndex=" + currentLetterIndex + '}';
    }


    /**
     * Overriding the equals for rubric and if needed for debug
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FrequentLetterPlayer that = (FrequentLetterPlayer) o;
        return currentLetterIndex == that.currentLetterIndex && playerId.equals(that.playerId);
    }
}
