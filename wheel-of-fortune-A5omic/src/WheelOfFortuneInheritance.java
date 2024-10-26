import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortuneInheritance extends GuessingGame {

    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected List<Character> previousGuesses;
    protected List<String> phrases;


    /**
     * Constructor
     */
    public WheelOfFortuneInheritance() {
        super("phrases.txt");
        this.previousGuesses = new ArrayList<>();
        this.phrases = readPhrasesFromFile("phrases.txt");
    }


    /**
     * Reads phrases from file
     */
    protected List<String> readPhrasesFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * Selects a random phrase
     */
    protected String selectRandomPhrase() {
        if (phrases.isEmpty()) {
            return null;
        }
        Random rand = new Random();
        int index = rand.nextInt(phrases.size());
        String selectedPhrase = phrases.remove(index); // Remove to prevent reuse
        return selectedPhrase;
    }


    /**
     * Generates the hidden version
     */
    protected String generateHiddenPhrase(String phrase) {
        StringBuilder hiddenText = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) {
                hiddenText.append("*");
            } else {
                hiddenText.append(phrase.charAt(i));
            }
        }
        return hiddenText.toString();
    }


    /**
     * Processes a user's guess
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
     * Get the next guess
     */
    protected abstract char getGuess(String previousGuesses);


    /**
     * Handle the game play
     */
    @Override
    public abstract GameRecord play();


    /**
     * Checks if the next game
     */
    @Override
    public abstract boolean playNext();
}
