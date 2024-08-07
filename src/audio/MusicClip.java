package audio;

import game.setting.AudioSettings;
import game.setting.GameSetting;

import javax.sound.sampled.Clip;

public class MusicClip extends AudioClip{
    public MusicClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(AudioSettings audioSettings) {
        return audioSettings.getMusicVolume();
    }
}
