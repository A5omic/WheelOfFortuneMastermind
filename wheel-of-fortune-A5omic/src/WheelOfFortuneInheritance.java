public abstract class WheelOfFortuneInheritance extends GuessingGame {


    /**
     * Constructor
     */
    public WheelOfFortuneInheritance() {
        super("phrases.txt");
    }


    /**
     * Selects a random phrase
     */
    protected String selectRandomPhrase() {
        return selectRandomItem(); // Use GuessingGame's method
    }


    /**
     * Generates the hidden version of the phras
     */
    protected String generateHiddenPhrase() {
        return generateHiddenVersion(phrase); // Use GuessingGame's method
    }


    /**
     * Processes a user's guess
     */
    @Override
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
     * Abstract method to get the next guess
     */
    protected abstract char getGuess(String previousGuesses);


    /**
     * Abstract method to handle the game play
     */
    @Override
    public abstract GameRecord play();


    /**
     * Determines if the game should continue
     */
    @Override
    public abstract boolean playNext();


    /**
     * Overriding the toString
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