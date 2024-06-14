package gfx;

import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {
    private static final String PATH_TO_UNITS = "/sprites/units/";
    private Map<String, SpriteSet> units;
    private Map<String, Image>tiles;

    public SpriteLibrary() {
        units = new HashMap<>();
        tiles = new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        loadUnits();
        loadTiles();

    }

    private void loadTiles() {
        BufferedImage image= new BufferedImage(Game.SPRITE_SIZE,Game.SPRITE_SIZE,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics=image.createGraphics();
        graphics.setColor(Color.red);
        graphics.drawRect(0,0,Game.SPRITE_SIZE,Game.SPRITE_SIZE);

        graphics.dispose();
        tiles.put("default",image);
    }

    private void loadUnits()
    {
        String[] folderNames = getFolderNames(PATH_TO_UNITS);

        if (folderNames != null) {
            for (String folderName : folderNames) {
                SpriteSet spriteSet = new SpriteSet();
                String pathToFolder = PATH_TO_UNITS + folderName;
                String[] sheetsInFolder = getSheetsInFolder(pathToFolder);

                if (sheetsInFolder != null) {
                    for (String sheetName : sheetsInFolder) {
                        String sheetBaseName = sheetName.substring(0, sheetName.length() - 4); // Assuming .png extension
                        spriteSet.addSheet(sheetBaseName, ImageUtils.loadImage(pathToFolder + "/" + sheetName));
                    }
                }

                units.put(folderName, spriteSet);
            }
        }
    }

    private String[] getSheetsInFolder(String basePath) {
        URL resource = getClass().getResource(basePath);
        if (resource == null) {
            return null;
        }
        File folder = new File(resource.getFile());
        return folder.list((current, name) -> new File(current, name).isFile());
    }

    private String[] getFolderNames(String basePath) {
        URL resource = getClass().getResource(basePath);
        if (resource == null) {
            return null;
        }
        File baseFolder = new File(resource.getFile());
        return baseFolder.list((current, name) -> new File(current, name).isDirectory());
    }

    public SpriteSet getUnit(String name) {
        return units.get(name);
    }

    public Image getTile(String name){
        return tiles.get(name);
    }
}
