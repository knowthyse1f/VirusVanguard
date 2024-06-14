package game.state;

import controller.PlayerController;
import core.Size;
import entity.GameObject;
import entity.Player;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;

    public State(Input input) {

        this.input=input;
        spriteLibrary= new SpriteLibrary();
        gameObjects=new ArrayList<>();


    }
    public void update(){
        gameObjects.forEach(gameObject -> gameObject.update());
    }
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
