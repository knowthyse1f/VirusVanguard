package game;

public class Time {
    private int updatedsSinceStart;

    public Time() {
        this.updatedsSinceStart = 0;
    }
    public int getUpdatesFromSeconds(int seconds){
        return seconds*GameLoop.UPDATES_PER_SECOND;
    }
}
