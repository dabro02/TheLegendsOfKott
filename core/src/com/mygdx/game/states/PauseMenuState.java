package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.sprites.Kothaufen;

/**
 * Created by Daniel on 03.08.2017.
 */
public class PauseMenuState extends State {

    MathUtils math;

    Button testbutton;
    Button about;
    Button graphics;
    Button control;
    Button saveAndExit;
    Sound kottsound;
    Kothaufen kott1;
    Kothaufen kott2;
    Kothaufen kott3;
    Kothaufen kott4;

    int mouseX;
    int mouseY;


    public PauseMenuState(GameStateManager gsm) {
        super(gsm);

        Gdx.gl.glClearColor(0.1f, 0.66f, 0.2f, 1);
        cam.position.set(new Vector3(1920,1080,0)) ;
        cam.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        math = new MathUtils();


        //testbutton = new Button("Dies ist toll", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/2, 200,70);
        //kottsound = Gdx.audio.newSound(Gdx.files.internal("Kott-Sound.mp3"));
        about = new Button("About", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/5*4, 200,70);
        graphics = new Button("Graphics", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/5*3, 200,70);
        control = new Button("Control", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/5*2, 200,70);
        saveAndExit = new Button("Save and Exit", Gdx.graphics.getWidth()/2-100, Gdx.graphics.getHeight()/5, 200,70);


        kott1 = new Kothaufen(math.random(200, Gdx.graphics.getWidth()-200),math.random(200, Gdx.graphics.getHeight()-200));
        kott1.setStatus(0);
        kott2 = new Kothaufen(math.random(200, Gdx.graphics.getWidth()-200),math.random(200, Gdx.graphics.getHeight()-200));
        kott2.setStatus(0);
        kott3 = new Kothaufen(math.random(200, Gdx.graphics.getWidth()-200),math.random(200, Gdx.graphics.getHeight()-200));
        kott3.setStatus(0);
        kott4 = new Kothaufen(math.random(200, Gdx.graphics.getWidth()-200),math.random(200, Gdx.graphics.getHeight()-200));
        kott4.setStatus(0);
        //velocity of Kotthaufen
        kott1.setVelocityX(math.random(300,400));
        kott1.setVelocityY(math.random(300,400));
        kott2.setVelocityX(math.random(-400,-300));
        kott2.setVelocityY(math.random(-400,-300));
        kott3.setVelocityX(math.random(300,400));
        kott3.setVelocityY(math.random(300,400));
        kott4.setVelocityX(math.random(300,400));
        kott4.setVelocityY(math.random(300,400));
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
        //Exit PauseMenu
        if(Gdx.input.isKeyJustPressed(Input.Keys.CONTROL_LEFT)){
            gsm.pop();
        }
        //clicken
        if(Gdx.input.justTouched()){
            kott1.setVelocityX(mouseX-(int) kott1.getPosition().x-kott1.getKott().getWidth()/2);
            kott1.setVelocityY(Gdx.graphics.getHeight()-mouseY-(int) kott1.getPosition().y-kott1.getKott().getHeight()/2);

            kott2.setVelocityX(mouseX-(int) kott2.getPosition().x-kott2.getKott().getWidth()/2);
            kott2.setVelocityY(Gdx.graphics.getHeight()-mouseY-(int) kott2.getPosition().y-kott2.getKott().getHeight()/2);

            kott3.setVelocityX(mouseX-(int) kott3.getPosition().x-kott3.getKott().getWidth()/2);
            kott3.setVelocityY(Gdx.graphics.getHeight()-mouseY-(int) kott3.getPosition().y-kott3.getKott().getHeight()/2);

            kott4.setVelocityX(mouseX-(int) kott4.getPosition().x-kott4.getKott().getWidth()/2);
            kott4.setVelocityY(Gdx.graphics.getHeight()-mouseY-(int) kott4.getPosition().y-kott4.getKott().getHeight()/2);
        }

        if(about.isClicked()){
            //action
        }
        else if(graphics.isClicked()){
            //action
        }
        else if(control.isClicked()){
            //action
        }
        else if(saveAndExit.isClicked()){
            //action
        }
    }

