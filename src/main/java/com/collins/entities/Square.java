package com.collins.entities;

import org.lwjgl.opengl.GL21;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.input.InputHandler;

public class Square implements Entity{

    float x, y, width, height, speed;

    public Square(float x, float y, float width, float height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    @Override
    public void draw() {
        GL21.glBegin(GL_QUADS);//static field
        GL21.glVertex3f(x,y, 0);
        GL21.glVertex3f(x + width,y, 0);
        GL21.glVertex3f(x + width,y - height, 0);
        GL21.glVertex3f(x,y - height, 0);
        GL21.glEnd();
    }

    @Override
    public void update() {

        //no movements
    }
}
