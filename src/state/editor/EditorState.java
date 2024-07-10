package state.editor;
import core.Size;
import game.setting.GameSetting;
import input.Input;
import map.GameMap;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSetting gameSetting) {
        super(windowSize, input, gameSetting);
        gameMap= new GameMap(new Size(20,20), spriteLibrary);

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSetting.getRenderSettings()));
    }
}
