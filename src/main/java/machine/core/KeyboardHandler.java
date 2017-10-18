package machine.core;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;

import org.lwjgl.glfw.GLFWKeyCallback;

public class KeyboardHandler extends GLFWKeyCallback {

	public static boolean[] keys = new boolean[65536];

	// The GLFWKeyCallback class is an abstract method that
	// can't be instantiated by itself and must instead be extended
	//
	@Override
	public void invoke(long window, int key, int scancode, int action, int mods) {
		if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
			glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop

		keys[key] = action != GLFW_RELEASE;
	}

	// boolean method that returns true if a given key
	// is pressed.
	public static boolean isKeyDown(int keycode) {
		return keys[keycode];
	}

}
