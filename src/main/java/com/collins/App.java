package com.collins;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.glfw.GLFW.glfwDefaultWindowHints;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwShowWindow;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;
import java.util.List;

import com.collins.display.DisplayManager;
import com.collins.display.Loader;
import com.collins.display.MasterRenderer;
import com.collins.display.ModelData;
import com.collins.display.OBJFileLoader;
import com.collins.display.Models.RawModel;
import com.collins.display.Models.TexturedModel;
import com.collins.display.textures.ModelTexture;
import com.collins.entities.Camera;
import com.collins.entities.Entity;
import com.collins.entities.ObjectManager;
import com.collins.entities.Light;
import com.collins.entities.Square;
import com.collins.input.InputHandler;
import com.collins.terrains.Terrain;

import org.joml.Random;
import org.joml.Vector3f;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import javafx.geometry.Dimension2D;

// NOTES:

        //adding player

		// float[] vertices = {
        //     //Left bottom triangle
        //     -0.5f, 0.5f, 0, //V0
        //     -0.5f, -0.5f, 0,//V1
        //     0.5f, -0.5f, 0, //V2
        //     0.5f, 0.5f, 0   //v3
        // };

        // int[] indices = {
        //     0, 1, 3, //Top Left triangle (V0, V1, V3)
        //     3, 1, 2  //Bottom right triangle (V3, V1, V2)
        // };

        // float[] textureCoords = {
        //     0, 0, //V0
        //     0, 1, //V1
        //     1, 1, //V2
        //     1, 0  //V3
        // };


public class App {

	// The window handle
	private static long window;
    private float UPS = 60f;
    private float FPS = 60f;
    private boolean RENDER_TIME = false;
    private DisplayManager displayManager;
	private Loader loader;
	private Camera camera;
	private Light light;
	private MasterRenderer masterRenderer;

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

		masterRenderer = new MasterRenderer();

		camera = new Camera();
		light = new Light(new Vector3f(3000, 2000, 3000), new Vector3f(1,1,1));
        ObjectManager.init();
		displayManager = new DisplayManager(window, masterRenderer, camera, light);

		List<Terrain> terrains = ObjectManager.getTerrains();

		terrains.add(new Terrain(-1,-1, loader, new ModelTexture(loader.loadTexture("grass"))));
		terrains.add(new Terrain(0,-1, loader, new ModelTexture(loader.loadTexture("grass"))));


		ModelData modelData = OBJFileLoader.loadOBJ("cube2");
        RawModel rawModel = loader.loadToVAO(modelData.getVertices(), modelData.getTextureCoords(), modelData.getNormals(), modelData.getIndices());
        TexturedModel texturedModel = new TexturedModel(rawModel, new ModelTexture(loader.loadTexture("coolTexture")));
		ModelTexture texture = texturedModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

        List<Entity> entities = ObjectManager.getEntities();
		Random random = new Random();

		for (int i = 0; i< 200; i++) {
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			entities.add(new Square(texturedModel, new Vector3f(x, y, z), random.nextFloat()*180f, random.nextFloat()*180f, 0f, 1f));

		}

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
		masterRenderer.cleanUp();
		loader.cleanUp();
	}

    private void getInput() {

    }

    private void update() {
        ObjectManager.update();
		camera.move();
    }

    private void render() {
        displayManager.render();
    }

	public static void main(String[] args) {
		new App().run();
	}

	public static Dimension2D getWindowSize() {
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			return new Dimension2D(pWidth.get(0), pHeight.get(0));

		} catch(Exception e) {

			System.out.println("Error getting window size");
			System.exit(-1);
		}
		return null; //never reaches
	}

}