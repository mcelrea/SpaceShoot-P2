package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Bullet {

    private static final float COLLISION_RADIUS = 5f;
    private Circle collisionCircle;
    private float x;
    private float y;
    private float xVel; //x velocity
    private float yVel; //y velocity

    public Bullet(float x, float y, float xVel, float yVel) {
        collisionCircle = new Circle(x,
                y,
                COLLISION_RADIUS);
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void update(float delta) {
        x = x + xVel * delta; //move image
        y = y + yVel * delta; //move image
        collisionCircle.setX(x); //move collision circle
        collisionCircle.setY(y); //move collision circle
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }
}