package gfx;

import core.Direction;
import game.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimationManager {
    private SpriteSet spriteSet;
    private String currentAnimationName;
    private BufferedImage currentAnimationSheet;
    private int currentFrameTime;
    private int updatePerFrame;
    private int frameIndex;
    private int directionIndex;
    private boolean looping;

    public AnimationManager(SpriteSet spriteSet){
        this(spriteSet,true);
    }
    public AnimationManager(SpriteSet spriteSet, boolean looping){
        this.spriteSet=spriteSet;
        this.updatePerFrame=20;
        this.frameIndex=0;
        this.currentFrameTime=0;
        this.directionIndex=0;
        this.looping=looping;
        currentAnimationName="";
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

            int animationSIze = currentAnimationSheet.getWidth() / Game.SPRITE_SIZE;
            if(frameIndex>= animationSIze){
                frameIndex= looping? 0: animationSIze-1;
            }
        }
    }

    public void playAnimation(String name){
        if(!name.equals(currentAnimationName)){
            this.currentAnimationSheet=(BufferedImage) spriteSet.getOrGetDefault(name);
            currentAnimationName=name;
            frameIndex=0;
        }


    }
}