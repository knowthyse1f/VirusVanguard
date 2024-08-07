package state.menu;

import core.Size;
import game.setting.GameSetting;
import input.Input;
import map.GameMap;
import map.MapIO;
import state.State;
import state.menu.ui.UIMainMenu;
import ui.UIContainer;

public class MenuState extends State {
    public MenuState(Size windowSize, Input input, GameSetting gameSetting) {
        super(windowSize, input, gameSetting);
        gameMap= MapIO.load(spriteLibrary);
        gameSetting.getRenderSettings().getShouldRenderGrid().setValue(false);

        uiContainers.add(new UIMainMenu(windowSize));

        audioPlayer.playMusic("isobubbler.wav");
    }
    public void enterMenu(UIContainer container){
        uiContainers.clear();
        uiContainers.add(container);
    }
}
