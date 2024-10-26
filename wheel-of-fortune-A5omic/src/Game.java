public abstract class Game {

    public abstract GameRecord play();
    public abstract boolean playNext();


    /**
     * play all that uses the all games record to make a new record, start playing and add the record
     */
    public AllGamesRecord playAll() {
        AllGamesRecord allGamesRecord = new AllGamesRecord();
        while (playNext()) {
            GameRecord record = play();
            allGamesRecord.add(record);
        }
        return allGamesRecord;
    }
}
