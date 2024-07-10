package display;

import core.Position;
import game.Game;
import state.State;
import map.GameMap;

import java.awt.*;

public class Renderer {
    public void render(State state, Graphics graphics){
    Camera camera=state.getCamera();
        renderMap(state,graphics);
        renderGameObject(state,graphics);
        renderUI(state,graphics);
    }

    private void renderUI(State state, Graphics graphics) {
        state.getUiContainers().forEach(uiContainer -> graphics.drawImage(
                uiContainer.getSprite(),
                uiContainer.getRealtivePostion().intX(),
                uiContainer.getRealtivePostion().intY(),
                null
        ));
    }

    private  void renderGameObject(State state, Graphics graphics){
        Camera camera=state.getCamera();
        state.getGameObjects().stream()

                .filter(gameObject->camera.isInView(gameObject))
                .forEach(gameObject -> graphics.drawImage(
                        gameObject.getSprite(),
                        gameObject.getRenderPosition(camera).intX(),
                        gameObject.getRenderPosition(camera).intY(),
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

                if(state.getGameSetting().getRenderSettings().getShouldRenderGrid().getValue()) {


                    graphics.setColor(Color.GRAY);
                    graphics.drawRect(
                            x * Game.SPRITE_SIZE - camera.getPosition().intX(),
                            y * Game.SPRITE_SIZE - camera.getPosition().intY(),
                            Game.SPRITE_SIZE,
                            Game.SPRITE_SIZE

                    );
                }
            }
        }



    }


}

