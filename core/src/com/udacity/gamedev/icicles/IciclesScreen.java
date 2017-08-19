package com.udacity.gamedev.icicles;

import  com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
//import com.sun.prism.image.ViewPort;
import java.awt.RenderingHints;

import javax.security.auth.login.LoginException;



public class IciclesScreen implements Screen {

	ShapeRenderer renderer;
	Player player;
	Icicles ice;
	ExtendViewport icicleview;
	Batch batch;
	Texture img;
	BitmapFont font;
	float icicles_drop_rate;
	IcicleGame object;
	ScreenViewport HUDViewport;

	IciclesScreen(float difficulty, IcicleGame object){
		icicles_drop_rate = difficulty;
		this.object = object;
	}

	@Override
	public void show () {
		renderer = new ShapeRenderer();
		renderer.setAutoShapeType(true);
		icicleview = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		font = new BitmapFont();
		HUDViewport = new ScreenViewport();
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		player = new Player(icicleview);
		ice = new Icicles(icicleview, icicles_drop_rate, object);
	}

	public void resize(int width, int height){
		icicleview.update(width, height, true);
		HUDViewport.update(width, height, true);
		font.getData().setScale(1.5f);
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

	public void render (float delta) {
		icicleview.apply(true);
		HUDViewport.apply(true);
		Gdx.gl.glClearColor(0, 0, 200, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		renderer.setProjectionMatrix(icicleview.getCamera().combined);
		batch.setProjectionMatrix(HUDViewport.getCamera().combined);

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		player.render(renderer);
	    ice.render(renderer);
		if(ice.collision){
			player.player_position = 0;ice.collision = false;
		}
		renderer.end();

		batch.begin();
		font.draw(batch, "High Score "+ice.High_score(),0, icicleview.getScreenHeight() - 1);
		font.draw(batch, "Current Score" + ice.getCurrentScore(), 0, icicleview.getScreenHeight() - 20f);
		font.draw(batch, "Deaths" + ice.getHits(), icicleview.getScreenWidth() - 100, icicleview.getScreenHeight() - 1);
		batch.end();
	}

	@Override
	public void dispose () {
		renderer.dispose();
	}

}
