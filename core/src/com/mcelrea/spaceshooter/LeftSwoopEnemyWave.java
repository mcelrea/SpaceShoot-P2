package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class LeftSwoopEnemyWave extends Enemy {

    Array<Enemy> wave;
    private static final int NUMOFENEMIES = 10;

    public LeftSwoopEnemyWave(float x, float y) {
        super(x, y);
        wave = new Array<Enemy>();
        for(int i=0; i < NUMOFENEMIES; i++) {
            wave.add(new Enemy(x+(i*50),y));
        }
    }

    @Override
    public void act(float delta) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).act(delta);
        }
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).drawDebug(shapeRenderer);
        }
    }
}
