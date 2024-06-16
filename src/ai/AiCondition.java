package ai;

import entity.NPC;
import game.state.State;

public interface AiCondition {
    boolean isMet(State state, NPC currentCharecter);

}
