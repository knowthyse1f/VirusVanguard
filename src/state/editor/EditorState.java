package state.editor;
import core.Size;
import game.setting.GameSetting;
import input.Input;
import input.mouse.action.TilePlacer;
import map.GameMap;
import map.Tile;
import state.State;
import state.editor.ui.UIButtonMenu;
import state.editor.ui.UIRenderSettings;

public class EditorState extends State {
    public EditorState(Size windowSize, Input input, GameSetting gameSetting) {
        super(windowSize, input, gameSetting);
        gameMap= new GameMap(new Size(40,32), spriteLibrary);
        gameSetting.getRenderSettings().getShouldRenderGrid().setValue(true);
        mouseHandelar.setPrimaryButtonAction(new TilePlacer(new Tile(spriteLibrary, "grass")));

        uiContainers.add(new UIButtonMenu(windowSize));
        uiContainers.add(new UIRenderSettings(windowSize, gameSetting.getRenderSettings(), gameMap));
    }
}
