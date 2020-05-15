package com.poizon.engine.physics;

import com.poizon.engine.physics.collision.CollisionComponent;

import java.util.ArrayList;

public class Physix {
        private static ArrayList<CollisionComponent> collisionComponents = new ArrayList<>();

        public static void addCollisionComponent(CollisionComponent component) {
            collisionComponents.add(component);
        }

        public static void update() {
            for (int i = 0; i < collisionComponents.size(); i++) {
                for (int j = i +1; j < collisionComponents.size(); j++) {
                    CollisionComponent c1 = collisionComponents.get(i);
                    CollisionComponent c2 = collisionComponents.get(j);
                    if(Math.abs(c1.getCenterX() - c2.getCenterX()) < c1.getHalfWidth() + c2.getHalfWidth()) {
                        if(Math.abs(c1.getCenterY() - c2.getCenterY()) < c1.getHalfHeight() + c2.getHalfHeight()) {
                            c1.getParent().onCollisionEvent(c2.getParent());
                            c2.getParent().onCollisionEvent(c1.getParent());
                        }
                    }
                }
            }
            collisionComponents.clear();
        }



}
