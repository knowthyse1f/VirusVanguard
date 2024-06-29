package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.SelectionCircle;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Isolated;
import entity.humanoid.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknaceStatistics;
import input.Input;
import map.GameMap;

public class GameState extends State{

    public GameState(Size windowSize,Input input) {
        super(windowSize,input);
        gameMap= new GameMap(new Size(20,20), spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
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
        makeNumberOfNPCsSick(10);
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
    public long getNumberOfSick(){
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffected(Sick.class)&& !humanoid.isAffected(Isolated.class))
                .count();
    }
    public long getNumberOfIsolated(){
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffected(Sick.class)&& humanoid.isAffected(Isolated.class))
                .count();
    }
    public long getNumberOfHealthy(){
        return getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffected(Sick.class))
                .count();
    }
}
