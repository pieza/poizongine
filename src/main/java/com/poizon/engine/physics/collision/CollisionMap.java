package com.poizon.engine.physics.collision;

import com.poizon.engine.graphics.Image;
import com.poizon.engine.objects.GameObject;

public class CollisionMap {
    private boolean[] collision;
    private int levelWidth, levelHeight;
    private Image map;

    public CollisionMap(Image image) {
        this.loadCollisionMap(image);
    }

    protected void loadCollisionMap(Image image) {
        this.map = image;
        this.levelWidth = image.getWidth();
        this.levelHeight = image.getHeight();

        collision = new boolean[levelHeight * levelWidth];

        for (int y = 0; y < levelHeight; y++) {
            for (int x = 0; x < levelWidth; x++) {
                if(image.getPixels()[x + y * levelWidth] != 0) {
                    collision[x + y * levelWidth] = true;
                }
            }
        }
    }

    public void addCollision(Image image, int offX, int offY) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if(image.getPixels()[x + y * image.getWidth()] != 0) {
                    collision[(x + offX) + (y + offY) * levelWidth] = true;
                }
            }
        }
    }

    public boolean getCollision(int x, int y) {
        if(x < 0 || x >= levelWidth || y < 0 || y >= levelHeight)
            return true;
        return collision[x + y * levelWidth];
    }

    public boolean checkUpCollision(GameObject object) {
        return getCollision(object.getPositionX(), object.getPositionY() - 1)
                || getCollision(object.getPositionX() + object.getWidth(), object.getPositionY() - 1);
    }

    public boolean checkDownCollision(GameObject object) {
        return getCollision(object.getPositionX(), object.getPositionY() + object.getHeight() + 1)
                || getCollision(object.getPositionX() + object.getWidth(), object.getPositionY()  + object.getHeight() + 1);
    }

    public boolean checkLeftCollision(GameObject object) {
        return getCollision(object.getPositionX() - 1, object.getPositionY())
                || getCollision(object.getPositionX() -1, object.getPositionY() + object.getHeight());
    }

    public boolean checkRightCollision(GameObject object) {
        return getCollision(object.getPositionX() + object.getWidth() + 1, object.getPositionY())
                || getCollision(object.getPositionX() + object.getWidth() + 1, object.getPositionY() + object.getHeight());
    }
}
