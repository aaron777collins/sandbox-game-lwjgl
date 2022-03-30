package com.collins.display;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.entities.Entity;
import com.collins.entities.EntityManager;
import com.collins.entities.Square;
import com.collins.shaders.StaticShader;

public class DisplayManager {

    private long window;
    private Loader loader;
    private Renderer renderer;
    private StaticShader shader;

    public DisplayManager(long window, Loader loader, Renderer renderer, StaticShader shader) {
        this.window = window;
        this.loader = loader;
        this.renderer = renderer;
        this.shader = shader;
    }

    public void render() {

        renderer.prepare();

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        for (Entity entity : EntityManager.getEntities()) {
            // entity.render();
            shader.start();
            renderer.render(entity, shader);
            shader.stop();
        }

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}
