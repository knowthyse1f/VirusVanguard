package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entity.NPC;
import entity.Player;
import entity.action.Cough;
import entity.effect.Sick;
import input.Input;
import map.GameMap;
import ui.*;

import java.awt.*;

public class GameState extends State{

    public GameState(Size windowSize,Input input) {
        super(windowSize,input);
        gameMap= new GameMap(new Size(20,20), spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
    }

    private void initializeUI(Size windowSize) {
        UIContainer container=new VerticalContainer(windowSize);
        container.setPadding(new Spacing(5));
        container.setBackgroundColor(new Color(0,0,0, 0));

        UIContainer containerEnd=new VerticalContainer(windowSize);
        containerEnd.setPadding(new Spacing(5));
        containerEnd.setBackgroundColor(new Color(0,0,0, 0));
        containerEnd.setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        container.addUIComponent(new UIText("Hello Hello Tajree"));
        containerEnd.addUIComponent(new UIText("Hello Hello Safu"));
        uiContainers.add(container);
        uiContainers.add(containerEnd);
    }

    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);

        gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(200);
    }

    private void initializeNPCs(int numberOfNPCs) {

        for(int i=0;i<numberOfNPCs;i++){
            NPC npc = new NPC(new NPCController(),spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.addEffect(new Sick());
            gameObjects.add(npc);
        }
    }
}
