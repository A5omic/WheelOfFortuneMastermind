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


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerId='" + playerId + '\'' +
                '}';
    }


    /**
     * Overriding the equals for rubric and if needed for debug
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GameRecord that = (GameRecord) obj;
        return score == that.score && playerId.equals(that.playerId);
    }
}
