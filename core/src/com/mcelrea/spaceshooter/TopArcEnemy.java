package com.mcelrea.spaceshooter;

import com.badlogic.gdx.utils.Array;

public class TopArcEnemy extends Enemy{

    public TopArcEnemy(float x, float y) {
        super(x, y);
    }

    public void act(float delta, Array<Bullet> bullets) {
        x = x + delta*speed; //x decreases with time
        y = (float) (Math.sqrt(Math.pow(350,2)-Math.pow(x-300,2))+200);//equation for movement
        collisionCircle.setX(x);
        collisionCircle.setY(y);
        shoot(bullets);
    }
}
