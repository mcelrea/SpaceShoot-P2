package com.mcelrea.spaceshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by mcelrea on 1/18/2017.
 */
public class GameplayScreen implements Screen {

    private static final float WORLD_WIDTH = 480;
    private static final float WORLD_HEIGHT = 640;
    private SpriteBatch batch; //draw graphics
    private ShapeRenderer shapeRenderer; //draw shapes
    private Camera camera; //the players view of the world
    private Viewport viewport; //control the view of the world
    private PlayerShip player;
    Array<Bullet> playerBullets = new Array<Bullet>();

    public GameplayScreen(MyGdxGame myGdxGame) {
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(); //2D camera
        camera.position.set(WORLD_WIDTH/2,WORLD_HEIGHT/2,0);
        camera.update();
        viewport = new FitViewport(WORLD_WIDTH,WORLD_HEIGHT,camera);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
        batch = new SpriteBatch();
        player = new PlayerShip(400,300,"spaceSpriteSheet.png");
    }

    @Override
    public void render(float delta) {
        clearScreen();
        getUserInput();

        update(delta); //update AI, collision, etc.

        batch.setProjectionMatrix(camera.projection);
        batch.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        batch.begin();
        batch.end();

        shapeRenderer.setProjectionMatrix(camera.projection);
        shapeRenderer.setTransformMatrix(camera.view);
        //all graphics drawing goes here
        shapeRenderer.begin();
        for(int i=0; i < playerBullets.size; i++) {
            playerBullets.get(i).drawDebug(shapeRenderer);
        }
        player.drawDebug(shapeRenderer);
        shapeRenderer.end();
    }

    private void update(float delta) {
        //update bullets
        for(int i=0; i < playerBullets.size; i++) {
            playerBullets.get(i).update(delta);
        }
        removeBulletsOffscreen();
    }

    private void removeBulletsOffscreen() {
        for(int i=0; i < playerBullets.size; i++) {
            Bullet b = playerBullets.get(i);
            //top
            if(b.getY() >= WORLD_HEIGHT) {
                playerBullets.removeIndex(i);//remove bullet
                i--;//there is now one less bullet
            }
            //bottom
            else if(b.getY() + b.getDiameter() <= 0) {
                playerBullets.removeIndex(i);//remove bullet
                i--;//there is now one less bullet
            }
            //left
            else if(b.getX() + b.getDiameter() <= 0) {
                playerBullets.removeIndex(i);//remove bullet
                i--;//there is now one less bullet
            }
            //right
            else if(b.getX() > WORLD_WIDTH) {
                playerBullets.removeIndex(i);//remove bullet
                i--;//there is now one less bullet
            }
        }
    }

    private void getUserInput() {
        //player moving
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player.flyLeft();
            if(player.getX() <= 0) {
                player.setPosition(0,player.getY());
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player.flyRight();
            if(player.getX() + 25 >= WORLD_WIDTH) {
                player.setPosition(WORLD_WIDTH - 25, player.getY());
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            player.flyUp();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            player.flyDown();
        }

        //player shooting
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
            player.shoot(playerBullets);
        }
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}