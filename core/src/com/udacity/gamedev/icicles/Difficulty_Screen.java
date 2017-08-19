package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.glass.events.MouseEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by hp on 20-Apr-17.
 */

public class Difficulty_Screen implements Screen
{
    static float difficulty;
    public final float TEXT_SIZE = 5.8f;
    Texture text;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font;
    Vector2 easy;
    Vector2 medium;
    Vector2 hard;
    public boolean selection_complete = false;
    IcicleGame object;
    int counter = 0;
    ExtendViewport viewport;
    float radius;
    float aspect_ratio;
    ScreenViewport texts;

    Difficulty_Screen(IcicleGame object){
        this.object = object;
    }

    public void init(){
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        texts = new ScreenViewport();
        float width = Gdx.graphics.getWidth();float height = Gdx.graphics.getHeight();
        float width2 = Constants.WORLD_SIZE + 3; float height2 = Constants.WORLD_SIZE;
        Gdx.app.log("ht", "" + height2);
        easy = new Vector2(width2 / 4, height2 / 2);
        medium = new Vector2(width2 / 2, height2 / 2);
        hard = new Vector2(3 * width2 / 4, height2 / 2);
        radius = 1f; aspect_ratio = Constants.WORLD_SIZE / Gdx.graphics.getHeight();
    }

    public void show() {
        init();
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        font = new BitmapFont();
    }

    public void dispose()
    {
        batch.dispose();
        renderer.dispose();
        text.dispose();
    }

    public void render(float x){
        viewport.apply(true);
        texts.apply(true);
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        float height = Gdx.graphics.getHeight();
        float width = Gdx.graphics.getWidth();
        float width2 = viewport.getWorldWidth();
        float height2 = viewport.getWorldHeight();
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);

        batch.setProjectionMatrix(texts.getCamera().combined);
        batch.begin();
        renderer.setColor(Color.GREEN);
        renderer.circle(width2/4 , height2/2 , radius, 20);
        font.setColor(Color.BLUE);
        font.draw(batch , "Easy" , width/4 - 35, height/3);

        renderer.setColor(Color.YELLOW);
        renderer.circle(width2/2 , height2/2, radius, 20);
        font.draw(batch , "Medium" , width/2 - 40 , height/3 );

        renderer.setColor(Color.RED);
        renderer.circle(3 * width2 / 4 , height2 / 2, radius, 20);
        font.draw(batch , "Hard" , 3 * width/4 - 35, height/3);

        batch.end();
        renderer.end();
        counter++;
        update(Gdx.graphics.getDeltaTime());
    }

    public void update(float delta) {
        if (checkifclicked(easy)) {
            difficulty = Constants.EASY;selection_complete = true;
        }
        if (checkifclicked(medium)) {
            difficulty = Constants.NORMAL;selection_complete = true;
        }
        if(checkifclicked(hard)) {
            difficulty = Constants.HARD;selection_complete = true;
        }

        if(selection_complete)
            object.setScreen(new IciclesScreen(difficulty, object));
    }

    public boolean checkifclicked(Vector2 selection){
        if(Gdx.input.isTouched()){
            if(Math.pow((Gdx.input.getX() * (Constants.WORLD_SIZE + 3) / Gdx.graphics.getWidth() - selection.x), 2) + Math.pow((Gdx.input.getY() * aspect_ratio - selection.y), 2) < radius * radius)
                return  true;
        }
        return  false;
    }

    public void hide()
    {

    }

    public void pause(){

    }

    public void resume(){

    }

    public void resize(int x , int y){
        viewport.update(x, y, true);
        texts.update(x, y ,true);
        font.getData().setScale(2);
    }
}
