package com.collins.entities;

import com.collins.input.InputHandler;

import org.joml.Vector3f;

public class Camera {
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch = 0; //high low
    private float yaw = 0; //left right
    private float roll = 0; //twist

    public void move() {
        if(InputHandler.UP) {
            position.z-=1f;
        }
        if(InputHandler.DOWN) {
            position.z+=1f;
        }
        if(InputHandler.RIGHT) {
            position.x+=1f;
        }
        if(InputHandler.LEFT) {
            position.x-=1f;
        }
        if(InputHandler.VERT_UP) {
            position.y+=1f;
        }
        if(InputHandler.VERT_DOWN) {
            position.y-=1f;
        }
        if(InputHandler.CAM_UP) {
            pitch-=1f;
        }
        if(InputHandler.CAM_DOWN) {
            pitch+=1f;
        }
        if(InputHandler.CAM_RIGHT) {
            yaw+=1f;
        }
        if(InputHandler.CAM_LEFT) {
            yaw-=1f;
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
