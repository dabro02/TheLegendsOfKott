package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Daniel on 13.05.2017.
 */
public class Kothaufen {
    private Vector3 position;
    private Vector3 velocity;
    Texture kott;

    public Kothaufen(int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        kott = new Texture("Kothaufen.png");
    }

    public void update(float dt) {
            velocity.scl(dt);
        position.add(velocity);
            velocity.scl(1/dt);
            //System.out.println(position);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getKott() {
        return kott;
    }

    public void setVelocityX(int x) {
        this.velocity.x = x;
    }
    public void setVelocityY(int y) {
        this.velocity.y = y;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }
}