    @Override
    public void update(float dt) {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        handleInput();

        /*if(testbutton.isClicked()){
            kottsound.play(0.4f);
        }

        //System.out.println(Gdx.input.getX()+"       "+ Gdx.input.getY()+ cam.position);

        testbutton.update();*/
        about.update();
        control.update();
        graphics.update();
        saveAndExit.update();

        kott1.update(dt);
        //abprallen an den Wänden
        if(kott1.getPosition().y < 0 | kott1.getPosition().y > Gdx.graphics.getHeight()-kott1.getKott().getHeight()){
            kott1.setVelocityY((int) - kott1.getVelocity().y /*+math.random(10,20)*/);
            kott1.setVelocityX((int) kott1.getVelocity().x /*+math.random(50,70)*/ );
        }
        if(kott1.getPosition().x < 0 | kott1.getPosition().x > Gdx.graphics.getWidth()-kott1.getKott().getWidth()) {
            kott1.setVelocityX((int) -kott1.getVelocity().x /*+math.random(10,20)*/);
            kott1.setVelocityY((int) kott1.getVelocity().y /*+math.random(50,70)*/ );
        }
        kott2.update(dt);
        if(kott2.getPosition().y < 0 | kott2.getPosition().y > Gdx.graphics.getHeight()-kott2.getKott().getHeight()){
            kott2.setVelocityY((int) - kott2.getVelocity().y /*+math.random(10,20)*/);
            kott2.setVelocityX((int) kott2.getVelocity().x /*+math.random(50,70)*/ );
        }
        if(kott2.getPosition().x < 0 | kott2.getPosition().x > Gdx.graphics.getWidth()-kott2.getKott().getWidth()) {
            kott2.setVelocityX((int) -kott2.getVelocity().x );
            kott2.setVelocityY((int) kott2.getVelocity().y  );
        }

        kott3.update(dt);
        if(kott3.getPosition().y < 0 | kott3.getPosition().y > Gdx.graphics.getHeight()-kott3.getKott().getHeight()){
            kott3.setVelocityY((int) - kott3.getVelocity().y /*+math.random(10,20)*/);
            kott3.setVelocityX((int) kott3.getVelocity().x /*+math.random(50,70)*/ );
        }
        if(kott3.getPosition().x < 0 | kott3.getPosition().x > Gdx.graphics.getWidth()-kott3.getKott().getWidth()) {
            kott3.setVelocityX((int) -kott3.getVelocity().x );
            kott3.setVelocityY((int) kott3.getVelocity().y  );
        }

        kott4.update(dt);
        if(kott4.getPosition().y < 0 | kott4.getPosition().y > Gdx.graphics.getHeight()-kott4.getKott().getHeight()){
            kott4.setVelocityY((int) - kott4.getVelocity().y /*+math.random(10,20)*/);
            kott4.setVelocityX((int) kott4.getVelocity().x /*+math.random(50,70)*/ );
        }
        if(kott4.getPosition().x < 0 | kott4.getPosition().x > Gdx.graphics.getWidth()-kott4.getKott().getWidth()) {
            kott4.setVelocityX((int) -kott4.getVelocity().x );
            kott4.setVelocityY((int) kott4.getVelocity().y  );
        }


        cam.update();
    }

    @Override
    public void render(SpriteBatch sb, ShapeRenderer sr) {

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        kott1.render(sb);
        kott2.render(sb);
        kott3.render(sb);
        kott4.render(sb);
        sb.end();

        sr.setColor(Color.BLACK);
        sr.setProjectionMatrix(cam.combined);
        //testbutton.render(sr, sb);
        about.render(sr,sb);
        graphics.render(sr,sb);
        control.render(sr,sb);
        saveAndExit.render(sr,sb);
        /*sr.begin(ShapeRenderer.ShapeType.Filled);
        //sr.box(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY(),0, 50, 50,1);
        sr.end();*/


    }

    @Override
    public void dispose() {

    }
}
