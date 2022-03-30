package com.collins.display.Models;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.display.Loader;
import com.collins.display.Renderer;
import com.collins.entities.Entity;
import com.collins.entities.EntityManager;
import com.collins.entities.Square;

public class DisplayManager {

    private long window;
    private Loader loader;
    private Renderer renderer;

    public DisplayManager(long window, Loader loader, Renderer renderer) {
        this.window = window;
        this.loader = loader;
        this.renderer = renderer;
    }

    public void render() {

        renderer.prepare();

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        for (Entity entity : EntityManager.getEntities()) {
            // entity.render();
            entity.render();
        }

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}
