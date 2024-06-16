package ai.state;

import ai.AiTransition;
import entity.NPC;
import game.state.State;

public abstract class AiState {
    private AiTransition transition;

    public AiState() {
        this.transition = initializeTransition();
    }

    protected abstract AiTransition initializeTransition();
    public abstract void update (State state, NPC currentCharacter);
    public boolean shouldTransition(State state, NPC currentCharecter){
        return transition.shouldTransition(state,currentCharecter);
    }
    public String getNextState(){
        return transition.getNextState();
    }

}
