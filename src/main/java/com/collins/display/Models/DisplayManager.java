package com.collins.display.Models;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.collins.entities.Entity;
import com.collins.entities.EntityManager;
import com.collins.entities.Square;

public class DisplayManager {

    private long window;

    public DisplayManager(long window) {
        this.window = window;
    }

    public void render() {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        for (Entity entity : EntityManager.getEntities()) {
            entity.draw();
        }

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}
