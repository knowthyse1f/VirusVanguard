package ai.state;

import ai.AiTransition;
import entity.NPC;
import game.state.State;

public class Stand extends AiState{
    private  int updatesAlive;
    @Override
    protected AiTransition initializeTransition() {
        return new AiTransition("stand",((state, currentCharecter) -> updatesAlive>=state.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, NPC currentCharecter) {
    updatesAlive++;
    }
}
