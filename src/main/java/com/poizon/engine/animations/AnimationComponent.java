package com.poizon.engine.animations;

import com.poizon.engine.Game;
import com.poizon.engine.objects.Component;
import com.poizon.engine.objects.GameObject;
import com.poizon.engine.utils.log.LogLevel;

/**
 * Callback that will handle the animation frames.
 */
interface Callback {
    void call(float animationFrame);
}

/**
 * Animations is a set of frames where each frame contains a different action and happens in the same time line
 * as the gam loop, but slower or faster that the actual real time.
 *
 * @author Jose Ulloa
 */
public class AnimationComponent extends Component {

    /**
     * Function where user can handle actions in the animation
     */
    private Callback callback;

    /**
     * Number of frames of the animation.
     */
    private int totalFrames;

    /**
     * Actual frame of the animation.
     */
    private float animationFrame;

    /**
     * Speed on the animation based on delta time.
     */
    private float speed;

    public AnimationComponent(GameObject parent, int totalFrames, float speed, Callback callback) {
        super(parent);
        this.callback = callback;
        this.totalFrames = totalFrames;
        this.animationFrame = 0;
        this.speed = speed;
    }

    @Override
    public void update(Game game, float deltaTime) {
        animationFrame += deltaTime * speed;
        if(animationFrame > totalFrames) {
            animationFrame = 0;
        }
        try {
            if(callback != null) callback.call(animationFrame);
        } catch (Exception ex) {
            game.logger.log(LogLevel.ERROR, ex.getMessage());
        }
    }

    @Override
    public void render(Game game) {
        // no need to render
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public float getAnimationFrame() {
        return animationFrame;
    }

    public void setAnimationFrame(float animationFrame) {
        this.animationFrame = animationFrame;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
