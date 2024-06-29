package display;

import core.CollisionBox;
import entity.humanoid.Humanoid;
import game.state.State;
import ui.UIText;

import java.awt.*;
import java.util.stream.Collectors;

public class DebugRenderer {
    public void render(State state, Graphics graphics){
        Camera camera=state.getCamera();
        state.getGameObjects().stream().
                filter(gameObject->camera.isInView(gameObject))
                .map(gameObject-> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollitionBox(collisionBox,graphics,camera));
        drawEffects(state,graphics);
    }
    private void drawEffects(State state, Graphics graphics){
        Camera camera = state.getCamera();
        state.getGameObjectsOfClass(Humanoid.class).stream()
                .forEach(humanoid -> {
                    UIText effectText=new UIText("" +
                         humanoid.getEffects().stream().map(effect -> effect.getClass()
                                 .getSimpleName()).collect(Collectors.joining(","))
                            );
                    effectText.Update(state);
                    graphics.drawImage(
                            effectText.getSprite(),
                            humanoid.getPosition().intX()- camera.getPosition().intX(),
                            humanoid.getPosition().intY()-camera.getPosition().intY(),null
                    );
                });
    }

    private void drawCollitionBox(CollisionBox  collisionBox,Graphics graphics,Camera camera){
       graphics.setColor(Color.ORANGE);
       graphics.drawRect(
               (int)collisionBox.getBounds().getX()-camera.getPosition().intX(),
               (int)collisionBox.getBounds().getY()-camera.getPosition().intY(),
               (int)collisionBox.getBounds().getWidth(),
               (int)collisionBox.getBounds().getHeight()
       );
    }
}
