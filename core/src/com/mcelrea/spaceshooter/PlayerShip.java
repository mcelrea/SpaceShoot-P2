package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PlayerShip {
    private float x;
    private float y;
    private Texture image;
    private static final float COLLISION_WIDTH=25f;
    private static final float COLLISION_HEIGHT=25f;
    private Rectangle collisionRect;
    private float shipSpeed = 5f;
    private static final int SINGLERAIL=1, DOUBLERAIL=2;
    private int currentWeapon = DOUBLERAIL;

    public PlayerShip(float x, float y, Texture image) {
        this.x = x;
        this.y = y;
        this.image = image;
        collisionRect = new Rectangle(x,
                y,
                COLLISION_WIDTH,
                COLLISION_HEIGHT);
    }

    //shoot
    public void shoot(Array<Bullet> bullets) {
        if(currentWeapon == SINGLERAIL) {
            Bullet b = new Bullet(x + COLLISION_WIDTH / 2,
                    y + COLLISION_HEIGHT,
                    0,
                    500);
            bullets.add(b);
        }
        else if(currentWeapon == DOUBLERAIL) {
            Bullet b = new Bullet(x,
                    y + COLLISION_HEIGHT,
                    0,
                    500);
            bullets.add(b);
            b = new Bullet(x + COLLISION_WIDTH,
                    y + COLLISION_HEIGHT,
                    0,
                    500);
            bullets.add(b);
        }
    }

    public void flyLeft() {
        setPosition(x - shipSpeed, y);
    }

    public void flyRight() {
        setPosition(x + shipSpeed, y);
    }

    public void flyUp() {
        setPosition(x, y + shipSpeed);
    }

    public void flyDown() {
        setPosition(x, y - shipSpeed);
    }

    private void updateCollisionRect() {
        collisionRect.setX(x);
        collisionRect.setY(y);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateCollisionRect();
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(collisionRect.x,
                collisionRect.y,
                collisionRect.width,
                collisionRect.height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
