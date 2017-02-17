package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

public class Enemy {
    protected float x;
    protected float y;
    private static final float COLLISION_RADIUS=10f;
    private Circle collisionCircle;
    private float speed = 100;
    protected long timer=900;//1000 = 1 sec
    protected long lastShotTimeStamp;

    public Enemy(float x, float y) {
        this.x = x;
        this.y = y;
        collisionCircle = new Circle(x,y,COLLISION_RADIUS);
        lastShotTimeStamp = System.currentTimeMillis();
    }

    public boolean isHit(Bullet b) {
        if(Intersector.overlaps(collisionCircle,b.getCollisionCircle())) {
            return true;
        }
        else {
            return false;
        }
    }

    public void act(float delta, Array<Bullet> bullets) {
        x = x - delta*speed; //x decreases with time
        y = (float) (Math.pow(x,2)/300);//equation for movement
        collisionCircle.setX(x);
        collisionCircle.setY(y);
        shoot(bullets);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.circle(collisionCircle.x,
                collisionCircle.y,
                collisionCircle.radius);
    }

    public void shoot(Array<Bullet> bullets) {
        if(lastShotTimeStamp + timer < System.currentTimeMillis()) {
            Bullet b = new Bullet(x,y,0,-500);
            bullets.add(b);
            lastShotTimeStamp = System.currentTimeMillis();
        }
    }
}
