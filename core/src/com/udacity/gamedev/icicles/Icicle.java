package com.udacity.gamedev.icicles;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by hp on 20-Apr-17.
 */

public class Icicle
{
    Vector2 vertex_1 , vertex_2 , vertex_3 ;
    float acceleration;
    float velocity;
    Viewport viewport;

    Icicle(Viewport viewpor)
    {
        this.viewport = viewpor;
        vertex_1 = new Vector2();
        vertex_2 = new Vector2();
        vertex_3 = new Vector2();
        float initlocationa = (float)(Math.random() * (viewport.getWorldWidth() - Constants.ICICLES_WIDTH - 1));
        float height = viewport.getWorldHeight();
        vertex_1.x = initlocationa;
        vertex_1.y = height;
        vertex_2.x = initlocationa + Constants.ICICLES_WIDTH;
        vertex_2.y = height;
        vertex_3.x = initlocationa + Constants.ICICLES_WIDTH/2;
        vertex_3.y = height - Constants.ICICLES_HEIGHT;
        velocity = Constants.ICICLE_CONSTT_VELOCITY;
    }

    public void update(float delta)
    {
        this.vertex_3.y -= delta * velocity;
        this.vertex_1.y -= delta * velocity;
        this.vertex_2.y -= delta * velocity;
    }

    public void render(ShapeRenderer render , float delta){
        render.set(ShapeRenderer.ShapeType.Filled);
        render.setColor(Color.BLACK);
        render.triangle(vertex_1.x , vertex_1.y , vertex_2.x , vertex_2.y , vertex_3.x , vertex_3.y);
        update(delta);
    }
}
