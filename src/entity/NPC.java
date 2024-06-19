package entity;

import ai.AiManager;
import controller.Controller;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends MovingEntity{
    private AiManager aiManager;

    public NPC(Controller controller, SpriteLibrary spriteLibrary) {
        super(controller, spriteLibrary);
        animationManager = new AnimationManager(spriteLibrary.getUnit("dave"));
        aiManager=new AiManager();
    }

    @Override
    public void update(State state){
        super.update(state);
        aiManager.update(state,this);
    }

    @Override
    protected void handleCollision(GameObject other) {
        if(other instanceof Player){
            motion.stop(willCollideX(other), willCollideY(other));
        }
    }
}
