package state.menu.ui;

import core.Size;
import state.editor.EditorState;
import state.game.GameState;
import state.game.ui.UIOptionMenu;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;

public class UIMainMenu extends VerticalContainer {
    public UIMainMenu(Size windowSize) {
        super(windowSize);
        alignment =new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER);
        setBackgroundColor(Color.DARK_GRAY);

        addUIComponent(new UIText("Virus Vanguard"));
        addUIComponent(new UIButton("PLAY",(state)->state.setNextState(new GameState(windowSize,state.getInput(),state.getGameSetting()))));
        addUIComponent(new UIButton("OPTIONS",(state)->((MenuState)state).enterMenu(new UIOptionMenu(windowSize,state.getGameSetting()))));
        addUIComponent(new UIButton("EDITOR",(state)->state.setNextState(new EditorState(windowSize,state.getInput(),state.getGameSetting()))));
        addUIComponent(new UIButton("EXIT",(state)->System.exit(0)));
    }
}
