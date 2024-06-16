package ai.state;

import ai.AiTransition;
import controller.NPCController;
import core.Position;
import entity.NPC;
import game.state.State;

import java.util.ArrayList;
import java.util.List;

public class Wander extends AiState{

    private List<Position> targets;

    public Wander() {
        targets = new ArrayList<>();
    }

    @Override
    protected AiTransition initializeTransition() {
        return null;
    }

    @Override
    public void update(State state, NPC currentCharacter) {
        if(targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }
        NPCController controller = (NPCController) currentCharacter.getController();
        controller.moveToTarget(targets.get(0),currentCharacter.getPosition());

        if(arrived(currentCharacter)){
            controller.stop();

        }

    }

    private boolean arrived(NPC currentCharacter){
        return currentCharacter.getPosition().isInRangeOf(targets.get(0));

    }
}
