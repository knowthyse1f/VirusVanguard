package ui.clickable;

import core.Size;
import game.setting.Setting;
import gfx.ImageUtils;
import state.State;
import ui.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UICheckbox extends UIComponents {

    //Making UICheckBox
    private UIContainer container;

    public UICheckbox(String label, Setting<Boolean>setting) {
        this.container = new HorizontalContainer(new Size(0,0));
        container.addUIComponent(new Checkbox(setting));
        container.addUIComponent(new UIText(label));

    }

    @Override
    public Image getSprite() {
        return container.getSprite();
    }

    @Override
    public void Update(State state) {
        container.Update(state);
        size = container.getSize();
        container.setParent(parent);
        container.setAbsulutePostion(absulutePostion);
    }

    private static class Checkbox extends UIClickable{

        private Setting<Boolean> setting;
        private Color color;

        private Checkbox(Setting<Boolean> setting) {
            this.setting = setting;
            size = new Size(20,20);
            color = Color.GRAY;
            margin = new Spacing(0,0,0,5);
        }

        @Override
        public void Update(State state){
            super.Update(state);
            color = setting.getValue() ? Color.WHITE : Color.GRAY;

            if(hasFocus){
                color = Color.LIGHT_GRAY;

                if(isPressed){
                    color = Color.DARK_GRAY;
                }
            }
        }

        @Override
        protected void onFocus(State state) {}

        @Override
        protected void onDrag(State state) {}

        @Override
        protected void onClick(State state) {

            setting.setValue(!setting.getValue());

        }

        @Override
        public Image getSprite() {
            BufferedImage sprite = (BufferedImage) ImageUtils.createCompatbleImage(size, ImageUtils.ALPHA_BIT_MASKED);
            Graphics2D graphics = sprite.createGraphics();

            graphics.setColor(color);
            if(setting.getValue()){
                graphics.fillRect(0,0, size.getWidth(), size.getHeight());
            }else{
                graphics.drawRect(0,0, size.getWidth()-1, size.getHeight()-1);
            }
            graphics.dispose();
            return sprite;
        }
    }
}
