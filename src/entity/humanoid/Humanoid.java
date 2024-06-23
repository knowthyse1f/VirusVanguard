package entity.humanoid;

import controller.EntityController;
import core.Position;
import core.Size;
import entity.GameObject;
import entity.MovingEntity;
import entity.humanoid.action.Action;
import entity.humanoid.effect.Effect;
import game.state.State;
import gfx.SpriteLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Humanoid extends MovingEntity {
    protected List<Effect> effects;
    protected Optional<Action> action;

    public Humanoid(EntityController entityController, SpriteLibrary spriteLibrary) {
        super(entityController, spriteLibrary);
        effects=new ArrayList<>();
        action= Optional.empty();

        this.collisionBoxSize=new Size(16,26);
        this.renderOffset = new Position(size.getWidth()/2, size.getHeight()-12);
        this.collisionBoxOffset = new Position(collisionBoxSize.getWidth()/2, collisionBoxSize.getHeight());

    }
    @Override
    public void update(State state){
        super.update(state);
        handleAction(state);
        effects.forEach(effect -> effect.update(state,this));
        cleanup();

    }
    protected void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldebeDeleted)
                .forEach(effects::remove);
        if(action.isPresent() && action.get().isDone()){
            action= Optional.empty();
        }
    }
    @Override
    protected  String decideAnimation(){
        if(action.isPresent()){
            return action.get().getAnimationName();
        }
        else if(motion.isMoving()){
            return "walk";
        }
            return "stand";
    }

    private void handleAction(State state) {
        if(action.isPresent()){
            action.get().update(state, this);
        }
    }

    protected void handleMotion() {

        if(action.isPresent()){
            motion.stop(true,true);
        }
    }
    public void perform(Action action) {
        this.action= Optional.of(action);
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    protected void clearEffects() {
        effects.clear();
    }
    public boolean isAffected(Class<?> clazz) {
        return  effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }
    @Override
    protected void handleCollision(GameObject other) {

    }
}
