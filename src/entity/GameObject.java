package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;

import java.awt.*;

public abstract class GameObject {
    protected Position position;
    protected Size size;

    protected GameObject parent;

    public GameObject() {
        position=new Position(50,50);
        size= new Size(50,50);

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

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
