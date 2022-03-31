package com.collins.display;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

import java.util.List;

import com.collins.entities.Camera;
import com.collins.entities.Entity;
import com.collins.entities.EntityManager;
import com.collins.entities.Light;

public class DisplayManager {

    private long window;
    private Camera camera;
    private Light light;
    private MasterRenderer renderer;
    private List<Entity> entities;

    public DisplayManager(long window, MasterRenderer renderer, Camera camera, Light light) {
        this.window = window;
        this.camera = camera;
        this.light = light;
        this.renderer = renderer;
        this.entities = EntityManager.getEntities();
    }

    public void render() {

        for (Entity entity : entities) {
            renderer.processEntity(entity);
        }

        renderer.render(light, camera);
        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();
    }
}
