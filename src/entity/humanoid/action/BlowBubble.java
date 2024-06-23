package entity.humanoid.action;

import controller.NPCController;
import entity.Bubble;
import entity.humanoid.Humanoid;
import game.GameLoop;
import game.state.State;

public class BlowBubble extends  Action{

    private int lifeSpanINupdates;
    private Humanoid target;
    private Bubble bubble;

    public BlowBubble(Humanoid target) {
        lifeSpanINupdates= GameLoop.UPDATES_PER_SECOND;
        this.target = target;
    }

    @Override
    public void update(State state, Humanoid humanoid) {
        lifeSpanINupdates--;

        if(bubble==null){
            bubbleTarget(state);
        }
    }

    private void bubbleTarget(State state) {
        bubble=new Bubble(new NPCController(), state.getSpritelibrary());
        bubble.insert(target);
        state.spawn(bubble);
    }

    @Override
    public boolean isDone() {
        return lifeSpanINupdates==0;
    }

    @Override
    public String getAnimationName() {
        return "blow";
    }
}
