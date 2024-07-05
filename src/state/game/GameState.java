package state.game;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.Game;
import state.State;
import state.game.ui.UIGameTime;
import state.game.ui.UISicknaceStatistics;
import input.Input;
import map.GameMap;
import state.menu.MenuState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.util.List;

public class GameState extends State {

    private List<Condition>victoryCondtions;
    private List<Condition>defeateConditions;
    private boolean playing;

    public GameState(Size windowSize,Input input) {
        super(windowSize,input);
        gameMap= new GameMap(new Size(20,20), spriteLibrary);
        playing=true;
        initializeCharacters();
        initializeUI(windowSize);
        intializeConditions();
    }

    private void intializeConditions() {
        victoryCondtions=List.of(()-> getNumberOfSick()==0);
        defeateConditions=List.of(()-> (float)getNumberOfSick()/getNumberOfNPCs()>0.25);
    }

    private void initializeUI(Size windowSize) {

        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UISicknaceStatistics(windowSize));
    }

    private void initializeCharacters() {
        SelectionCircle circle = new SelectionCircle();
        Player player = new Player(new PlayerController(input), spriteLibrary, circle);

        gameObjects.add(player);
        camera.focusOn(player);
        gameObjects.add(circle);

        initializeNPCs(200);
        makeNumberOfNPCsSick(20);
    }

    private void makeNumberOfNPCsSick(int number) {
       getGameObjectsOfClass(NPC.class).stream()
               .limit(number)
               .forEach(npc -> npc.addEffect(new Sick()));
    }

    private void initializeNPCs(int numberOfNPCs) {

        for(int i=0;i<numberOfNPCs;i++){
            NPC npc = new NPC(new NPCController(),spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());

            gameObjects.add(npc);
        }
    }
    @Override
    public void update(Game game){
        super.update(game);
        if(playing){
            if(victoryCondtions.stream().allMatch(Condition::isMet)){
                win();
            }
            if(defeateConditions.stream().allMatch(Condition::isMet)){
                lost();
            }

        }
    }

    private  void lost() {
        playing=false;

        VerticalContainer lossContainer=new VerticalContainer(camera.getSize());
        lossContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
        lossContainer.addUIComponent(new UIText("loss"));
        uiContainers.add(lossContainer);
    }


    private void win() {
        playing=false;

        VerticalContainer winContainer=new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
         winContainer.setBackgroundColor(Color.DARK_GRAY);
        winContainer.addUIComponent(new UIButton("Menu",(state)-> state.setNextState(new MenuState(windowSize,input))));
        winContainer.addUIComponent(new UIButton("Options",(state)-> System.out.println("Button 1 pressed")));
        winContainer.addUIComponent(new UIButton("Exit",(state)-> System.exit(0)));

        uiContainers.add(winContainer);
    }

    public long getNumberOfSick(){
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffected(Sick.class)&& !npc.isAffected(Isolated.class))
                .count();
    }
    public long getNumberOfIsolated(){
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffected(Sick.class)&& npc.isAffected(Isolated.class))
                .count();
    }
    public long getNumberOfHealthy(){
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> !npc.isAffected(Sick.class))
                .count();
    }
    public long getNumberOfNPCs(){
        return getGameObjectsOfClass(NPC.class).size();

    }
}
