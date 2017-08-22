package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.sprites.Kothaufen;

/**
 * Created by Daniel on 14.05.2017.
 */
public class HomeState extends State{

    private Kothaufen kott;
    private Sound kottsound;
    private Texture houseBackground, bed, chest;
    private Vector3 bedPosition, chestPosition;

    public HomeState(GameStateManager gsm) {
        super(gsm);
        Gdx.gl.glClearColor(0.032f, 0.032f, 0.032f, 1);
        kott = new Kothaufen(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/4,viewport);
        kott.setStatus(2);
        kottsound = Gdx.audio.newSound(Gdx.files.internal("Kott-Sound.mp3"));
        houseBackground = new Texture("Hausboden.png");
        bed = new Texture("Bett.png");
        bedPosition = new Vector3(1920-bed.getWidth()-10, 1080-bed.getHeight(),0);
        chest = new Texture("Truhe.png");
        chestPosition = new Vector3(1920-chest.getHeight()-10, 600,0);
        rezise(true);
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
                //cam.setToOrtho(false,1920,1080);
                rezise(true);
            }
        }
        kott.movement();

        if(Gdx.input.justTouched()){
            kottsound.play(0.3f);
            /*if( (float)1920/super.width*Gdx.input.getX() >= bedPosition.x && (float)1920/super.width*Gdx.input.getX() <= ) {

            }*/
        }



    }

    @Override
    public void update(float dt) {
        handleInput();



        if(kott.getPosition().x <= kott.getKott().getWidth()/2){
            kott.setPosition(new Vector3(1+kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        else if(kott.getPosition().x+kott.getKott().getWidth()/2 >= Gdx.graphics.getWidth()){
            kott.setPosition(new Vector3(Gdx.graphics.getWidth()-1-kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        if(kott.getPosition().y >= Gdx.graphics.getHeight()){
            kott.setPosition(new Vector3(kott.getPosition().x, Gdx.graphics.getHeight()-1,0));
        }
        else if(kott.getPosition().y <= 0+kott.getKott().getHeight()){
            kott.setPosition(new Vector3(kott.getPosition().x,1+kott.getKott().getHeight(),0));
        }
        //System.out.println(kott.getPosition());


        viewport.apply();
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
