import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Display extends JFrame {

   private  Canvas canvas;

   public Display( int width , int height, Input input ){
       setTitle("Virus Vanguard");
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);

       canvas  = new Canvas();
       canvas.setPreferredSize(new Dimension(width, height));
       canvas.setFocusable(false);
       add(canvas);
       addKeyListener(input);
       pack();

       canvas.createBufferStrategy(3);
       setLocationRelativeTo(null);
       setVisible(true);


   }
   public void render (Game game){
       BufferStrategy bufferStrategy =canvas.getBufferStrategy();
       Graphics graphics=bufferStrategy.getDrawGraphics();

       //clear screen
       graphics.setColor(Color.BLACK);
       graphics.fillRect(0,0, canvas.getWidth(), canvas.getHeight());

        game.getGameObjects().forEach(gameObject -> graphics.drawImage(
                gameObject.getSprite(),
                gameObject.getPosition().getX(),
                gameObject.getPosition().getY(),
                null

        ));

       graphics.dispose();
       bufferStrategy.show();
   }

}
