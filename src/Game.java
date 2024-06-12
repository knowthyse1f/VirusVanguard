import java.awt.*;

public class Game {
    private Display display;
    private Rectangle rectangle;
    public Game(int width , int height){
        display= new Display(width, height);
        rectangle=new Rectangle(0,0,50,50);
    }
    public void update(){

    }
    public void render(){
        display.render(this);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
