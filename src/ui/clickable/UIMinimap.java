package ui.clickable;

import core.Position;
import core.Size;
import display.Camera;
import game.Game;
import gfx.ImageUtils;
import map.GameMap;
import state.State;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIMinimap extends UIClickable{

    private double ratio;
    private Rectangle cameraViewBounds;
    private BufferedImage mapImage;
    private Color color;

    public UIMinimap(GameMap gameMap) {
        size = new Size(128,128);
        cameraViewBounds = new Rectangle(0,0,0,0);
        color = Color.GRAY;

        calculateRation(gameMap);
        generateMap(gameMap);
    }

    @Override
    public void Update(State state){
        super.Update(state);
        Camera camera = state.getCamera();
        cameraViewBounds = new Rectangle(
                (int) (camera.getPosition().getX() * ratio),
                (int) (camera.getPosition().getY() * ratio),
                (int) (camera.getSize().getWidth() * ratio),
                (int) (camera.getSize().getHeight() * ratio)
        );

        color = Color.GRAY;
        if(hasFocus){
            color = Color.WHITE;
        }
    }

    private void generateMap(GameMap gameMap) {
        mapImage = (BufferedImage) ImageUtils.createCompatbleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = mapImage.createGraphics();

        int pixelsPerGrid = (int) Math.round(Game.SPRITE_SIZE * ratio);

        for(int x = 0; x < gameMap.getTiles().length; x++){
            for(int y = 0; y < gameMap.getTiles()[0].length; y++){
                graphics.drawImage(
                        gameMap.getTiles()[x][y].getSprite().getScaledInstance(pixelsPerGrid, pixelsPerGrid, 0),
                        x * pixelsPerGrid + (size.getWidth() - gameMap.getTiles().length + pixelsPerGrid) / 2,
                        y * pixelsPerGrid + (size.getHeight() - gameMap.getTiles()[0].length + pixelsPerGrid) / 2,
                        null
                );
            }
        }
    }

    private void calculateRation(GameMap gameMap) {
        ratio = Math.min(
                size.getWidth() / (double) gameMap.getWidth(),
                size.getHeight() / (double) gameMap.getHeight()
        );
    }

    @Override
    protected void onFocus(State state) {

    }

    @Override
    protected void onDrag(State state) {
        Position mousePosition = Position.CopyOf(state.getInput().getMousePosition());
        mousePosition.subtract(absulutePostion);

        state.getCamera().setPosition(
                new Position(
                        mousePosition.getX() / ratio - cameraViewBounds.getSize().getWidth() / ratio / 2,
                        mousePosition.getY() / ratio - cameraViewBounds.getSize().getHeight() / ratio / 2
                )
        );
    }

    @Override
    protected void onClick(State state) {

    }

    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatbleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D graphics = sprite.createGraphics();

        graphics.drawImage(mapImage, 0,0, null);
        graphics.setColor(color);
        graphics.drawRect(0,0, size.getWidth()-1, size.getHeight()-1);

        graphics.draw(cameraViewBounds);
        graphics.dispose();
        return sprite;
    }
}
