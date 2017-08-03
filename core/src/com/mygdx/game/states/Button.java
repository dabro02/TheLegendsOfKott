package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Daniel on 21.07.2017.
 */
public class Button {

    BitmapFont font;
    GlyphLayout glayout;

    int x;
    int y;
    int width;
    int height;
    String title;

    public Vector3 position;
    boolean pointed = false;

    public Button(String title, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.title = title;
        position = new Vector3(x,y,0);
        font = new BitmapFont(Gdx.files.internal("FontCalibri28black.fnt"));
        glayout = new GlyphLayout();
    }

    public void update(){
        if(Gdx.input.getX() >= x && Gdx.input.getX() <= x+width && Gdx.graphics.getHeight()-Gdx.input.getY() <= y+height && Gdx.graphics.getHeight()-Gdx.input.getY() >= y){
            pointed = true;
            System.out.println(true);
        }
        else pointed = false;
    }

    public void render(ShapeRenderer sr, SpriteBatch sb){
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0,0.4f, 0.6f,0.3f);
        if(!pointed) {
            sr.box(position.x, position.y, position.z, width, height, 0);
            //System.out.println(glayout.width+"  "+glayout.height);

        }
        else{
            sr.setColor(Color.BLACK);
            sr.box(position.x, position.y, position.z, width+4, height+4, 0);
            sr.setColor(0,0.4f, 0.6f,0.3f);
            sr.box(position.x+3, position.y+3, position.z,width,height,0);
        }
        //font.draw(sb,title,200,400);
        sr.end();
        sb.begin();

        glayout.setText(font, title.subSequence(0,title.length()));
        if(pointed)
            font.draw(sb, title, x+width/2-glayout.width/2+3, y+(height/2+glayout.height/2)+3);
        else
            font.draw(sb, title, x+width/2-glayout.width/2, y+(height/2+glayout.height/2));

        sb.end();

    }
}
