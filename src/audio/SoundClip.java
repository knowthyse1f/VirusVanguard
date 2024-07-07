package audio;

import game.setting.GameSetting;

import javax.sound.sampled.Clip;

public class SoundClip extends AudioClip{

    public SoundClip(Clip clip) {
        super(clip);
    }

    @Override
    protected float getVolume(GameSetting gameSetting) {
        return gameSetting.getSoundVolume();
    }

}
