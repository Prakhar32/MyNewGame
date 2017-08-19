package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.security.Key;
import java.util.ArrayList;

import sun.rmi.runtime.Log;

/**
 * Created by hp on 20-Apr-17.
 */


public class Icicles
{
    private  int high_score = 0;
    private int icicle_dodged = 0;
    private  int death_count = 0;
    DelayedRemovalArray<Icicle> number ;
    float counter = 0;
    ShapeRenderer renderer;
    Viewport viewport;
    float difficulty;
    boolean collision = false;
    IcicleGame object;

    Icicles(Viewport viewport, float difficulty, IcicleGame icicleGame){
        this.viewport = viewport;
        number = new DelayedRemovalArray<Icicle>(false, 100);
        this.difficulty = difficulty;
        object = icicleGame;
    }

   public void render(ShapeRenderer render){
       if(Gdx.input.isKeyPressed(Input.Keys.R))
           object.setScreen(new Difficulty_Screen(object));

       float delta = Gdx.graphics.getDeltaTime();
       render.set(ShapeRenderer.ShapeType.Filled);
       render.setColor(Color.WHITE);

      for(Icicle icicle: number){
          icicle.render(render, Gdx.graphics.getDeltaTime());
           has_collided(Player.playerheadsize, icicle);
          if(collision){
              number.clear();icicle_dodged = 0;return;
          }
       }
       update(delta);
   }

   public void update(float delta){
       if (MathUtils.random() < delta * difficulty) {
           // TODO: Add a new icicle at the top of the viewport at a random x position
           Icicle newIcicle = new Icicle(viewport);
           number.add(newIcicle);
       }

       number.begin();
       for(int i = 0; i < number.size; i++){
           if(number.get(i).vertex_3.y < 0)
           {
               icicle_dodged++;
               number.removeIndex(i);
           }
       }
       number.end();
   }

   public void has_collided(Vector3 playerhead, Icicle icicle){
       float x = playerhead.x;float y = playerhead.y;float radius = playerhead.z;
       if(Math.pow((icicle.vertex_3.x - x), 2) + Math.pow((icicle.vertex_3.y - y), 2) < radius * radius){
           collision = true;death_count++;
       }
   }

   public int High_score(){
       if(icicle_dodged > high_score)
           high_score = icicle_dodged;
       return  high_score;
   }

   public int getHits(){
       return death_count;
   }
   public int getCurrentScore(){
       return  icicle_dodged;
   }

}
