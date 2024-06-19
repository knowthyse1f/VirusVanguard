package display;

import core.CollisionBox;
import game.state.State;

import java.awt.*;

public class DebugRenderer {
    public void render(State state, Graphics graphics){
        Camera camera=state.getCamera();
        state.getGameObjects().stream().
                filter(gameObject->camera.isInView(gameObject))
                .map(gameObject-> gameObject.getCollisionBox())
                .forEach(collisionBox -> drawCollitionBox(collisionBox,graphics,camera));
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
