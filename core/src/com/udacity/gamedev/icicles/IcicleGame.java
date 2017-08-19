package com.udacity.gamedev.icicles;

import com.badlogic.gdx.Game;

/**
 * Created by hp on 17-Jul-17.
 */

public class IcicleGame extends Game {

    @Override
    public void create() {
        setScreen(new Difficulty_Screen(this));
    }
}
