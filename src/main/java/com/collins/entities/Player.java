package com.collins.entities;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL21;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.display.Loader;
import com.collins.display.EntityRenderer;
import com.collins.display.Models.TexturedModel;
import com.collins.input.InputHandler;
import com.collins.shaders.StaticShader;

public class Player extends Square{

    float speed;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale, float speed) {
        super(model, position, rotX, rotY, rotZ, scale);

        this.speed = speed;
    }

    @Override
    public void update() {

        //increasePosition(0, 0, -0.1f);

        // if(InputHandler.UP) {
        //     y+=speed;
        // }
        // if(InputHandler.DOWN) {
        //     y-=speed;
        // }
        // if(InputHandler.LEFT) {
        //     x-=speed;
        // }
        // if(InputHandler.RIGHT) {
        //     x+=speed;
        // }
    }
}
