package input;

import core.Position;

import java.awt.event.*;

public class Input implements KeyListener , MouseListener, MouseMotionListener {

    private boolean mouseClicked;
    private boolean mousePressed;

    private boolean[] currentlyPressed;
    private boolean[] pressed;
    private Position mousePosition = new Position(0,0);

    public Input(){
        pressed = new boolean[255];
        currentlyPressed = new boolean[255] ;
    }

    public boolean isPressed(int keyCode){
        if(!pressed[keyCode] && currentlyPressed[keyCode]){
            pressed[keyCode]=true;
            return true;
        }
        return false;
    }
    public boolean isCurrentlyPressed(int keyCode){
        return currentlyPressed[keyCode];
    }


    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public Position getMousePosition() {
        return mousePosition;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    currentlyPressed[e.getKeyCode()]=true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    currentlyPressed[e.getKeyCode()]=false;
    pressed[e.getKeyCode()]=false;
    }


    public boolean isCurrrentlyPressed(int keyCode) {
        return currentlyPressed[keyCode];
    }

        public void clearMouseClicke(){
        mouseClicked=false;
        }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressed=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked=true;
        mousePressed=false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePosition= new Position(e.getPoint().getX(),e.getPoint().getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition= new Position(e.getPoint().getX(),e.getPoint().getY());
    }
}
