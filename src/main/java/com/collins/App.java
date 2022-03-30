package com.collins;

import org.joml.Vector3f;
import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openvr.Texture;
import org.lwjgl.system.*;

import java.nio.*;

import com.collins.display.DisplayManager;
import com.collins.display.Loader;
import com.collins.display.Renderer;
import com.collins.display.Models.RawModel;
import com.collins.display.Models.TexturedModel;
import com.collins.display.textures.ModelTexture;
import com.collins.entities.EntityManager;
import com.collins.entities.Player;
import com.collins.input.InputHandler;
import com.collins.shaders.StaticShader;

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
	private StaticShader shader;

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
		shader = new StaticShader();

        displayManager = new DisplayManager(window, loader, renderer, shader);
        EntityManager.init();

        //adding player

		float[] vertices = {
            //Left bottom triangle
            -0.5f, 0.5f, 0, //V0
            -0.5f, -0.5f, 0,//V1
            0.5f, -0.5f, 0, //V2
            0.5f, 0.5f, 0   //v3
        };

        int[] indices = {
            0, 1, 3, //Top Left triangle (V0, V1, V3)
            3, 1, 2  //Bottom right triangle (V3, V1, V2)
        };

        float[] textureCoords = {
            0, 0, //V0
            0, 1, //V1
            1, 1, //V2
            1, 0  //V3
        };

        ModelTexture texture = new ModelTexture(loader.loadTexture("coolTexture"));
        RawModel rawModel = loader.loadToVAO(vertices, textureCoords, indices);
        TexturedModel texturedModel = new TexturedModel(rawModel, texture);

        EntityManager.getEntities().add(new Player(texturedModel, new Vector3f(-1.0f, 0f, 0.0f), 0f, 0f, 0f, 1f, 0.5f));
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
		shader.cleanUp();

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