package entity;

import ai.AiManager;
import controller.EntityController;
import core.Motion;
import entity.humanoid.Humanoid;
import state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

public class NPC extends Humanoid {
    private AiManager aiManager;

    public NPC(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        aiManager=new AiManager();
        motion = new Motion(Math.random()+1);
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
