package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;

import java.awt.*;

public abstract class GameObject {
    protected Position position;
    protected Position renderOffset;
    protected Position collisionBoxOffset;
    protected Size size;

    protected int renderOrder;

    protected GameObject parent;

    public GameObject() {
        position=new Position(0,0);
        renderOffset= new Position(0,0);
        size= new Size(64,64);
        collisionBoxOffset = new Position(0,0);
        renderOrder=5;

    }

    public abstract void update(State state);
    public abstract Image getSprite();
    public abstract CollisionBox getCollisionBox();

    public  boolean collidesWith(GameObject other){
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    public Position getPosition() {
        Position finalposition = Position.CopyOf(position);
        if(parent!=null){
            finalposition.add(parent.getPosition());
        }
        return finalposition;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Size getSize() {
        return size;
    }

    public void parent(GameObject parent) {
        this.parent = parent;
        this.position=new Position(0,0);
    }

    public Position getRenderPosition(Camera camera) {
        return new Position(
                getPosition().getX()-camera.getPosition().getX()-renderOffset.getX(),
                getPosition().getY()-camera.getPosition().getY()-renderOffset.getY()
        );
    }

    public int getRenderOrder() {
        return renderOrder;
    }

    protected void clearParent() {
        parent = null;
    }

    protected Position getRenderOffset() {
        return renderOffset;
    }

    public void setRenderOrder(int renderOrder) {
        this.renderOrder=renderOrder;
    }
}
