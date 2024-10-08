package game;

public class Time {
    private int updatedsSinceStart;

    public Time() {
        this.updatedsSinceStart = 0;
    }
    public int getUpdatesFromSeconds(double seconds){
        return (int) Math.round(seconds*GameLoop.UPDATES_PER_SECOND);
    }
    public void update(){
        updatedsSinceStart++;
    }
    public String getFormattedTime(){
        StringBuilder stringBuilder=  new StringBuilder();
        int minutes= updatedsSinceStart/GameLoop.UPDATES_PER_SECOND/60;
        int seconds= updatedsSinceStart/GameLoop.UPDATES_PER_SECOND%60;

        if(minutes<10){
            stringBuilder.append(0);
        }
        stringBuilder.append(minutes);
        stringBuilder.append(":");
        if(seconds<10){
            stringBuilder.append(0);
        }
        stringBuilder.append(seconds);
        return  stringBuilder.toString();
    }

    public boolean secondsDividableBy(double seconds) {
        return updatedsSinceStart % getUpdatesFromSeconds(seconds) == 0;
    }

    public int getUpdatedsSinceStart() {
        return updatedsSinceStart;
    }
}
