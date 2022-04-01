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
import com.collins.display.Models.RawModel;
import com.collins.display.Models.TexturedModel;
import com.collins.display.textures.ModelTexture;
import com.collins.input.InputHandler;
import com.collins.shaders.StaticShader;

public class Tree extends Entity{

    EntityRenderer renderer;
    StaticShader shader;

    public Tree(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);

    }

    @Override
    public void update() {

        //increaseRotation(0.5f, 1f, 0);
    }
}
