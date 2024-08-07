package entity;

import controller.EntityController;
import core.Position;
import core.Vector2D;
import entity.humanoid.Humanoid;
import entity.humanoid.action.BlowBubble;
import entity.humanoid.action.WalkInDirection;
import entity.humanoid.effect.Isolated;
import game.Game;
import state.State;
import gfx.SpriteLibrary;

import java.util.Comparator;
import java.util.Optional;

public class Player extends Humanoid {

    private NPC target;
    private double targetRange;
    private SelectionCircle selectionCircle;

    public Player(EntityController entityController, SpriteLibrary spriteLibrary, SelectionCircle selectionCircle) {
       super(entityController,spriteLibrary);
       this.selectionCircle= selectionCircle;
       this.targetRange = Game.SPRITE_SIZE;

       setPosition(new Position(Game.SPRITE_SIZE * 5,0));
       perform(new WalkInDirection(new Vector2D(0,1)));

    }

    @Override
    public void update(State state){
        super.update(state);
        handleTarget(state);

        handleInput(state);
    }

    private void handleInput(State state) {
        if(entityController.isRequestingAction()){
            if(target!=null){
                perform(new BlowBubble(target));
            }
        }
    }

    private void handleTarget(State state) {
        Optional<NPC> closestNPC = findClosestNPC(state);

        if(closestNPC.isPresent()){
            NPC npc = closestNPC.get();

            if(!npc.equals(target)){
                selectionCircle.parent(npc);
                target = npc;
            }
        }else{
            selectionCircle.clearParent();
            target = null;
        }
    }

    private Optional<NPC> findClosestNPC(State state) {
        return state.getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> getPosition().distanceTo(npc.getPosition()) < targetRange)
                .filter(npc -> isFacing(npc.getPosition()))
                .filter(npc-> !npc.isAffected(Isolated.class))
                .min(Comparator.comparingDouble(npc -> position.distanceTo(npc.getPosition())));
    }

    @Override
    protected void handleCollision(GameObject other) {}


}
