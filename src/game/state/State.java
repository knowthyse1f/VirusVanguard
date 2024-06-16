package game.state;

import controller.PlayerController;
import core.Size;
import display.Camera;
import entity.GameObject;
import entity.Player;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;
    public State(Size windowSize, Input input) {
        this.input=input;
        spriteLibrary= new SpriteLibrary();
        gameObjects=new ArrayList<>();
        camera=new Camera(windowSize);
        time=new Time();
    }
    public void update(){
        sortObjectsByPosition();
        gameObjects.forEach(gameObject -> gameObject.update(this));
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }
}
