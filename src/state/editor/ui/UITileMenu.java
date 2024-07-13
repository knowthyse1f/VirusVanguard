package state.editor.ui;

import core.Size;
import gfx.SpriteLibrary;
import map.Tile;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.clickable.UITileToggle;

import java.awt.*;

public class UITileMenu extends HorizontalContainer {
    public UITileMenu(Size windowSize, SpriteLibrary spriteLibrary) {
        super(windowSize);
        setBackgroundColor(Color.DARK_GRAY);
        setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.END));

        addUIComponent(new UITileToggle(new Tile(spriteLibrary,"wdfloor")));
        addUIComponent(new UITileToggle(new Tile(spriteLibrary,"grass")));
    }
}
