package ui;

import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class UIComponents {
    protected Position postion;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponents() {
        postion =new Position(0,0);
        size= new Size(1,1);
        margin=new Spacing(0);
        padding=new Spacing(5);
    }
    public abstract Image getSprite();
    public abstract void Update(State state);


    public void setPostion(Position postion) {
        this.postion = postion;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setMargin(Spacing margin) {
        this.margin = margin;
    }

    public void setPadding(Spacing padding) {
        this.padding = padding;
    }

    public Position getPostion() {
        return postion;
    }

    public Size getSize() {
        return size;
    }

    public Spacing getMargin() {
        return margin;
    }

    public Spacing getPadding() {
        return padding;
    }
}

