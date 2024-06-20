package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponents {

    protected Color backgroundColor;
    protected List<UIComponents> children;

    public UIContainer() {
        super();
        backgroundColor= Color.CYAN;
        margin = new Spacing(5);
        padding=new Spacing(5);
        children = new ArrayList<>();
        calculateSize();
        calculatePostion();

    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();
    private void calculateSize(){
        Size calculateContentSize = calculateContentSize();
        size= new Size(
                padding.getHorizontal() + calculateContentSize.getWidth(),
                padding.getVertical() + calculateContentSize.getHeight());
    }

    private void calculatePostion(){
        postion= new Position(margin.getLeft(), margin.getRight());
        calculateContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage image=(BufferedImage) ImageUtils.createCompatbleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics=image.createGraphics();

        graphics.setColor(backgroundColor);
        graphics.fillRect(0,0, size.getWidth(), size.getHeight() );

        for(UIComponents uiComponents : children){
            graphics.drawImage(
                    uiComponents.getSprite(),
                    uiComponents.getPostion().intX(),
                    uiComponents.getPostion().intY(),
                    null
            );
        }

        graphics.dispose();
        return image;
    }

    @Override
    public void Update(State state) {
        children.forEach(uiComponents -> uiComponents.Update(state));
        calculateSize();
        calculatePostion();

    }
    public void addUIComponent(UIComponents uiComponents){
        children.add(uiComponents);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor= color;

    }
}
