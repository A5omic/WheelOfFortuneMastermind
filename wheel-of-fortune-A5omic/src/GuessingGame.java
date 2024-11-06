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
     * Constructor
     */
    public GuessingGame(String fileName) {
        this.previousGuesses = new ArrayList<>();
        if (fileName != null) {
            this.gameData = readDataFromFile(fileName);
        } else {
            this.gameData = new ArrayList<>();
        }
    }


    /**
     * Reads data
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
//        String selectedItem = gameData.remove(index);
        String selectedItem = gameData.get(index);
        return selectedItem;
    }


    /**
     * Generates a hidden version of the given item
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
     * Abstract method to get the next guess from the player
     */
    protected abstract String getGuess();


    /**
     * Plays a single game
     */
    @Override
    public abstract GameRecord play();


    /**
     * Determines if the game should continue to another round
     */
    @Override
    public abstract boolean playNext();


    /**
     * Plays a series of games until the player decides to stop
     */
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        int gameCount = 1;

        System.out.printf("Playing game %d%n", gameCount);
        GameRecord record = play();

        if (record != null) {
            allGamesRecord.add(record);
        }

        while (playNext()) {
            System.out.printf("Playing game %d%n", ++gameCount);
            record = play();

            if (record != null) {
                allGamesRecord.add(record);
            }
        }
        System.out.printf("All games completed. Total games played: %d%n", gameCount);
        return allGamesRecord;
    }
}
