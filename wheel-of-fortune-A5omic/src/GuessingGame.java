import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract superclass for guessing games like WheelOfFortune and Mastermind.
 */
public abstract class GuessingGame extends Game {

    protected String phrase;  // Represents the target or hidden item (phrase/code)
    protected StringBuilder hiddenPhrase;  // Represents the current guessed state
    protected List<Character> previousGuesses;  // Tracks guesses made by the player
    protected List<String> gameData;  // Holds the data for the game (phrases, codes, etc.)

    /**
     * Constructor initializes the game state, loading data from a file if necessary.
     */
    public GuessingGame(String fileName) {
        this.previousGuesses = new ArrayList<>();
        this.gameData = readDataFromFile(fileName);
    }

    /**
     * Reads data (phrases or codes) from a file.
     */
    protected List<String> readDataFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Selects a random item from the game data.
     */
    protected String selectRandomItem() {
        if (gameData.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(gameData.size());
        String selectedItem = gameData.remove(index);  // Remove to prevent reuse
        return selectedItem;
    }

    /**
     * Generates a hidden version of a target item using a masking character ('*' for letters).
     */
    protected String generateHiddenVersion(String item) {
        StringBuilder hiddenText = new StringBuilder();
        for (int i = 0; i < item.length(); i++) {
            if (Character.isLetter(item.charAt(i))) {
                hiddenText.append("*");
            } else {
                hiddenText.append(item.charAt(i));
            }
        }
        return hiddenText.toString();
    }

    /**
     * Processes a player's guess, updating the game state if correct.
     */
    protected int processGuess(char guess, int lives) {
        boolean correctGuess = false;
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == guess) {
                correctGuess = true;
                hiddenPhrase.setCharAt(i, phrase.charAt(i));
            }
        }
        previousGuesses.add(guess);
        return correctGuess ? lives : lives - 1;
    }

    /**
     * Abstract method to get the next guess from a player.
     */
    protected abstract String getGuess();

    /**
     * Abstract method to play a single game.
     */
    @Override
    public abstract GameRecord play();

    /**
     * Abstract method to check if the game should continue.
     */
    @Override
    public abstract boolean playNext();
}
