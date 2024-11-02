public interface WheelOfFortunePlayer {

    /**
     * what is the users guess passing in the previous guesses and the hidden phrase
     */
    char nextGuess(String previousGuesses, String hiddenPhrase);

    /**
     * The players ids
     */
    String playerId();

    /**
     * If they want to reset
     */
    void reset();
}
