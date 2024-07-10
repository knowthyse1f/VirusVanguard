package game.setting;

public class GameSetting {

    private boolean debugMood;
    private double gameSpeedMultiplier;

    private final AudioSettings audioSettings;
    private final RenderSettings renderSettings;



    public GameSetting(boolean debugMood) {

        this.debugMood = debugMood;
        gameSpeedMultiplier=1;
        audioSettings = new AudioSettings();
        renderSettings = new RenderSettings();

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



    public AudioSettings getAudioSettings() {
        return audioSettings;
    }

    public RenderSettings getRenderSettings() {
        return renderSettings;
    }
}
