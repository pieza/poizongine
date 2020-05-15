package com.poizon.engine.scene;

import com.poizon.engine.Game;
import com.poizon.engine.camera.Camera;
import com.poizon.engine.graphics.Image;
import com.poizon.engine.objects.Box;
import com.poizon.engine.objects.Mob;
import com.poizon.engine.objects.Player;
import com.poizon.engine.physics.collision.CollisionMap;
import com.poizon.engine.scenes.GameScene;

public class TestCollisionScene extends GameScene {
    private Image background;
    private Image map;

    public TestCollisionScene() {
        background = new Image("/sprites/bg_c.png");
        map = new Image("/sprites/map_c.png");
    }

    @Override
    public void init(Game game) {
        Player player = new Player();
        player.setPositionX(50);
        player.setPositionY(30);
        objects.put("PLAYER", player);
        Box box = new Box(200, 200);
        Mob mob = new Mob(300, 200);
        objects.put("BOX", box);
        objects.put("MOB", mob);
        game.collisionMap = new CollisionMap(map);
        game.collisionMap.addCollision(box.sprite, box.getPositionX(), box.getPositionY());
        game.camera = new Camera("PLAYER");
    }

    @Override
    public void update(Game game, float deltaTime) {

    }

    @Override
    public void render(Game game) {
        game.renderer.drawImage(background, 0, 0);
        game.renderer.drawImage(map, 0, 0);
    }

}