package com.collins;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import com.collins.display.Loader;
import com.collins.display.Renderer;
import com.collins.display.Models.DisplayManager;
import com.collins.entities.EntityManager;
import com.collins.entities.Player;
import com.collins.input.InputHandler;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class App {

	// The window handle
	private long window;
    private float UPS = 60f;
    private float FPS = 60f;
    private boolean RENDER_TIME = false;
    private DisplayManager displayManager;
	private Loader loader;
	private Renderer renderer;

	public void run() {
		System.out.println("Hello LWJGL " + Version.getVersion() + "!");

		init();
		loop();

		// Free the window callbacks and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		// Setup an error callback. The default implementation
		// will print the error message in System.err.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");

		// Configure GLFW
		glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		// Create the window
		window = glfwCreateWindow(1280, 720, "Sandbox - LWJGL", NULL, NULL);
		if ( window == NULL )
			throw new RuntimeException("Failed to create the GLFW window");

		InputHandler.setBindings(window);

		// Get the thread stack and push a new frame
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);
		// Disable v-sync  (1 to enable)
		glfwSwapInterval(0);

		// Make the window visible
		glfwShowWindow(window);

				// This line is critical for LWJGL's interoperation with GLFW's
		// OpenGL context, or any context that is managed externally.
		// LWJGL detects the context that is current in the current thread,
		// creates the GLCapabilities instance and makes the OpenGL
		// bindings available for use.
		GL.createCapabilities();

		loader = new Loader();
		renderer = new Renderer();

        displayManager = new DisplayManager(window, loader, renderer);
        EntityManager.init();

        //adding player
        EntityManager.getEntities().add(new Player(0.2f, 0.2f, 0.2f, 0.2f, 0.05f, loader, renderer));
	}

	private void loop() {

		// Set the clear color
		//glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		// glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

		// // Run the rendering loop until the user has attempted to close
		// // the window or has pressed the ESCAPE key.
		// while ( !glfwWindowShouldClose(window) ) {
		// 	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

		// 	glfwSwapBuffers(window); // swap the color buffers

		// 	// Poll for window events. The key callback above will only be
		// 	// invoked during this call.
		// 	glfwPollEvents();
		// }

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while ( !glfwWindowShouldClose(window)) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                getInput();
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                if (RENDER_TIME) {
                    System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
                }
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }

		//game loop over
		loader.cleanUp();

	}

    private void getInput() {

    }

    private void update() {
        EntityManager.update();
    }

    private void render() {
        displayManager.render();
    }

	public static void main(String[] args) {
		new App().run();
	}

}