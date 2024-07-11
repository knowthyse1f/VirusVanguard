package input.mouse;

import input.Input;
import input.mouse.action.MouseAction;
import state.State;
import ui.UIImage;

import java.util.Optional;

public class MouseHandelar {

    private MouseAction primaryButtonAction;
    private MouseConsumer activateConsumer;
    public void update(State state){
        final Input input =state.getInput();

        handlePrimaryButton(state);
        handleActiveConsumer( state,input);
        cleanUp(input);
    }

    private void handlePrimaryButton(State state) {
        if(primaryButtonAction!=null){
            setActivateConsumer(primaryButtonAction);
            primaryButtonAction.Update(state);
        }
    }

    private  void cleanUp(Input input) {
        if(!input.isMousePressed()){
            activateConsumer=null;
        }
        input.clearMouseClicke();
    }

    private void handleActiveConsumer(State state, Input input){
        if(activateConsumer !=null){
            if(input.isMouseClicked()){
                activateConsumer.onClick(state);
            }else if(input.isMousePressed()){
                activateConsumer.onDrag(state);
            }
        }
    }

    public MouseConsumer getActivateConsumer() {
        return activateConsumer;
    }

    public void setActivateConsumer(MouseConsumer mouseConsumer) {

        if(activateConsumer==null){
            activateConsumer = mouseConsumer;
        }

    }

    public void setPrimaryButtonAction(MouseAction primaryButtonAction) {
        this.primaryButtonAction = primaryButtonAction;
    }

    public Optional<UIImage> getPrimaryButtonUI(){
        if(primaryButtonAction!=null){
            return Optional.ofNullable(primaryButtonAction.getSprite());
        }

        return Optional.empty();
    }
}

