package game.ui;

import core.Size;
import game.state.State;
import ui.Alignment;
import ui.HorizontalContainer;
import ui.UIText;

public class UIGameTime extends HorizontalContainer {
    private UIText gameTime;

    public UIGameTime(Size windowSize) {
        super(windowSize);
        this.alignment= new Alignment(Alignment.Position.CENTER,Alignment.Position.START);
        this.gameTime=new UIText(" ");
        addUIComponent(gameTime);
    }

    @Override
    public  void Update(State state){
        super.Update(state);
        gameTime.setText(state.getTime().getFormattedTime());
    }
}
