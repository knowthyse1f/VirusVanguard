package game.ui;

import core.Size;
import entity.MovingEntity;
import entity.humanoid.Humanoid;
import entity.humanoid.effect.Sick;
import game.state.State;
import ui.*;

public class UISicknaceStatistics extends HorizontalContainer {
    private UIText numberOfSick;
    private UIText numberOfHealthy;


    public UISicknaceStatistics(Size windowSize) {
        super(windowSize);
        this.numberOfSick= new UIText(" ");
        this.numberOfHealthy= new UIText(" ");

        UIContainer sickContainer = new VerticalContainer(windowSize);
        sickContainer.setMargin(new Spacing( 0));
        sickContainer.addUIComponent(new UIText("SICK : "));
        sickContainer.addUIComponent(numberOfSick);

        UIContainer healthyContainer = new VerticalContainer(windowSize);
        healthyContainer.setMargin(new Spacing( 0));
        healthyContainer.addUIComponent(new UIText("HEALTHY : "));
        healthyContainer.addUIComponent(numberOfHealthy);

        addUIComponent(healthyContainer);
        addUIComponent(sickContainer);
    }

    @Override
    public  void Update(State state){
        super.Update(state);
        long sickCount= state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffected(Sick.class))
                .count();

        long healthyCount= state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffected(Sick.class))
                .count();
        numberOfSick.setText(String.valueOf(sickCount));
        numberOfHealthy.setText(String.valueOf(healthyCount));
    }
}
