package gfx;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SpriteLibrary {
    private static String Path_To_Units="/sprits/units/";
    private Map<String , SpriteSet> units;

    public SpriteLibrary() {
        units=new HashMap<>();
        loadSpritesFromDisk();
    }

    private void loadSpritesFromDisk() {
        String[] foldernames= getFolderName(Path_To_Units);

    }

    private String[] getFolderName(String basePath) {
        URL resource = SpriteLibrary.class.getResource(basePath);
       File file = new File(resource.getFile());
       return file.list((current,name)->new File(current, name).isDirectory());
    }
}
