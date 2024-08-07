package ui;

import core.Size;
import state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class UIContainer extends UIComponents {

    protected boolean centerChildren;
    protected Color backgroundColor;
    protected  Alignment alignment;
    protected  Size windowSize;

    protected Size fixedSize;

    protected List<UIComponents> children;


    public UIContainer(Size windowSize) {
        super();
        this.windowSize=windowSize;
        centerChildren = false;
        alignment= new Alignment(Alignment.Position.START, Alignment.Position.START);
        backgroundColor= new Color(0,0,0,0);
        margin = new Spacing(5);
        padding=new Spacing(5);
        children = new ArrayList<>();
        calculateSize();
        calculatePosition();

    }

    protected abstract Size calculateContentSize();
    protected abstract void calculateContentPosition();
    private void calculateSize(){
        Size calculateContentSize = calculateContentSize();
        size= fixedSize !=null ? fixedSize
    : new Size(
                padding.getHorizontal() + calculateContentSize.getWidth(),
                padding.getVertical() + calculateContentSize.getHeight());
    }

    private void calculatePosition() {
        int x = padding.getLeft();
        if (alignment.getHorizontal().equals(Alignment.Position.CENTER)) {
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        } else if (alignment.getHorizontal().equals(Alignment.Position.END)) {
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }

        int y = padding.getTop();
        if (alignment.getVertical().equals(Alignment.Position.CENTER)) {
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        } else if (alignment.getVertical().equals(Alignment.Position.END)) {
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }

        // Ensure to set the position
        this.realtivePostion = new core.Position(x, y);
        if(parent==null){
            this.absulutePostion= new core.Position(x,y);
        }
        // Add this line
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
                    uiComponents.getRealtivePostion().intX(),
                    uiComponents.getRealtivePostion().intY(),
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
        calculatePosition();

    }
    public void addUIComponent(UIComponents uiComponents){
        children.add(uiComponents);
        uiComponents.setParent(this);
    }

    public void setBackgroundColor(Color color) {
        backgroundColor= color;

    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setFixedSize(Size fixedSize) {
        this.fixedSize = fixedSize;
    }

    public void setCenterChildren(boolean centerChildren) {
        this.centerChildren = centerChildren;
    }
}
