package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
        kott = new Kothaufen(1920/2,1080/4);
        kottsound = Gdx.audio.newSound(Gdx.files.internal("Kott-Sound.mp3"));
        cam.setToOrtho(false, 1920,1080);
    }

    @Override
    protected void handleInput() {

        //Bewegung des Haufens
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            kott.walkup();}

        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            kott.walkdown(); }

        else if(Gdx.input.isKeyPressed(Input.Keys.A)){
            kott.walkleft(); }

        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            kott.walkright(); }
        else {
            kott.setVelocityX(0);
            kott.setVelocityY(0);
        }

        if(Gdx.input.justTouched()){
            kottsound.play(0.1f);
        }

    }

    @Override
    public void update(float dt) {
        handleInput();

        cam.position.set(1920/2, 1080/2-kott.getKott().getHeight()/2,0);
        if(cam.viewportHeight <1080 ) {
            cam.setToOrtho(false, cam.viewportWidth, cam.viewportHeight+9);
        }
        if( cam.viewportWidth < 1920){
            cam.setToOrtho(false, cam.viewportWidth+16, cam.viewportHeight);
        }



        if(kott.getPosition().x <= 0+kott.getKott().getWidth()/2){
            kott.setPosition(new Vector3(1+kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        else if(kott.getPosition().x+kott.getKott().getWidth()/2 >= 1920){
            kott.setPosition(new Vector3(1919-kott.getKott().getWidth()/2,kott.getPosition().y,0));
        }
        if(kott.getPosition().y+kott.getKott().getHeight()/2 >= 1080){
            kott.setPosition(new Vector3(kott.getPosition().x, 1079-kott.getKott().getWidth()/2,0));
        }else if(kott.getPosition().y <= 0+kott.getKott().getHeight()/2){
            kott.setPosition(new Vector3(kott.getPosition().x,1+kott.getKott().getHeight()/2,0));
        }
        //System.out.println(kott.getPosition());



        cam.update();
        kott.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(kott.getKott(),kott.getPosition().x -kott.getKott().getWidth()/2 ,kott.getPosition().y -kott.getKott().getHeight());
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
