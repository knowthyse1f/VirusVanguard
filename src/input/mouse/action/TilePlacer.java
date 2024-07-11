package input.mouse.action;


import map.Tile;
import state.State;
import ui.UIImage;

public class TilePlacer extends MouseAction{

    private Tile tile;
    private UIImage preview;
    private int gridX;
    private int gridY;

    public TilePlacer(Tile tile) {
        this.tile = tile;
        preview = new UIImage(tile.getSprite());
    }

    @Override
    public void Update(State state) {
         preview.setAbsulutePostion(state.getInput().getMousePosition());
    }

    @Override
    public UIImage getSprite() {
        return preview;
    }
    @Override
    public void onClick(State state) {}

    @Override
    public void onDrag(State state) {

    }

}
