public class GameRecord implements Comparable<GameRecord> {

    private final int score;
    private final String playerId;


    /**
     * Constructor for the records
     */
    public GameRecord(int score, String playerId) {
        this.score = score;
        this.playerId = playerId;
    }


    /**
     * Getter to return the score
     */
    public int getScore() {
        return score;
    }


    /**
     * Getter to return the player id
     */
    public String getPlayerId() {
        return playerId;
    }


    /**
     * Overriding the compare function to compare the scores
     */
    @Override
    public int compareTo(GameRecord other) {
        return Integer.compare(other.score, this.score);
    }
}
