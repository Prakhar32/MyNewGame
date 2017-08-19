package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by hp on 20-Apr-17.
 */

public class Player extends InputAdapter
{
     float player_bodylength[];
    static Vector3 playerheadsize;
    float playerhand[];
    float playerlegs[];
    float  playerparam[];
    float height;
    float width;

    static  float player_position ;
    float player_velocity;

    ShapeRenderer renderer;
    Viewport view;

     Player(Viewport viewport) {
         this.view = viewport;
         player_position = 0;
         Gdx.app.log("hy",""+view.getWorldHeight());
         height = Constants.WORLD_SIZE ;
         width = Constants.WORLD_SIZE + 3 + Math.max(view.getWorldWidth() - Constants.WORLD_SIZE, 0);
    }

    public void playerInit(){
        player_bodylength = new float[4];
        player_bodylength[0] = width/2f + player_position;
        player_bodylength[1] = height/10;
        player_bodylength[2] = width/2f + player_position;
        player_bodylength[3] = height/5;

        playerheadsize = new Vector3(width/2 + player_position, height/4 , (height/4 - height/5));

        playerhand = new float[10];
        playerhand[0] = width/2f + player_position;
        playerhand[1] = height/3/2;
        playerhand[2] = width/1.825f + player_position;
        playerhand[3] = height/3.5f/2;
        playerhand[4] = width/2 + player_position;
        playerhand[5] = height/3/2;
        playerhand[6] = width/2.225f + player_position;
        playerhand[7] = height/3.5f/2;
        playerhand[8] = width/2 + player_position;
        playerhand[9] = height/3/2;

        playerlegs = new float[8];
        playerlegs[0] = width/2 + player_position;
        playerlegs[1] = height/5/2;
        playerlegs[2] = width/1.825f + player_position;
        playerlegs[3] = 0;
        playerlegs[4] = width/2 + player_position;
        playerlegs[5] = height/5/2;
        playerlegs[6] = width/2.225f + player_position;
        playerlegs[7] = 0;

        playerparam = new float[22];
        int counter = 0 ;

        for(int i = 0; i < player_bodylength.length ; i++)
            playerparam[counter++] = player_bodylength[i];

        for(int i = 0; i < playerhand.length ; i++)
            playerparam[counter++] = playerhand[i];

        for(int i = 0; i < playerlegs.length ; i++)
            playerparam[counter++] = playerlegs[i];
    }

    public void update(float delta ) {
        playerInit();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player_position += delta * Constants.CONSTANT_VELOCITY;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player_position -= delta * Constants.CONSTANT_VELOCITY;
        }

        float accelerometerInput = -Gdx.input.getAccelerometerY() / (Constants.ACCELERATION_DUE_TO_GRAVITY * Constants.ACCLEROMETER_SENSITIVITY);
        player_position += - delta * accelerometerInput * Constants.CONSTANT_VELOCITY;

        if (player_position - (width/2 - width/2.25f)<= -width / 2) {
            player_position =  - width / 2.25f + 0.01f;
        }

        if (player_position + (width/2 - width/2.25f)  >= width / 2) {
            player_position =  width  - width / 1.825f -0.01f ;
        }
    }

    public void render(ShapeRenderer render){
        float delta = Gdx.graphics.getDeltaTime();
        update(delta);
        render.set(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.WHITE);
       render.polyline(player_bodylength);
        render.polyline(playerhand);
        render.polyline(playerlegs);
        render.polyline(playerparam);
        render.set(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.WHITE);
        render.circle(playerheadsize.x , playerheadsize.y , playerheadsize.z, 20);
        Gdx.app.log("Test", ""+width);
    }
}
