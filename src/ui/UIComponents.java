package ui;

import core.Position;
import core.Size;
import state.State;

import java.awt.*;

public abstract class UIComponents {
    protected Position realtivePostion;
    protected Position absulutePostion;
    protected Size size;
    protected Spacing margin;
    protected Spacing padding;

    public UIComponents() {
        realtivePostion =new Position(0,0);
        absulutePostion=new Position(0,0);
        size= new Size(1,1);
        margin=new Spacing(0);
        padding=new Spacing(0);
    }
    public abstract Image getSprite();
    public abstract void Update(State state);


    public void setRealtivePostion(Position realtivePostion) {
        this.realtivePostion = realtivePostion;
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

    public Position getRealtivePostion() {
        return realtivePostion;
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

    public Position getAbsulutePostion() {
        return absulutePostion;
    }

    public void setAbsulutePostion(Position absulutePostion) {
        this.absulutePostion = absulutePostion;
    }
}

