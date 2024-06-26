package ai;

import entity.NPC;
import state.State;

public class AiTransition {
    private String nextState;
    private AiCondition condition ;

    public AiTransition(String nextState, AiCondition condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    public boolean shouldTransition(State state, NPC currentCharecter){
     return condition.isMet(state,currentCharecter);
    }

    public String getNextState() {
        return nextState;
    }
}
