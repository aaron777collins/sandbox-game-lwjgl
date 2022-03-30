package com.collins.entities;

import org.lwjgl.opengl.GL21;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.display.Loader;
import com.collins.display.Renderer;
import com.collins.input.InputHandler;

public class Player extends Square{

    public Player(float x, float y, float width, float height, float speed, Loader loader, Renderer renderer) {
        super(x, y, width, height, speed, loader, renderer);
        //TODO Auto-generated constructor stub

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    @Override
    public void update() {

        if(InputHandler.UP) {
            y+=speed;
        }
        if(InputHandler.DOWN) {
            y-=speed;
        }
        if(InputHandler.LEFT) {
            x-=speed;
        }
        if(InputHandler.RIGHT) {
            x+=speed;
        }
    }
}
