package controller;

import core.Position;

public class NPCController implements Controller{

    private boolean up;
    private boolean right;
    private boolean down;
    private boolean left;

    @Override
    public boolean isRequestingUP() {
        return up;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    @Override
    public boolean isRequestingDown() {
        return down;
    }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX()-current.getX();
        double deltaY = target.getY()-current.getY();

        up = deltaY<0;
        right = deltaX>0;
        down = deltaY>0;
        left = deltaX<0;
    }

    public void stop() {
        up = false;
        right = false;
        down = false;
        left = false;
    }
}
