package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.states.State;
import com.sun.prism.image.ViewPort;

/**
 * Created by Daniel on 13.05.2017.
 */
public class Kothaufen {
    private Vector3 position;
    private Vector3 velocity;
    Texture kott;
    int status;
    int width;
    int height;
    Viewport viewport;

    public Kothaufen(int x, int y, Viewport viewport) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        kott = new Texture(Gdx.files.internal("Kothaufen.png"));
        this.viewport = viewport;
    }

    public Kothaufen(int x, int y, int width, int height, Viewport viewport){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        kott = new Texture(Gdx.files.internal("Kothaufen.png"));
        this.width=width;
        this.height=height;
        this.viewport = viewport;
    }

    public void update(float dt) {
            velocity.scl(dt);
        position.add(velocity);
            velocity.scl(1/dt);
    }

    public void render(SpriteBatch sb) {
        sb.draw(kott, position.x, position.y);
    }

    public void walkright() {
        //Richtung: D ->
            setVelocityX(170);
    }

    public void walkleft() {
        //Richtung: A <-
            setVelocityX(-170);
    }

    public void walkup() {
        //Richtung: W (Oben)
            setVelocityY(145);
    }

    public void walkdown(){
        //Richtung: S (Unten)
            setVelocityY(-145);
    }

    //handleinput für die Bewegung
    public void movement() {
        //Bewegung des Haufens über WASD

        /*if(Gdx.input.isKeyPressed(Input.Keys.W)){
            walkup();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            walkdown();
            }
            else {
                setVelocityY(0);
            }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            walkleft();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)){
            walkright();
        }
        else {
            setVelocityX(0);
        }*/


        //Bewegung über Maus
        if(Gdx.input.isTouched()){

            if(status == 1){
                setVelocityX(-(position.x-viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY())).x));
                setVelocityY(-(position.y-viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY())).y));
            }
            else if(status == 2) {
                setVelocityX(-(getPosition().x-viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY())).x));
                setVelocityY(-((getPosition().y-kott.getHeight()/2)-(Gdx.graphics.getHeight()-viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY())).y)));
            }
        }
        else {
            setVelocityX(0);
            setVelocityY(0);
        }
    }


    public Vector3 getPosition() {
        return position;
    }

    public Texture getKott() {
        return kott;
    }

    public void setVelocityX(float x) {
        if(status >=1){
            if(x > 170)
                x=170;
            if(x < -170)
                x = -170;
        }
        this.velocity.x = x;
    }
    public void setVelocityY(float y) {
        if(status >= 1){
            if(y > 145)
                y=145;
            if(y < -145)
                y = -145;
        }
        this.velocity.y = y;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector3 getVelocity() {
        return velocity;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
