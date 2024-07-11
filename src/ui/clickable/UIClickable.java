package ui.clickable;

import core.Position;
import input.mouse.MouseConsumer;
import state.State;
import ui.UIComponents;

import java.awt.*;

public abstract class UIClickable extends UIComponents implements MouseConsumer {

    protected boolean hasFocus;
    protected boolean isPressed;

    @Override
    public void Update(State state) {
        Position mousePosition =state.getInput().getMousePosition();
        boolean previousFocus=hasFocus;

        hasFocus=getBounds().contains(mousePosition.intX(),mousePosition.intY());
        isPressed=hasFocus && state.getInput().isMousePressed();
        if(!previousFocus && hasFocus){
            onFocus(state);
        }
        if(hasFocus){
            state.getMouseHandelar().setActivateConsumer(this);
        }

    }

    protected abstract void onFocus(State state);

    private Rectangle getBounds(){
        return new Rectangle(
                absulutePostion.intX(),
                absulutePostion.intY(),
                size.getWidth(),
                size.getHeight()
        );
    }
}
