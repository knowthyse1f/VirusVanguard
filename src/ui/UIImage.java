package ui;

import core.Size;
import state.State;

import java.awt.*;

public class UIImage extends UIComponents{

    private Image image;

    public UIImage(Image image) {
        this.image = image;
        size = new Size(image.getWidth(null), image.getHeight(null));
    }

    @Override
    public Image getSprite() {
        return image;
    }

    @Override
    public void Update(State state) {}
}
