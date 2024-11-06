public abstract class Game {

    public abstract GameRecord play();
    public abstract boolean playNext();


    /**
     * play all that uses the all games record to make a new record, start playing and add the record
     */
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        int gameCount = 1;
        while (playNext()) {
            System.out.println("Playing game " + gameCount);
            GameRecord record = play();
            allGamesRecord.add(record);
            gameCount++;
        }
        System.out.println("All games completed. Total games played: " + (gameCount - 1));
        return allGamesRecord;
    }


    /**
     * Overriding the toString for rubric and if needed to print for debug
     */
    @Override
    public String toString() {
        return "Game instance";
    }
}
