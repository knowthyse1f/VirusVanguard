package state;

import core.Position;
import core.Size;
import display.Camera;
import entity.GameObject;
import game.Game;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer>uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;
    protected Size windowSize;
    private State nextState;

    public State(Size windowSize, Input input) {
        this.input=input;
        this.windowSize=windowSize;
        spriteLibrary= new SpriteLibrary();
        gameObjects=new ArrayList<>();
        uiContainers=new ArrayList<>();
        camera=new Camera(windowSize);
        time=new Time();
    }
    public void update(Game game){
        time.update();
        sortObjectsByPosition();
        updateGameObjects();
        List.copyOf(uiContainers).forEach(uiContainer -> uiContainer.Update(this));
        camera.update(this);
        handleMouseInput();
        if(nextState !=null){
            game.enterState(nextState);
        }
    }

    private void handleMouseInput() {
        if(input.isMouseClicked()){
            System.out.println(String.format("MOUSE CLICKED AT POSITION x : %.0f  y: %.0f ", input.getMousePosition().getX(),input.getMousePosition().getY()));
        }
        input.clearMouseClicke();
    }

    private void updateGameObjects() {

        for(int i=0;i<gameObjects.size();i++){
            gameObjects.get(i).update(this);
        }
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder).thenComparing(gameObject -> gameObject.getPosition().getY()));
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

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());


    }

    public List<UIContainer> getUiContainers() {
        return uiContainers;
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
        return gameObjects.stream()
                .filter(clazz::isInstance)
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public SpriteLibrary getSpritelibrary() {
        return spriteLibrary;
    }

    public void spawn(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public Input getInput() {
        return input;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }
}
