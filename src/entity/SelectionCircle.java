package entity;

import core.CollisionBox;
import core.Position;
import core.Size;
import state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject{

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = new Color(0,255,255);
        size = new Size(40,30);
        renderOffset = new Position(size.getWidth()/2, size.getHeight());
        collisionBoxOffset= renderOffset;
        renderOrder=4;
        initializeSprite();
    }

    private void initializeSprite() {
        sprite = (BufferedImage) ImageUtils.createCompatbleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D graphics = sprite.createGraphics();
        graphics.setColor(color);
        graphics.fillOval(0,0,size.getWidth(), size.getHeight());

        graphics.dispose();
    }

    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position position = getPosition();
        position.subtract(collisionBoxOffset);
        return CollisionBox.of(position, getSize());
    }

}
