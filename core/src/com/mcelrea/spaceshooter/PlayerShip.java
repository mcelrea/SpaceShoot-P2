package com.mcelrea.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class PlayerShip {
    private float x;
    private float y;
    private Texture image;
    private static final float COLLISION_WIDTH=20f;
    private static final float COLLISION_HEIGHT=30f;
    private Rectangle collisionRect;
    private float shipSpeed = 5f;
    private static final int SINGLERAIL=1, DOUBLERAIL=2;
    private int currentWeapon = SINGLERAIL;
    private long shootDelay = 300; //1000 = 1 second
    private long lastShot;
    TextureRegion leftRegion;
    TextureRegion rightRegion;
    TextureRegion middleRegion;
    public static final int LEFT=1,MIDDLE=2,RIGHT=3;
    private int dir = MIDDLE;
    ParticleEffect engine1;
    ParticleEffect engine2;

    public PlayerShip(float x, float y, String path) {
        this.x = x;
        this.y = y;
        this.image = image;
        collisionRect = new Rectangle(x,
                y,
                COLLISION_WIDTH,
                COLLISION_HEIGHT);
        lastShot = System.currentTimeMillis(); //lastShot = currentTime
        Texture t = new Texture(path);
        TextureRegion[] regions = TextureRegion.split(t,39,37)[0];
        leftRegion = regions[0];
        middleRegion = regions[1];
        rightRegion = regions[2];
        engine1 = new ParticleEffect();
        engine1.load(Gdx.files.internal("test.effect"), Gdx.files.internal(""));
        engine1.getEmitters().first().setPosition(x,y);
        engine1.start();

        engine2 = new ParticleEffect();
        engine2.load(Gdx.files.internal("test.effect"), Gdx.files.internal(""));
        engine2.getEmitters().first().setPosition(x,y);
        engine2.start();
    }

    //shoot
    public void shoot(Array<Bullet> bullets) {
        if(lastShot + shootDelay <= System.currentTimeMillis()) {
            lastShot = System.currentTimeMillis();
            if (currentWeapon == SINGLERAIL) {
                Bullet b = new Bullet(x + COLLISION_WIDTH / 2,
                        y + COLLISION_HEIGHT,
                        0,
                        500);
                bullets.add(b);
            } else if (currentWeapon == DOUBLERAIL) {
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

    public void draw(SpriteBatch batch) {

        engine1.draw(batch);
        engine2.draw(batch);

        if(dir == MIDDLE) {
            batch.draw(middleRegion, x - COLLISION_WIDTH / 2, y - 3);
        }
        else if(dir == LEFT) {
            batch.draw(leftRegion, x - COLLISION_WIDTH / 2, y - 3);
        }
        else if(dir == RIGHT) {
            batch.draw(rightRegion, x - COLLISION_WIDTH / 2, y - 3);
        }
    }

    public void update(float delta) {
        engine1.update(delta);
        engine2.update(delta);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;

        if(dir == MIDDLE) {
            engine1.setPosition(x - 2, y + 3);
            engine2.setPosition(x + COLLISION_WIDTH, y + 3);
        }
        else if(dir == LEFT) {
            engine1.setPosition(x + 2, y + 3);
            engine2.setPosition(x + COLLISION_WIDTH-7, y + 3);
        }
        else if(dir == RIGHT) {
            engine1.setPosition(x + 3, y + 3);
            engine2.setPosition(x + COLLISION_WIDTH-4, y + 3);
        }
    }
}
