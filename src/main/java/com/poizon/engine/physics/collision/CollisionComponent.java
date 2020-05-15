package com.poizon.engine.physics.collision;

import com.poizon.engine.Game;
import com.poizon.engine.graphics.Color;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.physics.Component;
import com.poizon.engine.physics.Physix;

public class CollisionComponent extends Component {
    private int centerX, centerY, halfWidth, halfHeight;

    public CollisionComponent(GameObject parent) {
        super(parent);
        halfWidth = parent.getWidth() / 2;
        halfHeight = parent.getHeight() / 2;
    }

    @Override
    public void render(Game game) {
        if(game.settings.isDebug()) {
            game.renderer.drawRect(centerX - halfWidth, centerY - halfHeight, halfWidth * 2, halfHeight * 2, Color.BLUE);
        }
    }

    @Override
    public void update(Game game, float deltaTime) {
        centerX = parent.getPositionX() + (parent.getWidth() / 2);
        centerY = parent.getPositionY() + (parent.getHeight() / 2);

        Physix.addCollisionComponent(this);
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getHalfWidth() {
        return halfWidth;
    }

    public void setHalfWidth(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    public int getHalfHeight() {
        return halfHeight;
    }

    public void setHalfHeight(int halfHeight) {
        this.halfHeight = halfHeight;
    }
}
