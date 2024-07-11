package map;

import gfx.SpriteLibrary;

import java.awt.*;

public class Tile {

    private Image sprite;

    public Tile(SpriteLibrary spriteLibrary) {
        this(spriteLibrary, "wdfloor");
    }
    public Tile(SpriteLibrary spriteLibrary, String tileName) {
        this.sprite = spriteLibrary.getImage(tileName);
    }

    public Image getSprite() {
        return sprite;
    }
}