package ui.clickable;

import core.Size;
import state.State;
import ui.Spacing;
import ui.UIContainer;
import ui.UIText;
import ui.VerticalContainer;

import java.awt.*;

public class UIButton extends  UIClickable {

    private UIContainer container;
    private UIText label;

    private  ClickAction clickAction;

    public UIButton(String label,ClickAction clickEvent) {
        this.label= new UIText(label);
        this.clickAction = clickEvent;

        setMargin(new Spacing(5,0,0,0));

        container= new VerticalContainer(new Size(0,0));
        container.setCenterChildren(true);
        container.addUIComponent(this.label);
        container.setFixedSize(new Size(150,40));
    }

    @Override
    public void Update(State state){
        super.Update(state);
        container.Update(state);
        size= container.getSize();

        Color color=Color.GRAY;

        if(hasFocus){
            color= Color.LIGHT_GRAY;
        }
        if(isPressed){
            color= Color.WHITE;
        }

        container.setBackgroundColor(color);
    }

    @Override
    protected void onFocus(State state) {
    state.getAudioPlayer().playSound("button.wav");
    }

    @Override
    public void onDrag(State state) { }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    public void onClick(State state) {
        if(hasFocus){
            clickAction.execute(state);
        }

    }
}
