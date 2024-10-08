package game;

public class GameLoop implements  Runnable{

    public  static final int UPDATES_PER_SECOND=60;
    private Game game;

    private boolean running;
    private final double updateRate=1.0d/UPDATES_PER_SECOND;
    private final long updateRateInNanos=(long)(updateRate*1e9);

    private long nextStartTime;
    private int fps,ups;

    public GameLoop(Game game){
        this.game=game;
    }
    @Override
    public void run() {
        running=true;
        double accumulator =0;
        long currentTime, lastUpdate= System.nanoTime();
        nextStartTime=System.currentTimeMillis()+1000;


        while(running){
            currentTime=System.nanoTime();
            long elapsedTime= currentTime-lastUpdate;
            accumulator+=elapsedTime * game.getSettings().getGameSpeedMultiplier();
            lastUpdate=currentTime;

            if(accumulator>=updateRateInNanos) {
                while (accumulator >= updateRateInNanos) {
                    update();
                    accumulator -= updateRateInNanos;
                }

                render();
            }
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

    private void update() {
        game.update();
        ups++;
    }
    private void render() {
        game.render();
        fps++;
    }


}
