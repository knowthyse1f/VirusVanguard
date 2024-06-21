package entity;

import ai.AiManager;
import controller.EntityController;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends MovingEntity{
    private AiManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
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
