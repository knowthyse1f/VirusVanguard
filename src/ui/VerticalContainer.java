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

        for(UIComponents uiComponents : children){
            currentY+=uiComponents.getMargin().getTop();
            uiComponents.setRealtivePostion(new Position(padding.getLeft(),currentY));
            uiComponents.setAbsulutePostion(new Position(padding.getLeft()+ absulutePostion.intX(),currentY+ absulutePostion.intY()));
            currentY+=uiComponents.getSize().getHeight();
            currentY+=uiComponents.getMargin().getBottom();
        }

    }
}
