package com.collins.entities;

import org.lwjgl.opengl.GL21;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.display.Loader;
import com.collins.display.Renderer;
import com.collins.display.Models.RawModel;
import com.collins.input.InputHandler;

public class Square implements Entity{

    float x, y, width, height, speed;

    RawModel model;
    Renderer renderer;

    public Square(float x, float y, float width, float height, float speed, Loader loader, Renderer renderer) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        this.renderer = renderer;

        float[] vertices = {
            //Left bottom triangle
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            //Right top triangle
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
        };

        model = loader.loadToVAO(vertices);
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

    @Override
    public void render() {
        renderer.render(model);
    }
}
