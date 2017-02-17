package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class EnemyWave extends Enemy {

    Array<Enemy> wave;
    private static final int NUMOFENEMIES = 10;

    public EnemyWave(float x, float y) {
        super(x, y);
        wave = new Array<Enemy>();
        for(int i=0; i < NUMOFENEMIES; i++) {
            wave.add(new Enemy(x+(i*50),y));
        }
    }

    public void checkWaveForHit(Bullet b) {
        for(int i=0; i < wave.size; i++) {
            Enemy e = wave.get(i);
            if(e.isHit(b)) {
                wave.removeIndex(i);
                i--;
            }
        }
    }

    @Override
    public void act(float delta, Array<Bullet> bullets) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).act(delta,bullets);
        }
    }

    @Override
    public void drawDebug(ShapeRenderer shapeRenderer) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).drawDebug(shapeRenderer);
        }
    }
}
