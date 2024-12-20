import java.util.Scanner;

public class WheelOfFortuneUserGame extends WheelOfFortuneInheritance {

    private final Scanner scanner;
    private boolean isFirstGame;


    /**
     * Constructor for the game
     */
    public WheelOfFortuneUserGame() {
        super();
        this.scanner = new Scanner(System.in);
        this.isFirstGame = true;
    }


    /**
     * Main method to run the user game
     */
    public static void main(String[] args) {
        WheelOfFortuneUserGame userGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = userGame.playAll();

        // Display results using AllGamesRecord methods
        System.out.println("User Game - Average Score: " + record.average());
        System.out.println("User Game - Top Scores: ");
        for (GameRecord gr : record.highGameList(3)) {
            System.out.println("Player: " + gr.getPlayerId() + ", Score: " + gr.getScore());
        }
        userGame.scanner.close();
    }


    /**
     * Code to get the guess from the user and check
     */
    @Override
    protected char getGuess(String previousGuesses) {
        char userEntered = ' ';
        while (true) {
            System.out.println("Previous Guesses: " + previousGuesses);
            System.out.print("Guess a Letter: ");
            String userInput = scanner.nextLine();
            if (userInput.length() == 1 && Character.isLetter(userInput.charAt(0))) {
                userEntered = Character.toLowerCase(userInput.charAt(0));
                break;
            } else {
                System.out.println("Invalid input. Please enter a single letter.");
            }
        }
        return userEntered;
    }


    /**
     * Code to start the game with lives and call other methods
     */
    @Override
    public GameRecord play() {
        phrase = selectRandomPhrase();
        if (phrase == null) {
            System.out.println("No more phrases available. Ending game.");
            return null;
        }
        hiddenPhrase = new StringBuilder(generateHiddenPhrase(phrase));
        previousGuesses.clear();

        int lives = 5;
        while (hiddenPhrase.toString().contains("*") && lives > 0) {
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);
            char guess = getGuess(previousGuesses.toString());
            if (!previousGuesses.contains(guess)) {
                lives = processGuess(guess, lives);
            } else {
                System.out.println("You've already guessed that letter.");
            }
        }

        // Show the final results to the user
        if (!hiddenPhrase.toString().contains("*")) {
            System.out.println("Congratulations! You've guessed the correct phrase: " + phrase);
        } else {
            System.out.println("You've run out of lives. The correct phrase was: " + phrase);
        }

        // Calculate the score based on the final state of the hidden phrase
        int revealedLetters = 0;
        for (int i = 0; i < hiddenPhrase.length(); i++) {
            if (hiddenPhrase.charAt(i) == phrase.charAt(i)) {
                revealedLetters++;
            }
        }
        int maxLetters = phrase.replaceAll("[^a-zA-Z]", "").length(); // Count only alphabet letters in the phrase
        int score = (revealedLetters == maxLetters) ? 100 : (int) ((double) revealedLetters / maxLetters * 100);

        // Return a GameRecord with the calculated score
        return new GameRecord(score, "User1");
    }


    /**
     * Code to ask user to play another game
     */
    @Override
    public boolean playNext() {
        if (isFirstGame) {
            isFirstGame = false;
            return true;
        }
        System.out.print("Do you want to play another game? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "WheelOfFortuneUserGame{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", previousGuesses=" + previousGuesses +
                ", remainingPhrases=" + phrases.size() +
                '}';
    }


    /**
     * Overriding the equals for rubric and if needed for debug
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WheelOfFortuneUserGame that = (WheelOfFortuneUserGame) obj;
        return phrase.equals(that.phrase) && hiddenPhrase.toString().equals(that.hiddenPhrase.toString()) && previousGuesses.equals(that.previousGuesses);
    }
}
