package com.poizon.engine.camera;

import com.poizon.engine.Game;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.render.IRenderable;
import com.poizon.engine.render.IUpdateable;

public class Camera implements IUpdateable, IRenderable {
    private int offX, offY;
    private int speed = 5;
    private String targetKey;
    private GameObject target;

    public Camera() {

    }

    public Camera(String targetKey) {
        this.targetKey = targetKey;
    }

    @Override
    public void update(Game game, float deltaTime) {
        if(target == null) {
            target = game.getObject(targetKey);
        }

        if(target == null) {
            return;
        }

        int targetX = (target.getPositionX() + target.getWidth() / 2) - game.settings.getScreenWidth() / 2;
        int targetY = (target.getPositionY() + target.getHeight() / 2) - game.settings.getScreenHeight() / 2;

        //offX = targetX;
        //offY = targetY;
        offX -= deltaTime * (offX - targetX) * speed;
        offY -= deltaTime * (offY - targetY) * speed;

        if(offX < 0) offX = 0;
        if(offY < 0) offY = 0;
    }

    @Override
    public void render(Game game) {
        game.renderer.setCameraX(offX);
        game.renderer.setCameraY(offY);
    }

    public int getOffX() {
        return offX;
    }

    public void setOffX(int offX) {
        this.offX = offX;
    }

    public int getOffY() {
        return offY;
    }

    public void setOffY(int offY) {
        this.offY = offY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getTargetKey() {
        return targetKey;
    }

    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }
}
