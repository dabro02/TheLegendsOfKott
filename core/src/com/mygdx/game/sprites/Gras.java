package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Daniel on 13.05.2017.
 */
public class Gras {

    Texture grasbüschel;
    Vector3 position;

    public Gras(int grasbüschelX, int grasbüschelY, int art){
        position = new Vector3(grasbüschelX, grasbüschelY,0);
        switch (art){
            case 0:
                grasbüschel = new Texture(Gdx.files.internal("Grasbüschel.png"));
                break;
            case 1:
                grasbüschel = new Texture(Gdx.files.internal("Grasbüschel2.png"));
                break;
            case 2:
                grasbüschel= new Texture(Gdx.files.internal("Grasbüschel3.png"));
                break;
            case 3:
                grasbüschel= new Texture(Gdx.files.internal("Grasbüschel4.png"));
                break;
            case 4:
                grasbüschel= new Texture(Gdx.files.internal("Grasbüschel5.png"));
                break;

                default:
                    grasbüschel = new Texture(Gdx.files.internal("Grasbüschel.png"));
                    break;


        }
    }

    public void draw(SpriteBatch sb) {
        sb.draw(grasbüschel, position.x,position.y);
    }

    public int getGrasbüschelX() {
        return (int) position.x;
    }

    public void setGrasbüschelX(int grasbüschelX) {
        this.position.x = grasbüschelX;
    }

    public int getGrasbüschelY() {
        return (int) position.y;
    }

    public void setGrasbüschelY(int grasbüschelY) {
        this.position.y = grasbüschelY;
    }
}
