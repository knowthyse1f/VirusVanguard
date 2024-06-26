package entity.humanoid.effect;

import entity.humanoid.Humanoid;
import state.State;

public abstract class Effect {
    private int lifeSpanInUpdates;

    public Effect(int lifeSpanInUpdates) {
        this.lifeSpanInUpdates = lifeSpanInUpdates;
    }
    public void update(State state , Humanoid humanoid){
        lifeSpanInUpdates--;
    }
    public boolean shouldebeDeleted(){
        return  lifeSpanInUpdates<=0;
    }

}
