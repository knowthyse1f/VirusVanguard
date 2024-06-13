package core;

import controller.Controller;

import java.util.Vector;

public class Movement {

    private Vector2D vector;
    private double speed;

    public Movement(double speed) {
        this.speed = speed;
        this.vector= new Vector2D(0,0);
    }

    public void update(Controller controller){

        int deltaX=0;
        int deltaY=0;

        if(controller.isRequestingUP()){
            deltaY--;
        }
        else if(controller.isRequestingDown()){
            deltaY++;
        }
        else if(controller.isRequestingLeft()){
            deltaX--;
        }
        else if(controller.isRequestingRight()){
            deltaX++;
        }

        vector = new Vector2D(deltaX,deltaY);
        vector.normalize();
        vector.multiply(speed);
        System.out.println(vector.length());

    }

    public Vector2D getVector() {
        return vector;
    }
}
