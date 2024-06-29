package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknaceStatistics;
import input.Input;
import map.GameMap;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;

import java.util.List;

public class GameState extends State{

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
        makeNumberOfNPCsSick(0);
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
    public void update(){
        super.update();
        if(playing){
            if(victoryCondtions.stream().allMatch(Condition::isMet)){
                win();
            }
            else if(defeateConditions.stream().allMatch(Condition::isMet)){
                lost(this);
            }
            else{
                draw();
            }
        }
    }

    private static void lost(GameState gameState) {
        gameState.playing =false;

        VerticalContainer lossContainer=new VerticalContainer(gameState.camera.getSize());
        lossContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
        lossContainer.addUIComponent(new UIText("LOSE"));
        gameState.uiContainers.add(lossContainer);
    }

    private void draw() {
        playing=false;

        VerticalContainer drawContainer=new VerticalContainer(camera.getSize());
        drawContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
        drawContainer.addUIComponent(new UIText("DRAW"));
        uiContainers.add(drawContainer);

    }

    private void win() {
        playing=false;

        VerticalContainer winContainer=new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER,Alignment.Position.CENTER));
        winContainer.addUIComponent(new UIText("VICTORY"));
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
