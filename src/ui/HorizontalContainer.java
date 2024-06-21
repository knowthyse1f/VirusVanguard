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

        for(UIComponents uiComponents : children){
            currentX+=uiComponents.getMargin().getLeft();
            uiComponents.setPostion(new Position(currentX,padding.getTop()));
            currentX+=uiComponents.getSize().getWidth();
            currentX+=uiComponents.getMargin().getRight();
        }

    }
}
