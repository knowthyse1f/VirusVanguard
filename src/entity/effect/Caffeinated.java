package entity.effect;

import controller.Controller;
import entity.MovingEntity;
import game.GameLoop;
import game.state.State;

public class Caffeinated extends Effect {
    private double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND * 5);
        speedMultiplier = -1;
    }


    public void update(State state, MovingEntity entity) {
        super.update(state, entity);

        entity.multiplypeed(speedMultiplier);
    }
}
