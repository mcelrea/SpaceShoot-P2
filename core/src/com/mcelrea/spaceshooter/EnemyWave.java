package com.mcelrea.spaceshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class EnemyWave {

    protected Array<Enemy> wave;
    protected static int NUMOFENEMIES = 10;
    protected static int GAP = 50;
    public static final int LEFTSWOOP=1,TOPARC=2;
    private int type;
    private Texture enemyImage;

    public EnemyWave(float x, float y, int num, int gap, int type, Texture t) {
        NUMOFENEMIES = num;
        GAP = gap;
        this.type = type;
        enemyImage = t;
        wave = new Array<Enemy>();
        for(int i=0; i < NUMOFENEMIES; i++) {
            if(type == LEFTSWOOP)
                wave.add(new Enemy(x+(i*GAP),y));
            if(type == TOPARC)
                wave.add(new TopArcEnemy(x-(i*GAP),y));
        }
    }

    public void checkWaveForHit(Bullet b) {
        for(int i=0; i < wave.size; i++) {
            Enemy e = wave.get(i);
            if(e.isHit(b)) {
                wave.removeIndex(i);
                i--;
                b.setAlive(false);
                return; //exit
            }
        }
    }

    public void act(float delta, Array<Bullet> bullets) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).act(delta,bullets);
            if(type == LEFTSWOOP && wave.get(i).x < -30) {
                wave.removeIndex(i);
                i--;
            }
            else if(type == TOPARC && wave.get(i).x > 700) {
                wave.removeIndex(i);
                i--;
            }
        }
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        for(int i=0; i < wave.size; i++) {
            wave.get(i).drawDebug(shapeRenderer);
        }
    }

    public void draw(SpriteBatch batch) {
        for(int i=0; i < wave.size; i++) {
            Enemy e = wave.get(i);
            batch.draw(enemyImage,
                    e.getX()-e.getRadius(),
                    e.getY()-e.getRadius());
        }
    }

    public int size() {
        return wave.size;
    }
}
