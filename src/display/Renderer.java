package display;

import core.Position;
import game.Game;
import game.state.State;
import map.GameMap;

import java.awt.*;

public class Renderer {
    public void render(State state, Graphics graphics){
    Camera camera=state.getCamera();
        renderMap(state,graphics);
        state.getGameObjects().stream().
                filter(gameObject->camera.isInView(gameObject)).
                forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().intX()-camera.getPosition().intX()-gameObject.getSize().getWidth()/2,
                gameObject.getPosition().intY()-camera.getPosition().intY()-gameObject.getSize().getHeight()/2,
                null

        ));

    }

    private void renderMap(State state, Graphics graphics) {
       GameMap map = state.getGameMap();
        Camera camera=state.getCamera();

        Position start=map.getVariableStartingGridPosition(camera);
        Position end=map.getVariableEndingGridPosition(camera);
        for(int x = start.intX(); x < end.intX();x++){
            for(int y= start.intY(); y< end.intY();y++){
                graphics.drawImage(
                       map.getTiles()[x][y].getSprite(),
                        x* Game.SPRITE_SIZE-camera.getPosition().intX(),
                        y*Game.SPRITE_SIZE-camera.getPosition().intY(),
                        null
                );
            }
        }
    }


}

