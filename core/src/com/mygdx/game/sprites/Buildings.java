package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.awt.image.BufferedImage;

/**
 * Created by Daniel on 14.05.2017.
 */
public class Buildings {

    private Vector3 position;
    private Texture texture;

    public Buildings(String pathname, int xPosition, int yPosition) {
        texture = new Texture(Gdx.files.internal(pathname));
        this.position = new Vector3(xPosition,yPosition,0);
    }

    public boolean hit(Vector3 camposition){

        if(camposition.x+75 <= position.x+texture.getWidth() && camposition.x+75 >= position.x  && camposition.y+75 >= position.y&& camposition.y+75 <= position.y+texture.getHeight()) {
            return true;
        }

        return false;
    }

    public Vector3 enter() {
        return new Vector3(position.x+texture.getWidth()/2, position.y+texture.getHeight()/2,0);
    }

    public boolean seeable(Vector3 camposition) {

        if(position.x < camposition.x+1920 && position.x > camposition.x-1920 && position.y < camposition.y+1080 && position.y > camposition.y-1080){
            return true;
        }

        return false;
    }

    public void draw(SpriteBatch sb) {
        sb.draw(texture,position.x, position.y);
    }

}
