import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Mastermind extends GuessingGame {
    private static final int CODESIZE = 4;
    private static final String COLORS = "RGBYOP"; // R, G, B, Y, O, P
    private String secretCode;
    private Scanner scanner;

    /**
     * Constructor
     */
    public Mastermind() {
        super(null);
        this.scanner = new Scanner(System.in);
        generateSecretCode();
    }


    /**
     * Generates a random secret code using the defined colors
     */
    private void generateSecretCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder(CODESIZE);
        for (int i = 0; i < CODESIZE; i++) {
            code.append(COLORS.charAt(random.nextInt(COLORS.length())));
        }
        secretCode = code.toString();
    }


    /**
     * Get the guess from the user.
     */
    @Override
    protected String getGuess() {
        System.out.print("Enter your guess (e.g., 'RGRY'): ");
        String guess = scanner.nextLine().toUpperCase().trim();
        if (guess.length() == CODESIZE && guess.chars().allMatch(c -> COLORS.indexOf(c) >= 0)) {
            return guess;
        } else {
            System.out.println("Invalid input. Please enter a valid guess with the correct format.");
            return getGuess();
        }
    }

    /**
     * Checks if the user wants to play another game
     */
    @Override
    public boolean playNext() {
        System.out.print("Do you want to play another game? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }


    /**
     * Plays the game
     */
    @Override
    public GameRecord play() {
        generateSecretCode();
        int attempts = 0;
        boolean won = false;

        while (attempts < 10 && !won) { // Allow up to 10 attempts
            String guess = getGuess();
            attempts++;

            int exactMatches = checkExacts(new StringBuilder(secretCode), new StringBuilder(guess));
            int partialMatches = checkPartials(new StringBuilder(secretCode), new StringBuilder(guess));

            if (exactMatches == CODESIZE) {
                won = true;
                System.out.println("Congratulations! You've cracked the code!");
            } else {
                System.out.println(exactMatches + " exact, " + partialMatches + " partial.");
            }
        }

        int score = won ? 100 : 0;
        System.out.println("The secret code was: " + secretCode);
        return new GameRecord(score, "User");
    }


    /**
     * Checks the number of exact matches
     */
    private int checkExacts(StringBuilder secretSB, StringBuilder guessSB) {
        int exacts = 0;
        for (int i = 0; i < CODESIZE; i++) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts++;
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i, '*');
            }
        }
        return exacts;
    }


    /**
     * Checks the number of partial matches
     */
    private int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        int partials = 0;
        for (int i = 0; i < CODESIZE; i++) {
            for (int j = 0; j < CODESIZE; j++) {
                if (secretSB.charAt(i) == guessSB.charAt(j) && secretSB.charAt(i) != '-') {
                    partials++;
                    secretSB.setCharAt(i, '-');
                    guessSB.setCharAt(j, '*');
                    break;
                }
            }
        }
        return partials;
    }


    /**
     * Main method to run the Mastermind
     */
    public static void main(String[] args) {
        Mastermind game = new Mastermind();
        AllGamesRecord record = game.playAll();

        // Display game statistics
        System.out.println("=== Mastermind Game Summary ===");
        System.out.println("Average Score: " + record.average());

        // Display top 3 scores across all games
        System.out.println("Top 3 Scores:");
        List<GameRecord> topScores = record.highGameList(3);
        for (GameRecord gr : topScores) {
            System.out.println("Player: " + gr.getPlayerId() + ", Score: " + gr.getScore());
        }
    }
}
