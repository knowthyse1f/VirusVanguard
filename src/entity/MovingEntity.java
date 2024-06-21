package entity;

import controller.EntityController;
import core.*;
import entity.action.Action;
import entity.effect.Effect;
import game.state.State;
import gfx.AnimationManager;
import gfx.SpriteLibrary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class  MovingEntity extends GameObject{

    protected EntityController entityController;
    protected Motion motion;
    protected AnimationManager animationManager;
    protected Direction direction;
    protected List<Effect>effects;
    protected Optional<Action>action;
    protected Size collisionBoxSize;

    public MovingEntity(EntityController entityController, SpriteLibrary spriteLibrary) {
        super();
        this.entityController = entityController;
        this.motion= new Motion(2);
        this.direction=Direction.S;
        this.animationManager=new AnimationManager(spriteLibrary.getUnit("matt"));
        effects=new ArrayList<>();
        action= Optional.empty();
        this.collisionBoxSize=new Size(16,26);
    }

    @Override
    public void update(State state){


        handleAction(state);
        handleMotion();

        animationManager.update(direction);
        effects.forEach(effect -> effect.update(state,this));

        handleCollisions(state);

        manageDirection();
        decideAnimation();

        position.apply(motion);

        cleanup();

    }

    private void handleCollisions(State state) {
        state.getCollidingGameObjects(this).forEach(this::handleCollision);

    }

    protected abstract void handleCollision(GameObject other);


    private void handleMotion() {

        if(!action.isPresent()){
            motion.update(entityController);
        }
        else{
            motion.stop(true,true);
        }

    }

    private void handleAction(State state) {
        if(action.isPresent()){
            action.get().update(state, this);
        }
    }

    private void cleanup() {
        List.copyOf(effects).stream()
                .filter(Effect::shouldebeDeleted)
                .forEach(effects::remove);
        if(action.isPresent() && action.get().isDone()){
            action= Optional.empty();
        }
    }

    private void decideAnimation() {

        if(action.isPresent()){
            animationManager.playAnimation(action.get().getAnimationName());
        }
        else if(motion.isMoving()){
            animationManager.playAnimation("walk");
        }else{
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection() {
        if(motion.isMoving()){
            this.direction=Direction.fromMotion(motion);
        }
    }

    @Override
    public boolean collidesWith(GameObject other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    @Override
    public CollisionBox getCollisionBox() {

        Position positionWithMotion= Position.CopyOf(position);
        positionWithMotion.apply(motion);

        return new CollisionBox(
                new Rectangle(
                        positionWithMotion.intX(),
                        positionWithMotion.intY(),
                        collisionBoxSize.getWidth(),
                        collisionBoxSize.getHeight()
                )
        );
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public EntityController getController() {
        return entityController;
    }

    public void multiplypeed(double multiplier) {
    motion.multiply(multiplier);
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
    public boolean willCollideX(GameObject other){
        CollisionBox otherBox=other.getCollisionBox();
        Position positionWithXApplied =Position.CopyOf(position);
        positionWithXApplied.applyx(motion);
        return CollisionBox.of(positionWithXApplied,collisionBoxSize).collidesWith(otherBox);
    }
    public boolean willCollideY(GameObject other){
        CollisionBox otherBox=other.getCollisionBox();
        Position positionWithYApplied= Position.CopyOf(position);
        positionWithYApplied.apply(motion);

        return CollisionBox.of(positionWithYApplied,collisionBoxSize).collidesWith(otherBox);
    }

    public boolean isAffected(Class<?> clazz) {
        return  effects.stream()
                .anyMatch(effect -> clazz.isInstance(effect));
    }
}
