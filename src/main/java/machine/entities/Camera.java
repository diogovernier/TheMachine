package machine.entities;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import machine.core.KeyboardHandler;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;

	public Camera() {

	}

	public void move() {
		if (KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_W)) {
			position.z -= 0.02f;
		}
		if (KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_D)) {
			position.x += 0.02f;
		}

		if (KeyboardHandler.isKeyDown(GLFW.GLFW_KEY_A)) {
			position.x -= 0.02f;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

}
