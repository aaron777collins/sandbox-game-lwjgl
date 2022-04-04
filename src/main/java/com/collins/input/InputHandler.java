package com.collins.input;
import static org.lwjgl.glfw.GLFW.*;


public class InputHandler {

    public static boolean UP, DOWN, LEFT, RIGHT, VERT_UP, VERT_DOWN, CAM_UP, CAM_DOWN, CAM_LEFT, CAM_RIGHT = false;

    public static void setBindings(long window) {
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
		glfwSetKeyCallback(window, (window1, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(window1, true); // We will detect this in the rendering loop
            if ( (key == GLFW_KEY_A) && action == GLFW_PRESS)
                LEFT = true;
            if ( (key == GLFW_KEY_W) && action == GLFW_PRESS)
                UP = true;
            if ( (key == GLFW_KEY_D) && action == GLFW_PRESS)
                RIGHT = true;
            if ( (key == GLFW_KEY_S) && action == GLFW_PRESS)
                DOWN = true;
            if ( (key == GLFW_KEY_Q) && action == GLFW_PRESS)
                VERT_UP = true;
            if ( (key == GLFW_KEY_E) && action == GLFW_PRESS)
                VERT_DOWN = true;
            if ( (key == GLFW_KEY_UP) && action == GLFW_PRESS)
                CAM_UP = true;
            if ( (key == GLFW_KEY_DOWN) && action == GLFW_PRESS)
                CAM_DOWN = true;
            if ( (key == GLFW_KEY_LEFT) && action == GLFW_PRESS)
                CAM_LEFT = true;
            if ( (key == GLFW_KEY_RIGHT) && action == GLFW_PRESS)
                CAM_RIGHT = true;
            if ( (key == GLFW_KEY_A) && action == GLFW_RELEASE)
                LEFT = false;
            if ( (key == GLFW_KEY_W) && action == GLFW_RELEASE)
                UP = false;
            if ( (key == GLFW_KEY_D) && action == GLFW_RELEASE)
                RIGHT = false;
            if ( (key == GLFW_KEY_S) && action == GLFW_RELEASE)
                DOWN = false;
            if ( (key == GLFW_KEY_Q) && action == GLFW_RELEASE)
                VERT_UP = false;
            if ( (key == GLFW_KEY_E) && action == GLFW_RELEASE)
                VERT_DOWN = false;
            if ( (key == GLFW_KEY_UP) && action == GLFW_RELEASE)
                CAM_UP = false;
            if ( (key == GLFW_KEY_DOWN) && action == GLFW_RELEASE)
                CAM_DOWN = false;
            if ( (key == GLFW_KEY_LEFT) && action == GLFW_RELEASE)
                CAM_LEFT = false;
            if ( (key == GLFW_KEY_RIGHT) && action == GLFW_RELEASE)
                CAM_RIGHT = false;
		});
    }
}