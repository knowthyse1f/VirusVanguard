package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;
    private int currentFrameTime;
    private int updatePerFrame;
    private int frameIndex;
    private int directionIndex;

    public AnimationManager(SpriteSet spriteSet){
        this.spriteSet=spriteSet;
        this.updatePerFrame=20;
        this.frameIndex=0;
        this.currentFrameTime=0;
        this.directionIndex=0;
        playAnimation("stand");
    }

    public Image getSprite(){
        return currentAnimationSheet.getSubimage(
            frameIndex* Game.SPRITE_SIZE,
                directionIndex* Game.SPRITE_SIZE,
                Game.SPRITE_SIZE,
                Game.SPRITE_SIZE
        );
    }

    public void update(Direction direction){
        currentFrameTime++;
        directionIndex=direction.getAnimationRow();
        if(currentFrameTime>=updatePerFrame){
            currentFrameTime=0;
            frameIndex++;

            if(frameIndex>=currentAnimationSheet.getWidth()/Game.SPRITE_SIZE){
                frameIndex=0;
            }
        }
    }

    public void playAnimation(String name){
        this.currentAnimationSheet=(BufferedImage) spriteSet.get(name);

    }
}