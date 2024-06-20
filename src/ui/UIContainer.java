package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIContainer extends UIComponents {

    private Color backgroundColor;

    public UIContainer() {
        super();
        backgroundColor= Color.CYAN;
        calculateSize();
        calculatePostion();

    }
    private void calculateSize(){
        size= new Size(padding.getHorizontal(), padding.getVertical());
    }

    private void calculatePostion(){
        postion= new Position(margin.getLeft(), margin.getRight());
    }

    @Override
    public Image getSprite() {
        BufferedImage image=(BufferedImage) ImageUtils.createCompatbleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics=image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0,0, size.getWidth(), size.getHeight() );
        graphics.dispose();
        return image;
    }

    @Override
    public void Update(State state) {
        calculateSize();
        calculatePostion();

    }

}
