package entity.effect;

import game.GameLoop;

public class Caffeinated extends Effect{
    private  double speedMultiplier;

    public Caffeinated() {
        super(GameLoop.UPDATES_PER_SECOND*5);
    }
}
