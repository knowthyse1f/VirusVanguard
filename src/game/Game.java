package game;
import core.Size;
import display.Display;
import game.setting.GameSetting;
import game.state.GameState;
import game.state.State;
import input.Input;


public class Game {
    public static int SPRITE_SIZE=64;

    private Display display;
    private Input input;
    private State state;
    private GameSetting settings;

    public Game(int width , int height){

        input=new Input();
        display= new Display(width, height,input);
        state= new GameState(new Size(width,height),input);
        settings=new GameSetting(true);

    }
    public void update(){

        state.update();

    }
    public void render(){
        display.render(state, settings.isDebugMood());
    }


}
