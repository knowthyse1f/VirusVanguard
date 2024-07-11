package input.mouse;

import input.Input;
import state.State;

public class MouseHandelar {
    private MouseConsumer activateConsumer;
    public void update(State state){
        final Input input =state.getInput();

        handleActiveConsumer( state,input);
        cleanUp(input);
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
}

