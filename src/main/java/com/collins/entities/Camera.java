package com.collins.entities;

import com.collins.input.InputHandler;

import org.joml.Vector3f;

public class Camera {
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch; //high low
    private float yaw; //left right
    private float roll; //twist

    public void move() {
        if(InputHandler.UP) {
            position.z-=0.1f;
        }
        if(InputHandler.DOWN) {
            position.z+=0.1f;
        }
        if(InputHandler.RIGHT) {
            position.x+=0.1f;
        }
        if(InputHandler.LEFT) {
            position.x-=0.1f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }
    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
    public float getRoll() {
        return roll;
    }

}
