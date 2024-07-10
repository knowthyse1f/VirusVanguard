package ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer{
    public VerticalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight =0;
        int widestChildWidth =0;
        for(UIComponents uiComponents:children){
            combinedChildHeight+= uiComponents.getSize().getHeight()+uiComponents.getMargin().getVertical();
            if(uiComponents.getSize().getWidth() > widestChildWidth){
                widestChildWidth= uiComponents.getSize().getWidth();
            }
        }
        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentY = padding.getTop();
        int currentX = padding.getLeft();

        for(UIComponents uiComponents : children){

            if(centerChildren){
                currentX = getSize().getWidth() / 2 - uiComponents.getSize().getWidth() / 2;
            }
            currentY+=uiComponents.getMargin().getTop();
            uiComponents.setRealtivePostion(new Position(currentX,currentY));
            uiComponents.setAbsulutePostion(new Position(currentX + absulutePostion.intX(),currentY+ absulutePostion.intY()));
            currentY+=uiComponents.getSize().getHeight();
            currentY+=uiComponents.getMargin().getBottom();
        }

    }
}
