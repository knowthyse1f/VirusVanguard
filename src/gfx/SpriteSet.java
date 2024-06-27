package gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private Map<String , Image> aninationSheets;

    public SpriteSet() {
        this.aninationSheets=new HashMap<>();
    }

    public SpriteSet(Image image) {
        this.aninationSheets=new HashMap<>();
        addSheet("default",image);
    }

    public void addSheet(String name, Image aninationSheet){
        aninationSheets.put(name, aninationSheet);
    }

    public Image getOrGetDefault(String name){
        if(aninationSheets.containsKey(name))
            return  aninationSheets.get(name);
        return aninationSheets.get("default");

    }
}