package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PlayerShip {
    private float x;
    private float y;
    private Texture image;
    private static final float COLLISION_WIDTH=25f;
    private static final float COLLISION_HEIGHT=25f;
    private Rectangle collisionRect;
    private float shipSpeed = 10f;

    public PlayerShip(float x, float y, Texture image) {
        this.x = x;
        this.y = y;
        this.image = image;
        collisionRect = new Rectangle(x,
                y,
                COLLISION_WIDTH,
                COLLISION_HEIGHT);
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
}
