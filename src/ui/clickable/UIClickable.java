package ui.clickable;

import core.Position;
import game.state.State;
import ui.UIComponents;

import java.awt.*;

public abstract class UIClickable extends UIComponents {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void Update(State state) {
        Position mousePosition =state.getInput().getMousePosition();
        hasFocus=getBounds().contains(mousePosition.intX(),mousePosition.intY());
        isPressed=hasFocus && state.getInput().isMousePressed();

        if(hasFocus && state.getInput().isMouseClicked()){
            onClick();
        }
    }

    protected abstract void onClick();

    private Rectangle getBounds(){
        return new Rectangle(
                absulutePostion.intX(),
                absulutePostion.intY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
