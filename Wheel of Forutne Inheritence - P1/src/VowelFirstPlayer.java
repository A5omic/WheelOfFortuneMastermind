import java.util.ArrayList;
import java.util.List;

public class VowelFirstPlayer implements WheelOfFortunePlayer {

    private final String playerId;
    private final List<Character> guesses;
    private static final String VOWELS = "aeiou";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";


    /**
     * Constructor
     */
    public VowelFirstPlayer(String playerId) {
        this.playerId = playerId;
        this.guesses = new ArrayList<>();
    }


    /**
     * Generates the next guess by prioritizing vowels before moving to consonants
     */
    @Override
    public char nextGuess(String previousGuesses, String hiddenPhrase) {
        String letters = VOWELS + CONSONANTS;
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            if (previousGuesses.indexOf(letter) == -1 && !guesses.contains(letter)) {
                guesses.add(letter);
                return letter;
            }
        }
        return '\0'; // No more letters to guess
    }

    @Override
    public String playerId() {
        return playerId;
    }

    @Override
    public void reset() {
        guesses.clear();
    }


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "VowelFirstPlayer{" + "playerId='" + playerId + '\'' + ", guesses=" + guesses + '}';
    }


    /**
     * Overriding the equals for rubric and if needed for debug
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VowelFirstPlayer that = (VowelFirstPlayer) o;
        return playerId.equals(that.playerId) && guesses.equals(that.guesses);
    }
}
