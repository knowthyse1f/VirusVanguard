package ui;

import core.Position;
import core.Size;

public class HorizontalContainer extends UIContainer{
    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWidth =0;
        int tallestChildHeight =0;
        for(UIComponents uiComponents:children){
            combinedChildWidth+= uiComponents.getSize().getWidth()+uiComponents.getMargin().getHorizontal();
            if(uiComponents.getSize().getHeight() > tallestChildHeight){
                tallestChildHeight= uiComponents.getSize().getHeight();
            }
        }
        return new Size(combinedChildWidth, tallestChildHeight);
    }

    @Override
    protected void calculateContentPosition() {
        int currentX = padding.getLeft();
        int currentY = padding.getTop();

        for(UIComponents uiComponents : children){

            if(centerChildren){
                currentY = getSize().getHeight()/2 - uiComponents.getSize().getHeight()/2;
            }
            currentX+=uiComponents.getMargin().getLeft();

            uiComponents.setRealtivePostion(new Position(currentX,currentY));
            uiComponents.setAbsulutePostion(new Position(currentX+absulutePostion.intX(), currentY +absulutePostion.intY()));
            currentX+=uiComponents.getSize().getWidth();
            currentX+=uiComponents.getMargin().getRight();
        }

    }
}
