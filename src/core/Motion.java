package core;

import controller.EntityController;

public class Motion {

    private Vector2D vector;
    private double speed;

    public Motion(double speed) {
        this.speed = speed;
        this.vector= new Vector2D(0,0);
    }

    public void update(EntityController entityController){

        int deltaX=0;
        int deltaY=0;

        if(entityController.isRequestingUP()){
            deltaY--;
        }
        else if(entityController.isRequestingDown()){
            deltaY++;
        }
        else if(entityController.isRequestingLeft()){
            deltaX--;
        }
        else if(entityController.isRequestingRight()){
            deltaX++;
        }

        vector = new Vector2D(deltaX,deltaY);
        vector.normalize();
        vector.multiply(speed);


    }

    public Vector2D getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length()>0;
    }

    public void multiply(double multiplier) {
    vector.multiply(multiplier);
    }

    public void stop(boolean stopX,boolean stopY) {
        vector= new Vector2D(
                stopX? 0: vector.getX(),
                stopY? 0: vector.getY());
    }

    public Vector2D getDirection() {
        Vector2D direction = Vector2D.copyOf(vector);
        direction.normalize();
        return direction;
    }

    public void add(Vector2D vector) {
        this.vector.add(vector);
    }
}
