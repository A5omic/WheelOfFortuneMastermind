import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class GuessingGame extends Game {

    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected List<Character> previousGuesses;
    protected List<String> gameData;


    /**
     * Constructor initializes the game state, loading data from a file if necessary.
     */
    public GuessingGame(String fileName) {
        this.previousGuesses = new ArrayList<>();
        if (fileName != null) {
            this.gameData = readDataFromFile(fileName);
        } else {
            this.gameData = new ArrayList<>(); // No file, initialize empty data
        }
    }


    /**
     * Reads data (phrases or codes) from a file.
     */
    protected List<String> readDataFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * Selects a random item
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
     * Generates a hidden version
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
     * Processes a player's guess
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
     * Abstract method to get the next
     */
    protected abstract String getGuess();


    /**
     * Play a single game
     */
    @Override
    public abstract GameRecord play();


    /**
     * Check if the game should continue
     */
    @Override
    public abstract boolean playNext();
}
