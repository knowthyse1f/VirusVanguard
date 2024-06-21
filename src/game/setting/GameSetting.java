package game.setting;

public class GameSetting {

    private boolean debugMood;
    private double gameSpeedMultiplier;

    public GameSetting(boolean debugMood) {

        this.debugMood = debugMood;
        gameSpeedMultiplier=1;
    }

    public boolean isDebugMood() {
        return debugMood;
    }

    public void toggleDebugMode() {
        debugMood= !debugMood;
    }

    public void increaseGameSpeed(){
        gameSpeedMultiplier+=0.25;
    }
    public void decreaseGameSpeed(){
        gameSpeedMultiplier-=0.25;
    }

    public double getGameSpeedMultiplier() {
        return gameSpeedMultiplier;
    }
}
