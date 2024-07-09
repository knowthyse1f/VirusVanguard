package state.game.ui;

import core.Size;
import game.setting.GameSetting;
import state.State;
import state.menu.MenuState;
import state.menu.ui.UIMainMenu;
import ui.*;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

import java.awt.*;

public class UIOptionMenu extends VerticalContainer {

    private UISlider musicVolSlider;
    private UIText musicVolLabel;

    private UISlider soundVolSlider;
    private UIText soundVolLabel;


    public UIOptionMenu(Size windowSize, GameSetting gameSetting) {
        super(windowSize);
        alignment=new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER);
        musicVolSlider = new UISlider(0, 1);
        musicVolSlider.setValue(gameSetting.getAudioSettings().getMusicVolume());
        musicVolLabel = new UIText("");

        soundVolSlider=new UISlider(0,1);
        soundVolSlider.setValue(gameSetting.getAudioSettings().getSoundVolume());
        soundVolLabel=new UIText(" ");

        UIContainer labelContainer= new VerticalContainer(windowSize);
        labelContainer.setBackgroundColor(Color.DARK_GRAY);
        addUIComponent(new UIText("OPTIONS"));

        UIContainer contentContainer=new VerticalContainer(windowSize);
        contentContainer.setMargin(new Spacing(0));
        contentContainer.setPadding(new Spacing(10));

        contentContainer.setBackgroundColor(Color.LIGHT_GRAY);
        contentContainer.addUIComponent(musicVolLabel);
        contentContainer.addUIComponent(musicVolSlider);
        contentContainer.addUIComponent(soundVolLabel);
        contentContainer.addUIComponent(soundVolSlider);
        contentContainer.addUIComponent(new UIButton("BACK",(state)->((MenuState)state).enterMenu(new UIMainMenu(windowSize))));

        addUIComponent(labelContainer);
        addUIComponent(contentContainer);
    }

    @Override

    public void Update(State state){
        super.Update(state);
        handleVolume(state);
    }

    private void handleVolume(State state) {
        state.getGameSetting().getAudioSettings().setMusicVolume((float) musicVolSlider.getValue());
        musicVolLabel.setText(String.format("MUSIC VOL : %d", Math.round(musicVolSlider.getValue()*100)));

        state.getGameSetting().getAudioSettings().setSoundVolume((float) soundVolSlider.getValue());
        soundVolLabel.setText(String.format("SOUND VOL : %d", Math.round(soundVolSlider.getValue()*100)));
    }

}
