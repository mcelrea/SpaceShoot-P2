package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Enemy {
    protected float x;
    protected float y;
    private static final float COLLISION_RADIUS=10f;
    private Circle collisionCircle;
    private float speed = 100;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
    }

    public void act(float delta) {
        x = x - delta*speed; //x decreases with time
        y = (float) (Math.pow(x,2)/300);//equation for movement
        collisionCircle.setX(x);
        collisionCircle.setY(y);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }
}
