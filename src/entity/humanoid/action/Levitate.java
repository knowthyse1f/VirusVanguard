package entity.humanoid.action;

import entity.humanoid.Humanoid;
import state.State;

public class Levitate extends Action{
    public Levitate() {
        interruptable=false;
    }

    @Override
    public void update(State state, Humanoid humanoid) {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public String getAnimationName() {
        return "levitate";
    }

    @Override
    public String getSoundName() {
        return null;
    }
}
