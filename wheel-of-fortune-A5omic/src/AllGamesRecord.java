import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllGamesRecord {

    private final List<GameRecord> records;


    /**
     * Sets an empty list to store GameRecord instances.
     */
    public AllGamesRecord() {
        records = new ArrayList<>();
    }


    /**
     * Adds a new GameRecord to the list of records when called
     */
    public void add(GameRecord record) {
        records.add(record);
    }


    /**
     * Calculates the average score of all the games
     */
    public double average() {
        return records.stream()
                .mapToInt(GameRecord::getScore)
                .average()
                .orElse(0.0);
    }


    /**
     * Calculates the average score of games that are done by a player
     */
    public double average(String playerId) {
        return records.stream()
                .filter(record -> record.getPlayerId().equals(playerId))
                .mapToInt(GameRecord::getScore)
                .average()
                .orElse(0.0);
    }


    /**
     * Returns a list of the top scores across all games.
     */
    public List<GameRecord> highGameList(int n) {
        return records.stream()
                .sorted()
                .limit(n)
                .collect(Collectors.toList());
    }


    /**
     * Returns a list of the top scores for a player.
     */
    public List<GameRecord> highGameList(String playerId, int n) {
        return records.stream()
                .filter(record -> record.getPlayerId().equals(playerId))
                .sorted()
                .limit(n)
                .collect(Collectors.toList());
    }
}