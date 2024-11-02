import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class WheelOfFortuneInheritance extends Game {

    protected String phrase;
    protected StringBuilder hiddenPhrase;
    protected List<Character> previousGuesses;
    protected List<String> phrases;


    /**
     * Constructor initializes the game state with phrases from a file
     */
    public WheelOfFortuneInheritance() {
        this.previousGuesses = new ArrayList<>();
        this.phrases = readPhrasesFromFile("phrases.txt");
    }


    /**
     * gets a random phrase form the text file
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
        return phrases.remove(index);
    }


    /**
     * Generates the hidden version of the phrase
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
        if (previousGuesses.contains(guess)) {
            System.out.println("You've already guessed that letter, no lives were lost!");
            return lives;
        }
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
     * Abstract method to get the next guess from a player
     */
    protected abstract char getGuess(String previousGuesses);


    /**
     * Abstract method to handle the game play
     */
    @Override
    public abstract GameRecord play();


    /**
     * Checks if the next game should be played
     */
    @Override
    public abstract boolean playNext();


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "WheelOfFortuneInheritance{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", previousGuesses=" + previousGuesses +
                '}';
    }
}
