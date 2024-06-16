package ai;

import ai.state.AiState;
import ai.state.Stand;
import entity.NPC;
import game.state.State;

public class AiManager {
    private AiState currentAiState;

    public AiManager() {
        transitioTo("stand");
    }

    public void update(State state ,NPC currentCharecter){
        currentAiState.update(state, currentCharecter);

        if(currentAiState.shouldTransition(state, currentCharecter)){
            transitioTo(currentAiState.getNextState());
        }
    }

    private void transitioTo(String nextState) {
        System.out.println("Transition to "+nextState);
        switch (nextState){
            case "stand":
            default:
                currentAiState= new Stand();
        }
    }
}
