public interface WheelOfFortunePlayer {
    char nextGuess(String previousGuesses, String hiddenPhrase);

    String playerId();

    void reset();
}
