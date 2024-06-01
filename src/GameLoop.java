public class GameLoop implements  Runnable{

    private boolean running;
    private final double updateRate=1.0d/60.0d;

    private long nextStartTime;
    private int fps,ups;

    @Override
    public void run() {
        running=true;
        double accumulator =0;
        long currentTime, lastUpdate= System.currentTimeMillis();
        nextStartTime=System.currentTimeMillis()+1000;


        while(running){
            currentTime=System.currentTimeMillis();
            double lastRenderTimeInSecond=(currentTime-lastUpdate);
            accumulator+=lastRenderTimeInSecond;
            lastUpdate=currentTime;

            while(accumulator>updateRate){
                update();
                accumulator-=updateRate;
            }
                render();
            printStates();
        }
    }

    private void printStates() {
        if(System.currentTimeMillis()>nextStartTime){
            System.out.println(String.format("FPS : %d , UPS: %d",fps,ups));
            fps=0;
            ups=0;
            nextStartTime=System.currentTimeMillis()+1000;

        }
    }

    private void render() {
        fps++;
    }

    private void update() {
        ups++;
    }
}
