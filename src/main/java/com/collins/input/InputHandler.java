package com.collins.input;
import static org.lwjgl.glfw.GLFW.*;


public class InputHandler {

    public static boolean UP, DOWN, LEFT, RIGHT, VERT_UP, VERT_DOWN = false;

    public static void setBindings(long window) {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window1, true); // We will detect this in the rendering loop
            if ( (key == GLFW_KEY_A || key == GLFW_KEY_LEFT) && action == GLFW_PRESS)
                LEFT = true;
            if ( (key == GLFW_KEY_W || key == GLFW_KEY_UP) && action == GLFW_PRESS)
                UP = true;
            if ( (key == GLFW_KEY_D || key == GLFW_KEY_RIGHT) && action == GLFW_PRESS)
                RIGHT = true;
            if ( (key == GLFW_KEY_S || key == GLFW_KEY_DOWN) && action == GLFW_PRESS)
                DOWN = true;
            if ( (key == GLFW_KEY_Q) && action == GLFW_PRESS)
                VERT_UP = true;
            if ( (key == GLFW_KEY_E) && action == GLFW_PRESS)
                VERT_DOWN = true;
            if ( (key == GLFW_KEY_A || key == GLFW_KEY_LEFT) && action == GLFW_RELEASE)
                LEFT = false;
            if ( (key == GLFW_KEY_W || key == GLFW_KEY_UP) && action == GLFW_RELEASE)
                UP = false;
            if ( (key == GLFW_KEY_D || key == GLFW_KEY_RIGHT) && action == GLFW_RELEASE)
                RIGHT = false;
            if ( (key == GLFW_KEY_S || key == GLFW_KEY_DOWN) && action == GLFW_RELEASE)
                DOWN = false;
            if ( (key == GLFW_KEY_Q) && action == GLFW_RELEASE)
                VERT_UP = false;
            if ( (key == GLFW_KEY_E) && action == GLFW_RELEASE)
                VERT_DOWN = false;
		});
    }
}