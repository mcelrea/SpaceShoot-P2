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
    private boolean alive = true;

    public Bullet(float x, float y, float xVel, float yVel) {
        collisionCircle = new Circle(x,
                y,
                COLLISION_RADIUS);
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
    }

    //update bullet
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

    public float getDiameter() {
        return COLLISION_RADIUS*2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Circle getCollisionCircle() {
        return collisionCircle;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
