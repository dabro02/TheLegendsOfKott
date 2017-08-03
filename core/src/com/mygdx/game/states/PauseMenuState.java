package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Daniel on 03.08.2017.
 */
public class PauseMenuState extends State {

    Button testbutton;
    Sound kottsound;

    public PauseMenuState(GameStateManager gsm) {
        super(gsm);

        cam.position.set(new Vector3(1920,1080,0)) ;
        cam.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        testbutton = new Button("Dies ist toll", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2, 200,70);
        kottsound = Gdx.audio.newSound(Gdx.files.internal("Kott-Sound.mp3"));
    }

    @Override
    protected void handleInput() {

        //Exit fullscreen
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if(!Gdx.graphics.isFullscreen()){
                Gdx.graphics.setDisplayMode(super.Width,super.Height,!Gdx.graphics.isFullscreen());
            }
            else{
                Gdx.graphics.setDisplayMode(super.windowWidth,super.windowHeight,!Gdx.graphics.isFullscreen());
                cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            }
        }
        //Exit PauseMenu
        if(Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)){
            gsm.pop();
        }

        cam.update();
    }

    @Override
    public void update(float dt) {
        handleInput();

        if(testbutton.isClicked()){
            kottsound.play(0.4f);
        }

        //System.out.println(Gdx.input.getX()+"       "+ Gdx.input.getY()+ cam.position);

        testbutton.update();
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {

        sr.setColor(Color.BLACK);
        sb.setProjectionMatrix(cam.combined);
        sr.setProjectionMatrix(cam.combined);
        testbutton.render(sr, sb);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        //sr.box(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY(),0, 50, 50,1);
        sr.end();

    }

    @Override
    public void dispose() {

    }
}
