package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Kothaufen;

/**
 * Created by Daniel on 14.05.2017.
 */
public class HomeState extends State{

    private Kothaufen kott;
    private Sound kottsound;

    public HomeState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(0.032f, 0.032f, 0.032f, 1);
        kott = new Kothaufen(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/4);
        kott.setStatus(2);
        kottsound = Gdx.audio.newSound(Gdx.files.internal("Kott-Sound.mp3"));
        cam.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    protected void handleInput() {

        //Exit fullscreen
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            if(!Gdx.graphics.isFullscreen()){
                Gdx.graphics.setDisplayMode(super.width,super.height,!Gdx.graphics.isFullscreen());
            }
            else{
                Gdx.graphics.setDisplayMode(super.windowWidth,super.windowHeight,!Gdx.graphics.isFullscreen());
                cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            }
        }
        kott.movement();

        if(Gdx.input.justTouched()){
            kottsound.play(0.3f);
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

        cam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2-kott.getKott().getHeight()/2,0);
        if(cam.viewportHeight <Gdx.graphics.getHeight() ) {
            cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight+9);
        }
        if( cam.viewportWidth < Gdx.graphics.getWidth()){
            cam.setToOrtho(false, cam.viewportWidth+16, cam.viewportHeight);
        }



        if(kott.getPosition().x <= 0+kott.getKott().getWidth()/2){
            kott.setPosition(new Vector3(1+kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        else if(kott.getPosition().x+kott.getKott().getWidth()/2 >= Gdx.graphics.getWidth()){
            kott.setPosition(new Vector3(Gdx.graphics.getWidth()-1-kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        if(kott.getPosition().y+kott.getKott().getHeight()/2 >= Gdx.graphics.getHeight()){
            kott.setPosition(new Vector3(kott.getPosition().x, Gdx.graphics.getHeight()-1-kott.getKott().getWidth()/2,0));
        }else if(kott.getPosition().y <= 0+kott.getKott().getHeight()/2){
            kott.setPosition(new Vector3(kott.getPosition().x,1+kott.getKott().getHeight()/2,0));
        }
        //System.out.println(kott.getPosition());



        cam.update();
        kott.update(dt);
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(kott.getKott(),kott.getPosition().x -kott.getKott().getWidth()/2 ,kott.getPosition().y -kott.getKott().getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
