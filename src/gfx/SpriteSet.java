package gfx;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private Map<String , Image> aninationSheets;

    public SpriteSet() {
        this.aninationSheets=new HashMap<>();
    }

    public void addSheet(String name, Image aninationSheet){
        aninationSheets.put(name, aninationSheet);
    }

    public Image get (String name){
        return  aninationSheets.get(name);
    }
}
