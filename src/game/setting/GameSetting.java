package game.setting;

public class GameSetting {

    private boolean debugMood;
    private double gameSpeedMultiplier;

    private float musicVolume;
    private float soundVolume;

    public GameSetting(boolean debugMood) {

        this.debugMood = debugMood;
        gameSpeedMultiplier=1;
        musicVolume = 0;
        soundVolume = 0;
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

    public float getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    public float getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }
}
